pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v ./maven-data/:/root/.m2'
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
