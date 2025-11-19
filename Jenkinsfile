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

       stage('Check Juice Shop')
       {
            steps {
            def status = sh(script: "curl -o /dev/null -s -w \"%{http_code}\" $BASE_URL", returnStdout:true).trim()
            if(status != "200")
                {
                error "Juice shop is unavailable! HTTP code: ${status}"
                }else
                {
                    echo "Juice shop is available, HTTP code: ${status}"
                }
            }
       }
        stage('Build & Test') {
            steps {
                {
                    sh 'chmod +x gradlew'
                    sh './gradlew clean test -Dgroups=ui,api'
                }

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
