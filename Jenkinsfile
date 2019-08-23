pipeline {
    agent any
    stages {
        stage('Checkout - Laserway') {
           	steps {
	               echo 'Clonar Repositório Laserway'
	           }
        }
        
        stage('QA - Analise Estática') {
         	steps {
	             echo 'Análise Estática'
	         }  
        }
        
        stage('Unit Testing') {
           steps {
               echo 'Testes Unitários'
           }
        }
        
        stage('Deploy') {
           steps {
               echo 'Deploy Application'
           }
        }
        
        stage('Preparar Ambiente') {
           	steps {
	               echo 'Preparação do Ambiente'
	           }
        }
        
        stage('Instalação do Software') {
           steps {
               echo 'Instalar Software'
           }
        }
        
        stage('Licenciar o Software') {
           	steps {
	               echo 'Licenciar'
	           }
        }
        
        stage('QA - Functional Test') {
	        steps {
	               echo 'Funcionais Testes'
	           }   
        }
        stage('QA - Smoke Test') {
           	steps {
	               echo 'Smoke Test'
	           }
        }
        
	}
}