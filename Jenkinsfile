pipeline {
    stages {
        stage('Test') {
        agent {
                docker {
                    image 'maven:3-alpine'
                    args '-v //c/temp:/root/.m2'
                }
            }
             steps{
                sh "mvn test"
             }
             post {
                always {
                    publishHTML(
                       target: [
                       allowMissing: true,
                       alwaysLinkToLastBuild: false,
                       keepAll: true,
                       reportDir: './cucumber',
                       reportFiles: 'overview-features.html',
                       reportName: 'Testresults'
                    ])
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
    }
}
