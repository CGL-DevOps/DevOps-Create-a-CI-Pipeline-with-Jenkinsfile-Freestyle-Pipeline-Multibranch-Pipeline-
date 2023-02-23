def buildJar() {
    echo "building the application..."
    sh 'mvn package'
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'Docker-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t jason8746/java-maven-app:1.0.0-pipeline .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push jason8746/java-maven-app:1.0.0-pipeline'
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

def masterBranch(){
    BRANCH_NAME == "master"
}

return this
