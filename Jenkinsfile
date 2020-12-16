
projectname='seleniumTest'

pipeline {
    agent any

    tools {
        maven "Maven"
    }

    stages {
        stage('Build') {
            steps {
                git 'https://github.com/hdsgrewal/seleniumTests.git'
            }
        }
        stage('test') {
              steps {
                   sh "mvn clean test"
              }
        }
    }
}
