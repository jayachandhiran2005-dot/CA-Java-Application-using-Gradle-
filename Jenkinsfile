pipeline {
  agent any
  options { timestamps(); disableConcurrentBuilds() }

  stages {
    stage('Build & Test') {
      steps { sh './gradlew clean build' }
      post {
        always {
          junit 'build/test-results/test/*.xml'
        }
      }
    }
    stage('Archive') {
      steps { archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true }
    }
    stage('Docker image') {
      when { branch 'main' }
      steps { sh 'docker build -t java-gradle-app:${BUILD_NUMBER} .' }
    }
    stage('Deploy') {
      when { branch 'main' }
      steps { echo 'Add your deployment step here (ssh, kubectl, etc.)' }
    }
  }
}
