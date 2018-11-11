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
                    publishHTML(target: [
                           allowMissing: true,
                           alwaysLinkToLastBuild: false,
                           keepAll: true,
                           reportDir: './target/cucumber',
                           reportFiles: 'index.html',
                           reportName: 'Testresults'
                      ])
                    emailext(
                        to: 'andreas@berrou.de',
                        subject: 'test',
                        from: 'andreas@berrou.de',
                        body: '''
                        <p>Monitoring job failed</p>
                        <p>Check reports and console output at &QUOT;<a href='www.google.de'>test</a>&QUOT;</p>

                        ''',
                        attachmentsPattern: './target/cucumber/index.html'
                    )
                }
             }
        }
    }
}
