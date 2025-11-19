pipeline {
    agent any

    environment
    {
        BASE_URL = 'http://juicyshop:3000'
    }

    stages {
       stage('Checkout') {
           steps {
               checkout scm
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
