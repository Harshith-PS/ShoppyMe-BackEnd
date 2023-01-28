
pipeline {
	agent any
	tools {
    maven 'maven-3.6.3' 
  }
	stages {
		stage('One') {
			steps {
				echo 'Pipeline to build Java Application'
			}
		}
		
		stage('Two') {
			steps {
				echo 'Do you want to proceed?'
			}
		}
		
		stage ('Build') {
    git url: 'https://github.com/Harshith-PS/ShoppyMe-BackEnd.git'
    withMaven {
      sh "mvn clean install"
    } // withMaven will discover the generated Maven artifacts, JUnit Surefire & FailSafe reports and FindBugs reports
  }
        
        stage('Test') {
		steps {
				echo 'Stage Test'
			}
           
        }
        
        stage('Check') {
		steps {
				echo 'Stage Check'
			}
            
        }      
		
		stage('Five') {
			steps {
				echo 'Finished'
			}
		}		
	}
}
