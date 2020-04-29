pipeline {
    agent any
    tools {
        maven 'MAVEN_HOME'
    }
    stages{
        stage('Build'){
            steps{
                 sh script: 'mvn clean package'
            }
        }
        stage('Upload War To Nexus'){
            steps{
                 nexusArtifactUploader artifacts: [
                     [
                         artifactId: 'maven-releases',
                         classifier: '',
                         file: 'target/simple-doiab-app-1.0.0.war',
                         type: 'war'
                    ]
                ],
                credentialsId: 'nexus3',
                groupId: 'in.javahome',
                nexusUrl: '172.18.0.9:8081',
                nexusVersion: 'nexus3',
                protocol: 'http',
                repository: 'maven-releases',
                version: '1.0.0'
            }
        }
    }
}
