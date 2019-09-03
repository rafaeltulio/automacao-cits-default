def dbRootPass = ''
def idUser = ''
def idPasss = ''
def creatorUser = ''
def creatorPass = ''
def brokerUser = ''
def brokerPass = ''
def appUser = ''
def appPass = ''
def version = '2.0.2'
def revisionBack = ''
def revisionFront = ''
pipeline {
	agent any  
	environment {
        hostInstall= '10.0.3.158'
    }
	stages {
        stage('DVCS - Back End') {
            steps {
                checkout([$class: 'GitSCM',
                          //branches: [[name: "${params.TAG}"]],
                          branches: [[name: "develop"]],
                          doGenerateSubmoduleConfigurations: false,
                          extensions: [],
                          gitTool: 'Default',
                          submoduleCfg: [],
                          userRemoteConfigs: [[url: 'ssh://jenkins.cits@10.80.42.118:29418/BR/DES/Software/ISPManager/ISPManager-Back']]
                        ])
                script{
                    revisionBack = sh(returnStdout: true, script: "git log -n 1 --pretty=format:'%h'").trim()
                    version = version + "-" + revisionBack
                }
            }
        }
        stage('DVCS - Front End') {
            steps {
                checkout([$class: 'GitSCM',
                          //branches: [[name: "${params.TAG}"]],
                          branches: [[name: "develop"]],
                          doGenerateSubmoduleConfigurations: false,
                          extensions: [[$class: 'RelativeTargetDirectory', 
                                        relativeTargetDir: 'fkcp-commons/fkcp-frontend-service/src/main/front']],
                          gitTool: 'Default',
                          submoduleCfg: [],
                          userRemoteConfigs: [[url: 'ssh://jenkins.cits@10.80.42.118:29418/BR/DES/Software/ISPManager/ISPManager-Front']]
                        ])
                    script{
                        revisionFront = sh(returnStdout: true, script: 'cd fkcp-commons/fkcp-frontend-service/src/main/front/; git log -n 1 --pretty=format:\'%h\'')
                        version = version + "." +revisionFront
                    }
            }
        }
		stage('Execução dos Testes - TDD') { 
			steps {
				sh 'echo Execução dos Testes unitários'
				sh 'mvn clean -fn test'
			}
			post{
			    always {
			        sh 'echo gerando os arquivos de relatórios'
			        junit '**/surefire-reports/*.xml, **/*.xml'
			    }
			}
		}
		stage('Compilação e Deploy') { 
			steps {
                sh 'echo Iniciando compilação'
			    sh 'mvn versions:set -DnewVersion=' + version
			    sh 'mvn install -DskipTests'
			}
		}
		stage('QA - Análise Estática') {
			steps {
			    sh 'echo Iniciando Análise Estática'
				sh 'sonar-scanner -Dsonar.projectKey=ISPManager-Back -Dsonar.sources=. -Dsonar.java.binaries=../'	
			}
		}
		stage('Preparação do Ambiente') {
			steps {
			    sh 'echo Remoção dos containers e exclusão dos arquivos de instalação anterior'
				script{
				    print "${version}"
				    sh 'echo "${version}"'
				    sh 'echo Preparação do ambiente para instalação do software'
					sh 'ssh root@${hostInstall} "echo "123456" | sudo -S /home/isp/bin/clean.sh"'
					sh 'ssh root@${hostInstall} "cd /home/isp/bin/; echo "123456" | sudo -S rm -rf /var/lib/furukawalatam data/ sql_migration/ isp.tgz"'
				    sh 'ssh root@${hostInstall} "cd /home/isp/bin/; echo "123456" | sudo -S rm -rf furukawalatam.consciusmanager*"'
					sh 'scp -r /var/lib/jenkins/jobs/Furukawa\\ -\\ Conscius\\ Manager/workspace/fkcp-docker/target/furukawalatam.consciusmanager.' + "${version}" + '-install.sh root@${hostInstall}:/home/isp/bin'
					sh 'echo "Transferindo pacote de instalação..."; sleep 60'
					sh 'ssh root@${hostInstall} "cd /home/isp/bin; echo "123456" | sudo -S dd if=furukawalatam.consciusmanager.' + "${version}" + '-install.sh of=isp.tgz bs=1024 skip=2"'
					sh 'ssh root@${hostInstall} "cd /home/isp/bin; tar -xvf isp.tgz"'
					sh 'ssh root@${hostInstall} "rm /home/isp/bin/furukawalatam.consciusmanager.' + "${version}" + '-install.sh"'
					sh 'ssh root@${hostInstall} \'sed -i -e \'s/\'"-d mariadb"\'/\'"-p 3306:3306 -d mariadb"\'/g\' /home/isp/bin/furukawalatam.consciusmanager.' + "${version}" + '.sh\''
					sh 'ssh root@${hostInstall} \'sed -i -e \'s/\'"    assert_files_integrity"\'/\'"#    assert_files_integrity"\'/g\' /home/isp/bin/furukawalatam.consciusmanager.' + "${version}" + '.sh\''
				}
			}
		}
		stage('Instalação do software'){
			steps{
			    sh 'echo iniciando processo de instalação'
				sh 'ssh root@${hostInstall} \'cd /home/isp/bin; echo "123456" | sudo -S /home/isp/bin/furukawalatam.consciusmanager.' + "${version}" + '.sh > arquivo.txt\''
				sh 'ssh root@${hostInstall} \'scp /home/isp/bin/arquivo.txt root@10.0.0.76:"/var/lib/jenkins/jobs/Furukawa\\ -\\ Conscius\\ Manager/workspace"\''
			}
		}
		stage('Licenciar o software'){
			steps{
			    sh 'echo iniciando licenciamento'
				sh 'ssh root@${hostInstall} \'cd /home/isp/bin; echo "123456" | sudo -S docker cp license.txt.asc fkcp-isp-service:/var/license\''
			    sh 'ssh root@${hostInstall} \'echo "123456" | sudo -S docker restart fkcp-isp-service\''
				sh 'echo \'Finalizando procedimento para licenciamento, reiniciando serviços... Aguarde...\'; sleep 120'
			}
		}
		stage('QA - Testes ABDD'){ 
			steps{
				script{				
						if(fileExists("isp-test-ui-feature"))
						{
						    sh 'echo atualizando scripts de testes automatizados...'
							sh 'cd isp-test-ui-feature/; git pull'
						} else {
						    sh 'echo clonando projeto de scripts para testes automatizados...'
							sh 'git clone -b "master" git@git.cits.br:furukawa/isp-test-ui-feature.git'
						}
				}
				sh 'cd isp-test-ui-feature/;  mvn clean test -DrunHost="10.0.3.154:4444" -Dcucumber.options="-t \'@Login, @OnuServicosHomologados, @SipServer, @Vlan, @Internet, @Telefonia, @PacoteDeServiço, @ValidarDependências, @Olt, @Usuario, @CentralDeAtividades\'"'
			}
			post{
			    always{
			        junit '**/surefire-reports/*.xml, **/*.xml, isp-test-ui-feature/target/*.xml'
			        cucumber buildStatus: 'UNSTABLE',
                        failedFeaturesNumber: 1,
                        failedScenariosNumber: 1,
                        skippedStepsNumber: 1,
                        failedStepsNumber: 1,
                        classifications: [
                            [key: 'Commit', value: '<a href="${GIT_URL_N}">${GIT_COMMIT}</a>'],
                            [key: 'Submitter', value: '${GIT_AUTHOR_NAME}']
                        ],
                        fileIncludePattern: 'isp-test-ui-feature/target/cucumber-pt.json',
                        sortingMethod: 'ALPHABETICAL',
                        trendsLimit: 100
			    }
			}
		}
	}	 
	post {
        always {
            sh 'echo finalizando o job automatizado...'
            script{
                dbRootPass = sh(returnStdout: true, script: 'sed -n \'/^** Database root password:/p\' arquivo.txt')
                idUser = sh(returnStdout: true, script: 'sed -n \'/^** Identity manager user:/p\' arquivo.txt')
                idPasss = sh(returnStdout: true, script: 'sed -n \'/^** Identity manager password:/p\' arquivo.txt')
                creatorUser = sh(returnStdout: true, script: 'sed -n \'/^** User creator:/p\' arquivo.txt')
                creatorPass = sh(returnStdout: true, script: 'sed -n \'/^** User creator password:/p\' arquivo.txt')
                brokerUser = sh(returnStdout: true, script: 'sed -n \'/^** Broker user:/p\' arquivo.txt')
                brokerPass = sh(returnStdout: true, script: 'sed -n \'/^** Broker password:/p\' arquivo.txt')
                appUser = sh(returnStdout: true, script: 'sed -n \'/^** Application user:/p\' arquivo.txt')
                appPass = sh(returnStdout: true, script: 'sed -n \'/^** Application password:/p\' arquivo.txt')
            }
            archiveArtifacts 'fkcp-docker/target/furukawalatam.consciusmanager.' + "${version}" + '-install.sh, isp-test-ui-feature/target/docs/*.pdf, isp-test-ui-feature/target/docs/*.html, isp-test-ui-feature/target/*.json, isp-test-ui-feature/target/*.xml'
            cucumber fileIncludePattern: 'isp-test-ui-feature/target/cucumber-pt.json', sortingMethod: 'ALPHABETICAL'
            publishHTML([allowMissing: true, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'isp-test-ui-feature/target/cucumber', reportFiles: 'index.html', reportName: 'Performance Test Report', reportTitles: ''])
            
            emailext attachmentsPattern: 'isp-test-ui-feature/target/docs/*.pdf, arquivo.txt, ${env.LOG_FILE}, arquivo.txt',      
            body: "IC - Furukawa Conscius Manager: \n ${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL} \n Análise Estática: http://10.0.0.76:9000/dashboard?id=ISPManager-Back \n\n ### Senhas ### \n ${dbRootPass} ${idUser} ${idPasss} ${creatorUser} ${creatorPass} ${brokerUser} ${brokerPass} ${appUser} ${appPass}",
            subject: currentBuild.currentResult + " : " + env.JOB_NAME + " (Test Automation)",
            to: 'antonio.grossi@cits.br'
        }
    }
}