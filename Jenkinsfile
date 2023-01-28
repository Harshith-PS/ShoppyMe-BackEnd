
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
		
		stage('Build') {
           
        }
        
        stage('Test') {
           
        }
        
        stage('Check') {
            
        }      
		
		stage('Five') {
			steps {
				echo 'Finished'
			}
		}		
	}
}
