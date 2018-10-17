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
        }
    }
    agent {
            docker {
                image 'selenium/node-chrome-debug'
            }
    }
    stages {
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'cucumber', reportFiles: 'index.html', reportName: 'Test Results', reportTitles: ''])
                }
                always {
                    //generate cucumber reports
                    cucumber '**/*.json'
                }
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
    }
}