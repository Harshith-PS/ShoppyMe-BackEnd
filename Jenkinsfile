
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
           steps {
				sh 'ls'
			}
        }
        
        stage('Test') {
		steps {
				input('Do you want to proceed?')
			}
           
        }
        
        stage('Check') {
		steps {
				input('Do you want to proceed?')
			}
            
        }      
		
		stage('Five') {
			steps {
				echo 'Finished'
			}
		}		
	}
}
