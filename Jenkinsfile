pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v C:/Users/bea/.m2:/root/.m2'
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
