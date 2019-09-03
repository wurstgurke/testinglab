pipeline {
  agent {
    docker {
      image 'cypress/base:10'
    }
  }
  stages {
    stage('build') {
      steps {
        echo "Running build ${env.BUILD_ID} on ${env.JENKINS_URL}"
        sh 'npm ci'
        sh 'npm run cy:verify'
      }
    }

    stage('run tests') {
      environment {
        CYPRESS_RECORD_KEY = credentials('cypress-example-kitchensink-record-key')
        CYPRESS_trashAssetsBeforeRuns = 'false'
      }
      steps {
        echo "Running build ${env.BUILD_ID}"
        sh "npm run e2e"
      }
    }
  }
}
