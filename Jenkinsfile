pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Setup') {
             steps{
                sh "./docker/docker-compose up"
             }
        }
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
