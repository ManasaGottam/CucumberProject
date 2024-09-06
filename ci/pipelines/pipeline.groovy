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
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                // Run tests
                bat 'mvn test'
            }
        }
    }

    post {
        always {
            // Archive test results
//            junit '**/target/surefire-reports/TEST-*.xml'
            archiveArtifacts artifacts: '**/*report*/**/*, **/logs/**/*'
            junit 'reports/Session_*/html-report.html'
            publishHTML (target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'reports/cucumber-reports',
                    reportFiles: 'cucumberReport.html',
                    reportName: 'Cucumber HTML Report'
            ])
        }
    }
}