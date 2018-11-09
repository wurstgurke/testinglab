pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
        }
    }
    stages {
        stage('Test') {
             environment {
                PROXY_HOST='http://http-proxy.intern.neusta.de'
                PROXY_PORT='3128'
             }
             steps{
                sh "mvn test"
             }
             post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
             }
        }
    }
}
