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
                }
                failure {
                    emailext attachmentsPattern: '**/report.html', body: 'Find attachments', subject: 'test', to: 'andreas.berrou@hec.de'
                }

             }
        }
    }
}
