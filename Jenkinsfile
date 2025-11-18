pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/AnastasiyaTarasevich/JuicyShopAutomationFramework.git', branch: 'main'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew clean test -Dgroups=ui,api'
            }
        }

        stage('Allure Report') {
            steps {
                allure results: [[path: "build/allure-results"]]
            }
        }

    }
     post {
                    always {

                        echo 'Pipeline finished.'
                    }
                    success {

                        echo 'Tests passed successfully!'
                    }
                    failure {

                        echo 'Tests failed. Check logs for details.'
                    }
                }
}
