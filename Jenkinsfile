pipeline {
    agent none
    stages {

        stage('Run with Hub') {
            agent {
                label 'linux && docker'
            }
             tools {
                jdk 'JDK1.8'
                maven 'Maven 3.3.9'
            }

            post {
                always {
                    sh './cucumber/scripts/stop-hub.sh'
                }
            }

            stages {
                stage('Checkout') {
                    steps {
                        checkout scm
                    }
                }

                stage('Start Hub') {
                    steps {
                        sh  '''
                            chmod 775 ./scripts/start-hub.sh
                            chmod 775 ./scripts/stop-hub.sh
                            ./scripts/stop-hub.sh
                            ./scripts/start-hub.sh
                         '''
                    }
                }

                stage('Chrome') {
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
            }
        }
    }
}
