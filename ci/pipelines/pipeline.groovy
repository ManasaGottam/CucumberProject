pipeline {
    agent any

    tools {
        maven 'Maven 3.6.3' // Adjust as needed
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
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                // Run Cucumber tests
                sh 'mvn test'
            }
        }
    }

    post {
        always {
            // Archive test results
            junit '**/target/surefire-reports/TEST-*.xml'
        }
    }
}