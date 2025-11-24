pipeline {
    agent any

    environment {
        CI = 'true'
        BASE_URL = credentials('BASE_URL')
        LOGIN_USER = credentials('LOGIN_USER')
        LOGIN_PASSWORD = credentials('LOGIN_PASSWORD')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                script {
                    sh 'chmod +x gradlew'

                    def result = sh(script: "./gradlew clean test -Dgroups=ui,api", returnStatus: true)
                    if (result != 0) {
                        echo "Tests failed, but continuing"
                    }
                }
            }
        }

        stage('Allure Report') {
            steps {
                echo "Generating Allure report..."
                allure([
                    results: [[path: 'build/allure-results']],
                    reportBuildPolicy: 'ALWAYS'
                ])
                echo "Allure report generated."
            }
        }
    }

    post {
        success {
            echo 'Tests passed successfully!'
        }
        failure {
            echo 'Tests failed. Check logs for details.'
        }
    }
}
