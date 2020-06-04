pipeline{
    agent {
        docker {
            image 'maven:3.6-jdk-11'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages{
        stage('Build') {
            
            steps{
                sh "mvn -v"
                sh "mvn clean compile"
            }
                        
        }
        
        stage('Test') {
            steps {
                sh "mvn test"
            }
        }
        
        stage('Analyze'){
            steps{
                sh "echo analyze" 
            }
        }
    }
    
}
