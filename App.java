package com.example.app;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class App {

    private static final Gson GSON = new Gson();
    private static final GreetingService SERVICE = new GreetingService();

    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/health", ex -> send(ex, 200, Map.of("status", "UP")));
        server.createContext("/greet", ex -> {
            String name = queryParam(ex.getRequestURI().getRawQuery(), "name");
            send(ex, 200, Map.of("message", SERVICE.greet(name)));
        });

        server.start();
        System.out.println("Listening on port " + port);
    }

    private static String queryParam(String rawQuery, String key) {
        if (rawQuery == null) return null;
        for (String pair : rawQuery.split("&")) {
            String[] kv = pair.split("=", 2);
            if (kv[0].equals(key) && kv.length == 2) {
                return URLDecoder.decode(kv[1], StandardCharsets.UTF_8);
            }
        }
        return null;
    }

    private static void send(HttpExchange ex, int status, Object body) throws IOException {
        byte[] bytes = GSON.toJson(body).getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().add("Content-Type", "application/json");
        ex.sendResponseHeaders(status, bytes.length);
        try (OutputStream os = ex.getResponseBody()) {
            os.write(bytes);
        }
    }
}
