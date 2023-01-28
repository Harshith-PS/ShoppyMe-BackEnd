
pipeline {
	agent any
	stages {
		stage('One') {
			steps {
				echo 'Pipeline to build Java Application'
			}
		}
		
		stage('Two') {
			steps {
				input('Do you want to proceed?')
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
