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
                sh 'mvn clean test -Dgroups=ui,api'
            }
        }

        stage('Allure Report') {
            steps {
                allure results: [[path: "target/allure-results"]]
            }
        }
    }
}
