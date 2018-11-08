pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v ./.m2:/root/.m2 -u root'
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
