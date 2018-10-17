pipeline {
    stages {
        stage('Start Maven') {
             agent {
                docker {
                    image 'maven:3-alpine'
                    args '-v /root/.m2:/root/.m2'
                }
             }
        }
        stage('Start Selenium Hub') {
            agent {
                docker {
                   image 'selenium/node-chrome-debug'
                   args '-p 5900:5900 --link hub:hub'
                }
             }
        }
        stage('Add Node to Hub') {
            agent {
                docker {
                  image 'selenium/node-chrome-debug'
                  args '-p 5900:5900 --link hub:hub'
                }
             }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
    }
}
