pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
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
                    cucumber '**/*.json'
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
    }
}
