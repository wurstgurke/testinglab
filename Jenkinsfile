pipeline {
    agent {
            docker {
                image 'maven:3-alpine'
                args '-v $HOME/.m2:/root/.m2'
            }
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
                    chmod 775 ./projects/jcs/scripts/start-hub.sh
                    chmod 775 ./projects/jcs/scripts/stop-hub.sh
                    ./projects/jcs/scripts/stop-hub.sh
                    ./projects/jcs/scripts/start-hub.sh
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
                           results = readFile "${env.WORKSPACE}/projects/jcs/target/surefire-reports/de.neusta.b4u.RunCukesTest.txt"
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
                   sh './projects/jcs/scripts/stop-hub.sh'
                 '''
            }
        }
    }
}
