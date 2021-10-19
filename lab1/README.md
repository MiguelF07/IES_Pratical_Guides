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

`mvn --version`

<h3>How are Maven projects structured?</h3>

On the project directory we have a pom.xml, that was explained before.
We can also find two **folders**:

* src: has all the source code of the project
* target: where the output is stored

To clean our project, in order to deliver it, we can use the following command:

`mvn clean`

<h2>GIT</h2>

To initiate a git project, we can travel to our destination folder and use the command:

`git init`

Then we can add the link ou our upstream:

`git remote add origin *OUR LINK*`

Then, to add and commit our changes:

`git add .` -> To stage the files to be commited

`git commit -m "commit message"`to commit locally

`git push -u origin main` -> to push to our repository



<h2>Docker</h2>

Docker is a SO-level virtualization software that runs over our SO. It allow us to build applications in containers (which simplifies the development and execution process).

A container is a process in execution that is isolated from other containers and even from the host. Running a container launches the application with private resources, securely isolated from the rest of the machine.

A **Docker Image** has it's own software, libraries, etc...







