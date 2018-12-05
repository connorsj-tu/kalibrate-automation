pipeline {
    agent {
        node {
            label 'dotnet'
        }
    }
    stages {
        stage('Build') {
            steps {
                powershell 'docker build -t $env:ENV_IMAGE_NAME -f Dockerfile.environment --no-cache=true .'
                powershell 'docker build -t $env:UI_IMAGE_NAME`:$env:GIT_COMMIT .'
            }
        }
        stage('Push') {
            when {
                branch 'develop'
            }
            steps {
                powershell 'docker login $env:REGISTRY -u $env:REGISTRY_CREDENTIALS_USR -p $env:REGISTRY_CREDENTIALS_PSW'
                powershell 'docker tag $env:ENV_IMAGE_NAME $env:REGISTRY/$env:ENV_IMAGE_NAME'
                powershell 'docker push $env:REGISTRY/$env:ENV_IMAGE_NAME'
                powershell 'docker tag $env:UI_IMAGE_NAME`:$env:GIT_COMMIT $env:REGISTRY/$env:UI_IMAGE_NAME'
                powershell 'docker push $env:REGISTRY/$env:UI_IMAGE_NAME'
            }
            environment {
                REGISTRY_CREDENTIALS = credentials('c1b9f41b-62da-4844-a865-a0929d067805')
                REGISTRY = 'nexus3.kalibrate.com:18079'
            }
        }
    }
    environment {
        ENV_IMAGE_NAME = 'kalibrate/automation-env'
        UI_IMAGE_NAME = 'kalibrate/ui-auto'
    }
}
