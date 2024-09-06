pipeline {
    agent any

    tools {
        maven "Maven 3.9.4"
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout code from Git repository
                git url: 'https://github.com/ManasaGottam/CucumberProject.git', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                // Run Maven build
                bat 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                // Run Cucumber tests
                bat 'mvn test'
            }
        }
    }

    post {
        always {
            // Archive test results
//            junit '**/target/surefire-reports/TEST-*.xml'
            junit 'reports/Session_*/html-report.html'
        }
    }
}