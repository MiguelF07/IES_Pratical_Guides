# Lab 1
IES Individual Classes Portfolio - Lab 1



https://mkyong.com/java/how-to-set-java_home-environment-variable-on-mac-os-x/
This link has information on how to set the $JAVA_HOME variable.

<b>What is a Maven Archetype? </b>

Archetype is a Maven project templating toolkit. Archetypes are used to get the users up and running as quickly as possible by providing a sample project that demonstrates many of the features of Maven.

<b>Naming Conventions for *groupID* and *artifactID*</b>

https://maven.apache.org/guides/mini/guide-naming-conventions.html

**A little summary of what is Maven** ⬇️

Maven helps in project management. This tool helps in building and documenting the project.
Maven is a build tool, and build tools are needed for the following processes:

* Generating source code
* Generating documentation from the source code
* Compiling of source code
* Packaging of the compiled codes into JAR files
* Installing the packaged code in a local repository, server or central repository

Maven is based on the Project Object Model (POM).

**What is POM?**

POM is an XML file that has all the information regarding project and configuration details. It also includes the plugins used by Maven in the project.

To add a dependency to POM we can install it on our IDE or go to an official repository and place the code between the <dependency> marker.

So, to sum up, Mavel is used to **build and manage any Java-based project.**

**Why do we need Maven?**

Maven allows us to:

* Get the right JAR files for each project as there may be different versions of separate packages.
* Download dependencies from mvnrepository.com. We no longer need to download the dependencies visiting their official website.
* Create the **right project structure**, which is essential for execution.
* Build and deploy the project to make it work..
* Easy access to all the required information.

To confirm our Maven version, we can use this command:

```bash
mvn --version
```

<h3>How are Maven projects structured?</h3>

On the project directory we have a pom.xml, that was explained before.
We can also find two **folders**:

* src: has all the source code of the project
* target: where the output is stored

To clean our project, in order to deliver it, we can use the following command:

```bash
mvn clean
```



<h2>GIT</h2>

To initiate a git project, we can travel to our destination folder and use the command:

```bash
git init
```

Then we can add the link ou our upstream:

```bash
git remote add origin *OUR LINK*
```

Then, to add and commit our changes:

```bash
git add .  #To stage the files to be commited
git commit -m "commit message"  #To commit locally
git push -u origin main  #To push to our repository
```



<h2>Docker</h2>

Docker is a SO-level virtualization software that runs over our SO. It allow us to build applications in containers (which simplifies the development and execution process).

A container is a process in execution that is isolated from other containers and even from the host. Running a container launches the application with private resources, securely isolated from the rest of the machine.

A **Docker Image** has it's own software, libraries, etc...

A **Dockerfile** describes how the file system works.

When we create a Dockerfile we're able to build the first Docker Image. When building, the instructions on the Dockerfile will be executed.
We can do that with these commands:

```bash
cd *source directory*
docker --build --tag <tagName> .
```

I created a Dockerfile on the project I downloaded from the Docker tutorial.
The file is named Dockerfile and has the following content:

```dockerfile
# syntax=docker/dockerfile:1
FROM node:12-alpine
RUN apk add --no-cache python g++ make
WORKDIR /app
COPY . .
RUN yarn install --production
CMD ["node", "src/index.js"]
```
Then I built the container image navegating to the app folder and using the following command:

```bash
docker build -t getting-started .
```

To run the image:

```bash
docker run -dp 3000:3000 getting-started
```



<h4>Portainer</h4>

Portainer is a Docker managing app that we can access from our browser.

To install portainer I used:

```bash
docker volume create portainer_data
docker run -d -p 8000:8000 -p 9443:9443 --name portainer --restart=always -v /var/run/docker.sock:/var/run/docker.sock -v portainer_data:/data portainer/portainer-ce:latest
```

These were the commands that were available on the "Install Portainer CE" [page](https://docs.portainer.io/v/ce-2.9/start/install/server/docker/wsl) .

So, to access Portainer, I accessed: https://localhost:9443

To login to **Portainer**:
user: **admin**
pass: **adminadmin**



<h4>PostgresSQL</h4>

To run Postgres we will use a custom made image.

To do that we use:

```bash
docker run --name pg-docker -e POSTGRES_PASSWORD=docker -e PGDATA=/tmp -d -p 5433:5432 -v ${PWD}:/var/lib/postgresql/data postgres:11
```

**-p 5432:5432** associates the port of the host to the port of the image

In my case, I replaced the ${PWD} with the path to a folder in the repository. So, the full command was:

```bash
docker run --name pg-docker -e POSTGRES_PASSWORD=docker -e PGDATA=/tmp -d -p 5432:5432 -v /Users/miguelferreira/Desktop/Universidade/3º\ Ano/IES/IES_98599/lab1/database_server:/var/lib/postgresql/data postgres:11
```



<h2>The Get started with Docker Compose Tutorial</h2>

**What is Docker Compose?**
Compose is **a tool for defining and running multi-container Docker applications**.

The tutorial can be accessed [here](https://docs.docker.com/compose/gettingstarted/).

Structure of the files created:

* app.py -> Has the app code
* requirements.txt -> Has the requirements that need to be installed for the app to run
* Dockerfile -> File to create a Docker Image that contains all the dependencies the Python application requires

Contents of the Dockerfile:

```dockerfile
# syntax=docker/dockerfile:1
FROM python:3.7-alpine	#Build an image starting with the Python 3.7 		image.
WORKDIR /code #Set the working directory to /code.
ENV FLASK_APP=app.py	#Set environment variables used by the flask command.
ENV FLASK_RUN_HOST=0.0.0.0	#Set environment variables used by the flask command.
RUN apk add --no-cache gcc musl-dev linux-headers #Install gcc and other dependencies
COPY requirements.txt requirements.txt #Copy requirements.txt and install the Python dependencies.
RUN pip install -r requirements.txt
EXPOSE 5000	#Add metadata to the image to describe that the container is listening on port 5000
COPY . .	#Copy the current directory . in the project to the workdir . in the image.
CMD ["flask", "run"]	#Set the default command for the container to flask run.
```



The Composer file defines two services: `web` and `redis`.

To start the application, we can use this command from the project directory:

```bash
docker-compose up
```

To run the app we can access http://localhost:5000

If we add 

```dockerfile
volumes:
  - .:/code
environment:
  FLASK_ENV: development
```

to the docker-compose.pml we will be able to modify the code on the fly, without having to rebuild the image.
The `volumes` key mounts the project directory on the host to `/code` inside the container.
The `environment` key sets the `FLASK_ENV` environment variable, which tells `flask run` to run in development mode and reload the code on change.

Some other commands:

```bash
docker-compose run web env #to see what environment variables are available to the web service
docker-compose down --volumes #this command brings everything down, removing the containers totally and removing the data volume used by the Redis container
```



<h1>To Sum up...</h1>

This pratical guide was focused on Team work.
We focused on Git, Maven, Docker, etc...

Here is a little scheme that illustrates how these three main technologies interact with each other:

--> GIT: Source Code Manager
|   ^
|   |
|   -- Maven: Build anywhere
|
-- Docker: Run Anywhere