pipeline {
    agent none
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Start selenium hub') {
            steps {
                sh  '''
                    chmod 775 ./scripts/start-hub.sh
                    chmod 775 ./scripts/stop-hub.sh
                    ./scripts/stop-hub.sh
                    ./scripts/start-hub.sh
                 '''
            }
        }
        stage('Run tests with maven') {
            steps {
                 ansiColor('xterm') {
                     sh '''
                         mvn test
                     '''
                 }
            }
            post {
               always {
                    publishHTML(target: [
                         allowMissing: true,
                         alwaysLinkToLastBuild: false,
                         keepAll: true,
                         reportDir: './cucumber/build/html/cucumber-html-reports',
                         reportFiles: 'overview-features.html',
                         reportName: 'Chrome Run'
                    ])
               }
               failure {
                    emailext(
                        to: 'andreas@berrou.de',
                        subject: 'test',
                        from: 'andreas@berrou.de',
                        body: '''
                              <p>There are Tests failing!</p>
                              <p>Check reports and console output at &QUOT;<a href='$BUILD_URL'>$JOB_NAME [$BUILD_NUMBER]</a>&QUOT;</p>
                              ''',
                        attachmentsPattern: './cucumber/build/html/cucumber-html-reports/overview-features.html'
                    )
               }
            }
        }
        stage('Stop selenium hub') {
            steps {
                sh  '''
                   sh './cucumber/scripts/stop-hub.sh'
                 '''
            }
        } 
    }
}
