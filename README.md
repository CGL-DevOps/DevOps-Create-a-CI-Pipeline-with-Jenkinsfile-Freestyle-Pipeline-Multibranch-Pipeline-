### Create a CI Pipeline with Jenkinsfile (Freestyle, Pipeline, Multibranch Pipeline)

CI Pipeline for a Java Maven application to build and push to the repository

### Technologies used:

Jenkins, Docker, Linux, Git, Java, Maven

### Project Description:

1- Create an Ubuntu server on DigitalOcean.

2- Set up and run Jenkins as Docker container.

3- Initialize Jenkins.

4- Install Build Tools (Maven, Node) in Jenkins.

5- Make Docker available on Jenkins server.

6- Create Jenkins credentials for a git repository.

7- Create different Jenkins job types (Freestyle, Pipeline, Multibranch pipeline) for the Java Maven project with Jenkinsfile to:
a. Connect to the application’s git repository
b. Build Jar
c. Build Docker Image
d. Push to private DockerHub repository

### Installation instructions:

### Part 1: Create Droplet server, install Docker and run Jenkins as a container

###### Step 1: Create a Droplet server on DigitalOcean.

1- Create a Droplet server in Singapore region
![image](image/Screenshot%202023-02-23%20at%203.59.07%20pm.png?raw=true)

2-Configure the droplet server with firewall
![image](image/Screenshot%202023-02-23%20at%204.02.17%20pm.png?raw=true)

###### Step 2: Login into Droplet server with root user

```
ssh root@68.183.224.228
```

###### Step 3: Install Docker on Droplet server

1-Update apt

```
apt update
```

2-install docker and docker-compose

```
apt  install docker.io
```

```
apt  install docker-compose
``
3-check docker and docker compose version
```

docker --version

```

```

docker-compose --version

```

```

###### Step 4: Install and run Jenkins as a Docker container

```
docker pull jenkins/jenkins:lts
```

```
docker run -p 8080:8080 -p 50000:50000 -v jenkins_home:/var/jenkins_home -d --name jenkins jenkins/jenkins:lts
```

```
docker ps
```

![image](image/Screenshot%202023-02-23%20at%204.15.31%20pm.png?raw=true)
![image](image/Screenshot%202023-02-23%20at%204.17.12%20pm.png?raw=true)

#Update the firewall with port number 8080
![image](image/Screenshot%202023-02-23%20at%204.23.25%20pm.png?raw=true)

###### Step 5: Initialize Jenkins

1- Login in jenkins with admin user and initalAdminPassword

```
cat /var/jenkins_home/secrets/initialAdminPassword
```

![image](image/Screenshot%202023-02-23%20at%204.20.37%20pm.png?raw=true)

```
68.183.224.228:8080
```

![image](image/Screenshot%202023-02-23%20at%206.06.10%20pm.png?raw=true)

### Part 2: Bind mount Docker into Jenkins container

#Stop the current running Jenkins container

```
docker stop 54e0af036b0c
```

```
docker rm 54e0af036b0c
```

#Re-run Jenkins container and bind mount Docker to Jenkins container

```
docker run -p 8080:8080 -p 50000:50000 -v jenkins_home:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock -d --name jenkins jenkins/jenkins:lts
```

#enter into jenkins container via jenkins root user and install docker

```
docker exec -it -u 0 105713d34a44 bash
```

#Install docker inside Jenkins server

```
dockerinstall && chmod 777 dockerinstall
```

```
bash dockerinstall
```

#change the /var/run/docker.sock privilege to read and write for all users

```
sudo chmod 666 /var/run/docker.sock
```

```
exit
```

```
docker exec -it 105713d34a44 bash
```

### Part 3: Install Maven and node in Jenkins

###### Step 1: Install and configure Maven in within Manage Plugin and Global Tool Configuration in Jenkins UI

![image](image/Screenshot%202023-02-23%20at%206.36.35%20pm.png?raw=true)

###### Step 2: Install and configure Node by command line interface

#Install Node directly inside Jenkins container

```
docker exec -it -u 0 540ec53fe543 bash
```

#Check with Linux distribution version container is running

```
cat /etc/issue
```

![image](image/Screenshot%202023-02-23%20at%206.41.03%20pm.png?raw=true)

#Install Node

```
apt update
```

```
apt install curl
```

```
cd ~
```

```
curl -sL https://deb.nodesource.com/setup_18.x -o nodesource_setup.sh
```

```
bash nodesource_setup.sh
apt install nodejs
node -v
npm -v
```

![image](image/Screenshot%202023-02-23%20at%206.52.29%20pm.png?raw=true)

### Part 4: Create Jenkins Credentials for a git repository

1- Click Manage Plugins

2- Click Manage Credentials

3- Click Add Credentials

4- Create Jenkins Credentials as Global Scope with Github Username and Password
![image](image/Screenshot%202023-02-23%20at%206.58.58%20pm.png?raw=true)

### Part 5: Create a Jenkins freestyle job and connect with git repo

1- Create a Freestyle jenkins job
![image](image/Screenshot%202023-02-23%20at%207.25.12%20pm.png?raw=true)

![image](image/Screenshot%202023-02-23%20at%208.00.01%20pm.png?raw=true)

![image](image/Screenshot%202023-02-23%20at%207.59.20%20pm.png?raw=true)

2- Check out a Git repo and build
#Connect Jenkins with a github repository with Jenkins credentials
![image](image/Screenshot%202023-02-23%20at%208.21.36%20pm.png?raw=true)

![image](image/Screenshot%202023-02-23%20at%208.23.47%20pm.png?raw=true)

![image](image/Screenshot%202023-02-23%20at%208.25.21%20pm.png?raw=true)

### Part 6: Create a Jenkins pipeline and connect with git repo

1-Create a pipeline in Jenkins

![image](image/Screenshot%202023-02-23%20at%208.39.12%20pm.png?raw=true)

2-Create a docker credentials for jenkins
![image](image/Screenshot%202023-02-23%20at%208.40.24%20pm.png?raw=true)

3-Create Jenkinsfile and external script.groovy

![image](image/Screenshot%202023-02-23%20at%208.47.50%20pm.png?raw=true)

![image](image/Screenshot%202023-02-23%20at%208.47.39%20pm.png?raw=true)

4- Build on Jenkins

![image](image/Screenshot%202023-02-23%20at%209.09.44%20pm.png?raw=true)

![image](image/Screenshot%202023-02-23%20at%209.10.03%20pm.png?raw=true)

### Part 7: Create a multi-pipeline and connect with git repo

1- Create a multi-pipeline
![image](image/Screenshot%202023-02-23%20at%209.45.30%20pm.png?raw=true)

![image](image/Screenshot%202023-02-23%20at%209.54.13%20pm.png?raw=true)
2-
