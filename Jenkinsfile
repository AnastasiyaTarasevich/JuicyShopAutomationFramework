pipeline {
    agent any

    environment {
        CI = 'true'
        BASE_URL = credentials('BASE_URL')
        LOGIN_USER = credentials('LOGIN_USER')
        LOGIN_PASSWORD = credentials('LOGIN_PASSWORD')
    }

    stages {
        stage('Checkout SCM') {
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
                        echo "Some tests failed, but continuing pipeline..."
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

        stage('Telegram Notification') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'TELEGRAM_CHAT_ID', variable: 'CHAT_ID')]) {
                        def status = currentBuild.currentResult ?: 'UNKNOWN'
                        def message = """Job '${env.JOB_NAME}' #${env.BUILD_NUMBER} finished.
Status: ${status}
Check console: ${env.BUILD_URL}
Allure report: ${env.BUILD_URL}allure"""

                        telegramSend(
                            chatId: CHAT_ID,
                            message: message
                        )
                    }
                }
            }
        }
    }

    post {
        always {
            echo "Pipeline finished. Telegram notification stage handles sending messages."
        }
    }
}
