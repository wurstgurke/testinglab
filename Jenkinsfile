pipeline {
    agent any

      tools{
      maven 'maven 3'
      jdk 'java 8'
      }
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
                         mvn verify -DskipTests
                     '''
                 }
            }
            post {
               always {
                       script {
                           results = readFile "${env.WORKSPACE}/target/surefire-reports/de.neusta.b4u.RunCukesTest.txt"
                           indexOfStartOfResultSummary = results.indexOf("Tests run: ") // length of this string is 11
                           indexOfEndOfResultSummary = results.indexOf(", Time elapsed")
                           if (indexOfStartOfResultSummary == -1 || indexOfEndOfResultSummary == -1) {
                                substr = "Fatal: Error result not found!"
                           } else {
                                substr = results.substring(indexOfStartOfResultSummary + 11, indexOfEndOfResultSummary) // 11 is length of "Tests run: ", see above
                           }
                           substr = results.substring(indexOfStartOfResultSummary + 11, indexOfEndOfResultSummary) // 11 is length of "Tests run: ", see above
                       }
                       mail(
                          from: 'andreas@berrou.de',
                          to: 'andreas@berrou.de',
                          replyTo: 'andreas@berrou.de',
                          subject: "Testruns Staging: ${substr}",
                          body: "The nighly run of the cucumber tests has finished.")
               }

            }
        }
        stage('Stop selenium hub') {
            steps {
                sh  '''
                   sh './scripts/stop-hub.sh'
                 '''
            }
        }
    }
}
