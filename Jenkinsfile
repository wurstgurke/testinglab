pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v //c/temp:/root/.m2'
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
                    emailext(
                        to: 'andreas.berrou@hec.de',
                        subject: 'test',
                        body: 'Hello World!'
                    )
                }
             }
        }
    }
}
