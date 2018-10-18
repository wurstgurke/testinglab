pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
        docker {
            image 'selenium/hub'
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
