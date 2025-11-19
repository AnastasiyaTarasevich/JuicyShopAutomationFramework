pipeline {
    agent any

    environment
    {
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
