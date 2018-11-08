pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2 -e PROXY_HOST=http-proxy.intern.neusta.de -e PROXY_PORT=3128'
        }
    }
    stages {
        stage('Test') {
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
