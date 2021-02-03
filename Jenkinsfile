pipeline {
    agent {
        docker {
            image 'maven:3.5.0'
        }
    }

    stage('Check environment') {
        sh 'mvn -v'
        sh 'docker -v'
        sh 'docker-compose -v'
        sh "docker ps -q | xargs -r docker stop"
        sh 'mvn clean'
        sleep 30
    }

    stage('packaging') {
        sh "mvn deploy -Pprod -DskipTests=true -DskipLiquibase=true"
    }

    stage('Push image') {
        steps {
            sh "mvn docker:build -DpushImage"
        }
    }

    stage('Finish') {
        sh "docker ps -q | xargs -r docker stop"
        sh "mvn docker:removeImage"
    }

}
