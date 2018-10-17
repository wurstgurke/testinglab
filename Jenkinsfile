pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    agent {
        docker {
            image 'selenium/hub'
            args '-p 5801:5801 --name hub'
        }
    }
    agent {
        docker {
            image 'selenium/node-chrome-debug'
            args '-p 5900:5900 --link hub:hub'
        }
    }
    stages {
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
