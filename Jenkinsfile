pipeline {
    agent {
            node {
                label 'docker'
            }
        }
        tools {
            maven 'maven-3-5.2'
            jdk 'jdk10u1'
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
                         cd projects
                         cd jcs
                         mvn test
                     '''
                 }
            }
            post {
               always {
                       cucumber(
                           buildStatus: 'UNSTABLE',
                           jsonReportDirectory: '/opt.ram/jenkins/workspace/b4u/Nightly Tests/projects/jcs/target/',
                           fileIncludePattern: 'cucumber-reports/cucumber.json',
                           trendsLimit: 10,
                           classifications: [
                               [
                                   'key': 'Browser',
                                   'value': 'Chrome'
                               ]
                           ]
                       )
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
                          from: 'b4u-support@neusta-consulting.de',
                          to: 'b4u-support@neusta-consulting.de',
                          replyTo: 'b4u-support@neusta-consulting.de',
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
