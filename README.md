# Java Application using Gradle

A small Java 17 web service built and delivered with Gradle and a CI/CD pipeline. It shows how to automate builds, manage dependencies, and ship continuously.

## Goals
- Automate Java project builds using Gradle
- Manage dependencies efficiently in the Java app
- Integrate CI/CD pipelines for continuous delivery
- Streamline build and deployment processes
- Understand core DevOps principles in Java development

## What's inside
| Path | Purpose |
|---|---|
| `build.gradle.kts` | Build logic: plugins, dependencies, tests, coverage |
| `gradle/libs.versions.toml` | Version catalog, the single place dependency versions live |
| `src/main`, `src/test` | App code (`/health`, `/greet?name=...`) and JUnit 5 tests |
| `Dockerfile` | Multi-stage build; runs as a non-root user |
| `.github/workflows/ci.yml` | GitHub Actions: build, test, publish image, deploy |
| `Jenkinsfile` | Equivalent Jenkins pipeline |

## One-time setup: generate the Gradle wrapper
The wrapper (`gradlew`) lets everyone, and CI, build with the same Gradle version. Generate it once with Gradle 8.x installed, then commit the files:
```bash
gradle wrapper --gradle-version 8.10.2
git add gradlew gradlew.bat gradle/wrapper
```

## Common commands
```bash
./gradlew build            # compile, test, package
./gradlew test             # run tests (coverage report in build/reports/jacoco)
./gradlew run              # start the app on http://localhost:8080
./gradlew dependencies     # inspect the dependency tree
./gradlew installDist      # runnable distribution in build/install
```
Try it:
```bash
curl localhost:8080/health
curl "localhost:8080/greet?name=Asha"
```

## Docker
```bash
docker build -t java-gradle-app .
docker run -p 8080:8080 java-gradle-app
```

## CI/CD flow
1. **Push / pull request**: build and run tests, upload test and coverage reports
2. **Merge to `main`**: build the Docker image and push it to GitHub Container Registry
3. **Deploy**: runs in the `production` environment (enable required reviewers for a manual approval gate). The step is a placeholder, so replace it with your real target.

## DevOps principles shown here
- **Automation**: one command builds, tests, and packages
- **Reproducibility**: the Gradle wrapper, pinned versions, and the Docker image
- **Fast feedback**: every change is tested automatically
- **Dependency hygiene**: a central version catalog and `./gradlew dependencies`
- **Continuous delivery**: tested changes on `main` become a deployable artifact
- **Security basics**: non-root container, no secrets in the repo

## License
MIT
