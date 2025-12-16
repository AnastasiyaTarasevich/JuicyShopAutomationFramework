pipeline {
    agent any

    environment {
        CI = 'true'
        BASE_URL = credentials('BASE_URL')
        LOGIN_USER = credentials('LOGIN_USER')
        LOGIN_PASSWORD = credentials('LOGIN_PASSWORD')
        RP_UUID = credentials('RP_UUID')
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

                    // Собираем системные свойства для ReportPortal
                    def rpProps = "-Drp.endpoint=http://34.118.90.140:8090 " +
                                  "-Drp.project=juicy_shop_web " +
                                  "-Drp.uuid=${env.RP_UUID} " +
                                  "-Drp.launch=${env.JOB_NAME}-${env.BUILD_NUMBER} " +
                                  "-Drp.description=Build URL: ${env.BUILD_URL} " +
                                  "-Drp.enable=true"

                    // Запуск тестов с Allure и ReportPortal
                    def result = sh(script: "./gradlew clean test ${rpProps} -Dgroups=ui,api", returnStatus: true)
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
Allure report: ${env.BUILD_URL}allure
ReportPortal: ${env.BUILD_URL}"""

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
