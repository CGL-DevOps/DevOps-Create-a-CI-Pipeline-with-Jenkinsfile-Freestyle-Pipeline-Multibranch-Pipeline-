### Install-Jenkins-on-DigitalOcean

### Technologies used:

Jenkins, Docker, DigitalOcean, Linux

### Project Description:

1- Create an Ubuntu server on DigitalOcean.

2- Set up and run Jenkins as Docker container.

3- Initialize Jenkins.

### Installation instructions:

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
