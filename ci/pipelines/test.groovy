pipeline {
    agent {
        label 'built-in'
    }
    stages {
        stage('Checkout') {
            steps {
                // Checkout code from Git repository
                git url: 'https://github.com/ManasaGottam/CucumberProject.git', branch: 'main'
            }
        }
        stage("Run unit tests") {
            steps {
                bat "mvn clean test"
            }
        }
    }
    post {
        always {
            junit '**/surefire-reports/*.xml'
        }
    }
}