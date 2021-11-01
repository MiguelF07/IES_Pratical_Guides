# Lab 2 | P1

Miguel Ferreira - 98599



## Server-side programming with servlets

A Servlet is a Java class that runs at the server, handles (client) requests, processes them, and reply with a response.
*Servlet* is a generic interface and the *HttpServlet* is an extension of that interface (the most used type of Servlets).

### Servlet Containers vs Docker Containers

**Docker Containers** are used to deploy virtualized runtimes, for any kind of services.
**Servlet Containers** provide a runtime to execute server-side web-related Java code (no virtualization).

To execute Apache Tomcat:

```bash
$ cd apache-tomcat/bin
$ chmod u+x startup.sh 
$ chmod u+x catalina.sh 
$ ./startup.sh
```

To check if we installed Apache Tomcat correctly, we can access http://localhost:8080/ and, if everything went correctly, it will show us this message: *If you're seeing this, you've successfully installed Tomcat. Congratulations!*

The Manager app included in Tomcat installation can be used to control the server, including deploying and un-deploying applications we developed. We can access it here: http://localhost:8080/manager

Before using the manager app, we must register at least one role in `conf/tomcat-users.xml`and then restart Tomcat.
In this folder, inside the tomcat-users flag, we must insert this piece of code:

```xml
<role rolename="manager-gui"/>
<role rolename="manager-script"/>
<user username="admin" password="secret" roles="manager-gui, manager-script"/>
```

We have now to create a maven-based web application project. For that, I used this template:

```
archetypeGroupId=org.codehaus.mojo.archetypes
archetypeArtifactId=webapp-javaee7
archetypeVersion=1.1
```

Then we build the project `mvn-build`

In the target folder we have a file named `webapp2_1-1.1.war`. This is our application packaged as a Web Archive.

To deploy the `.war` into the application server we can use the Tomcat management interface to upload and deploy a .war file (http://localhost:8080/manager) or copy the `.war`file to `apache-tomcat/webapps`.

After the app is deployed, it will appear on the Manager App and, if we execute it, it will display a big, bold **Hello World** for us!

https://howtodoinjava.com/java/servlets/complete-java-servlets-tutorial/#webservlet_annotation
Following this tutorial from *Develop Servlet with @WebServlet Annotation* to *Handling Servlet Request and Response*

Servlets are not static web pages; they are dynamic, and that is arguably their biggest strength.


<h3>How to deploy a project to Tomcat Server using VSCode</h3>

We start by installing the *Tomcat for Java* extension on VSCode. Then, we configure a Tomcat Server choosing the folder where *apache-tomcat* is located. Then, we run the command `mvn install`on our terminal for it to generate the most recent version of the application. Then we select the folder with the project name (not the .war file). In my case the folder is named `webapp2_1-1.1`. After selecting the folder, we right-click it and choose *Run in Tomcat Server*. The application will then be deployed and will be stored in the *Tomcat Server*.



<h2>Server-side programming with embedded servers</h2>

To run the web container from within our app, we'll be using an *embedded server*, since it's lifecycle (start,stop) and the deployment of the artifacts are controlled by our application code.

In this section I'll be following a tutorial, suggested by the professor, that can be found in this [link](https://examples.javacodegeeks.com/enterprise-java/jetty/embedded-jetty-server-example/).
In this tutorial we will create a simple embedded jetty server and a servlet and run that servlet on that server.



<h2>Introduction to web apps with a full-featured framework (Spring Boot)</h2>

Spring Boot is a rapid application development platform. It is built on top of Spring Framework.
Spring Boot is useful to get started with minimum effort and create stand-alone, production-grade applications.

To access Spring Initializr: [Link](https://start.spring.io)

How to build the downloaded application:

```bash
$ mvn install -DskipTests && java -jar target\webapp1-0.0.1-SNAPSHOT.jar
or
$ ./mvnw spring-boot:run
```

To access the application we can access http://localhost:8080. We are presented with a White Label Error but its normal for now.

Now we'll build a simple application to serve web content.

I created an app on Spring Initializr with the following dependencies:

* Spring Web
* Thymeleaf
* Spring Boot DevTools

<h4>Create a Web Controller</h4>

In Spring’s approach to building web sites, HTTP requests are handled by a controller.
A **View** is responsible for rendering the HTML content.

This is an example of a Controller:

````java
package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}

}
````

In this case GreetingController handles GET requests for `/greeting` by returning the name of a `View` (in this case, `greeting`).

Explaining the code:

The `@GetMapping` annotation ensures that HTTP GET requests to `/greeting` are mapped to the `greeting()` method.
`@RequestParam` binds the value of the query string parameter `name` into the `name` parameter of the `greeting()` method. This query string parameter is not `required`. If it is absent in the request, the `defaultValue` of `World` is used. The value of the `name` parameter is added to a `Model` object, ultimately making it accessible to the view template.

The implementation of the method body relies on a view technology (in this case, [Thymeleaf](http://www.thymeleaf.org/doc/tutorials/2.1/thymeleafspring.html)) to perform server-side rendering of the HTML. Thymeleaf parses the `greeting.html` template and evaluates the `th:text` expression to render the value of the `${name}` parameter that was set in the controller.

greeting.html example:

```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head> 
    <title>Getting Started: Serving Web Content</title> 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
    <p th:text="'Hello, ' + ${name} + '!'" />
</body>
</html>
```



<h4>Changing the Port</h4>

To change the default application port, I added the following line to the `application.properties`file:

```properties
server.port=8081
```

Now the app will run on port 8081 instead of the default 8080.



<h3>Spring Boot Devtools</h3>

A common feature of developing web applications is coding a change, restarting your application, and refreshing the browser to view the change. This entire process can eat up a lot of time. To speed up this refresh cycle, Spring Boot offers with a handy module known as [spring-boot-devtools](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using-boot-devtools). Spring Boot Devtools:

- Enables [hot swapping](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-hotswapping).
- Switches template engines to disable caching.
- Enables LiveReload to automatically refresh the browser.
- Other reasonable defaults based on development instead of production.

<h3>Running the application</h3>

Spring Boot automatically created a java file for us containing the following:

```java
package com.example.servingwebcontent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServingWebContentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServingWebContentApplication.class, args);
    }

}
```

`@SpringBootApplication` is a convenience annotation that adds all of the following:

- `@Configuration`: Tags the class as a source of bean definitions for the application context.
- `@EnableAutoConfiguration`: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings. For example, if `spring-webmvc` is on the classpath, this annotation flags the application as a web application and activates key behaviors, such as setting up a `DispatcherServlet`.
- `@ComponentScan`: Tells Spring to look for other components, configurations, and services in the `com/example` package, letting it find the controllers.

The `main()` method uses Spring Boot’s `SpringApplication.run()` method to launch an application.

There is no XML code, so this web app is 100% **pure Java**.



<h3>Building an executable Jar</h3>

We can run the application with Maven directly or we can create a JAR file.
Building an executable JAR makes it easy to ship, version, and deploy the service as an application throughout the development lifecycle, across different environments, and so forth. This is because the JAR file contains all the necessary dependencies, classes, and resources.

To run the app:

```bash
$ ./mvnw spring-boot:run
#or, we can build the JAR instead:
$ ./mvnw clean package
#and then execute it:
$ java -jar target/ex2_3b-0.0.1-SNAPSHOT.jar
```

Now the web application is running. To access it we should go to http://localhost:8081/greeting.
To change the name displayed on the message, we can add ?name=*value* to the URL where value is the name we want to see on the webpage. Example: http://localhost:8081/greeting?name=Miguel

This change demonstrates that the [`@RequestParam`](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestParam.html) arrangement in `GreetingController` is working as expected. The `name` parameter has been given a default value of `World`, but it can be explicitly overridden through the query string.

<h3>Adding a homepage</h3>

Static resources, including HTML and JavaScript and CSS, can be served from your Spring Boot application by dropping them into the right place in the source code. By default, Spring Boot serves static content from resources in the classpath at `/static`.
The `index.html`resource is special because, if it exists, it is used as a "welcome page".



<h2>Building a RESTful Web Service</h2>

We will build a service that will accept HTTP GET requests at `http://localhost:8080/greeting`.
It will respond with a JSON representation of a greeting, as the following listing shows:

```json
{"id":1,"content":"Hello, World!"}
```

We can customize the message with an optional parameter *name* that overrides the default value "World".
To do that, we add `?name=value` in the URL.
We'll get the result as the following JSON shows:

```json
{"id":1,"content":"Hello, value!"}
```

<h3>How does this system work?</h3>

The service will handle `GET` requests for `/greeting`, optionally with a `name` parameter in the query string. The `GET` request should return a `200 OK` response with JSON in the body that represents a greeting. It should resemble the following output:

```json
{
    "id": 1,
    "content": "Hello, World!"
}
```

The `id` field is a unique identifier for the greeting, and `content` is the textual representation of the greeting.

`Greeting.java`is our resource representation class.

<h3>Create a Resource Controller</h3>

In Spring’s approach to building RESTful web services, HTTP requests are handled by a controller. These components are identified by the [`@RestController`](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/bind/annotation/RestController.html) annotation.

The GreetingController handles `GET` requests for `/greeting` by returning a new instance of the `Greeting` class.

```java
package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}
```

Explaining the controller:

The `@GetMapping` annotation ensures that HTTP GET requests to `/greeting` are mapped to the `greeting()` method.

`@RequestParam` binds the value of the query string parameter `name` into the `name` parameter of the `greeting()` method. If the `name` parameter is absent in the request, the `defaultValue` of `World` is used.

A key difference between a traditional MVC controller and the RESTful web service controller shown earlier is the way that the HTTP response body is created. Rather than relying on a view technology to perform server-side rendering of the greeting data to HTML, this RESTful web service controller populates and returns a `Greeting` object. The object data will be written directly to the HTTP response as JSON.

This code uses Spring [`@RestController`](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/bind/annotation/RestController.html) annotation, which marks the class as a controller where every method returns a domain object instead of a view.

To build the JAR, we use the same commands as in the previous section.

Accessing http://localhost:8082/greeting (I changed the port using the same process I used in the previous section) we can see the JSON printed on the web page. If we provide a value to name the result changes, and so does the id. This happens because we are working against the same `GreetingController` instance across multiple requests and that its `counter`field is being incremented on each call as expected.

To access the results of the page in the terminal we can use the curl command:

```bash
$ curl -v http://localhost:8082/greeting
```

The Output:

```
*   Trying ::1:8082...
* Connected to localhost (::1) port 8082 (#0)
> GET /greeting HTTP/1.1
> Host: localhost:8082
> User-Agent: curl/7.77.0
> Accept: */*
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Sun, 31 Oct 2021 12:24:24 GMT
< 
* Connection #0 to host localhost left intact
{"id":6,"content":"Hello, World!"}%             
```



<h2>REST API about Shows & Movies</h2>

In this section, created an application on Spring Initializr, using the same template as in the 2.3c).
I created a controller to manage the web request.
I also changed the port of the application, so the address for the application was located at http://localhost:8083.

Based on the `GreetingController.java`from the previous exercise, I created a `ShowController.java`.
I also implemented two `@GetMapping` that ensures that HTTP GET requests to `/quote` and to `/shows` are mapped to the `quote()` and `show()` method respectively.

When we insert `/quote`on the URL we get a random quote from a random movie. When we insert `/quote?show=value`, where value is the id of a movie, we get a random quote from the movie we asked. When we insert `/shows`we get a list of all the shows and its id's.



<h2>Review Questions</h2>

**A. What are the responsibilities/services of a “servlet container”?**

O *servlet container* é um componente do servidor responsável pela gestão dos pedidos que permite a geração de páginas dinâmicas.
A *servlet container* is a component of the server that manages requests and uses java to dynamically generate the web page on the server side. So, to sum up, the servlet container is essentially a part of a web server that interacts with the servlets.

**B. Explain, in brief, the “dynamics” of Model-View-Controller approach used in Spring Boot to serve web content. (You may exemplify with the context of the previous exercises.)**

![MVC](https://static.javatpoint.com/sppages/images/spring-web-model-view-controller.png)

- **Model** - A model contains the data of the application. A data can be a single object or a collection of objects.
- **Controller** - A controller contains the business logic of an application. Here, the @Controller annotation is used to mark the class as the controller.
- **View** - A view represents the provided information in a particular format. Generally, JSP+JSTL is used to create a view page. Although spring also supports other view technologies such as Apache Velocity, Thymeleaf and FreeMarker.
- **Front Controller** - In Spring Web MVC, the DispatcherServlet class works as the front controller. It is responsible to manage the flow of the Spring MVC application.

**C. Inspect the POM.xml for the previous Spring Boot projects. What is the role of the “starters” dependencies?**

The single `spring-boot-starter-web` dependency transitively pulls in all dependencies related to web development. It also reduces the build dependency count.

Using `spring-boot-starter-test` we don't need to upgrade testing libraries and keep them compatible manually. We'll get an opinionated set of libraries and can start writing tests without further setup effort.
This starter includes Spring-specific dependencies and dependencies for auto-configuration and a set of testing libraries. This includes JUnit, Mockito, Hamcrest, AssertJ, JSONassert, and JsonPath. 

**D. Which annotations are transitively included in the @SpringBootApplication?**

`@SpringBootApplication` is a convenience annotation that adds all of the following:

- `@Configuration`: Tags the class as a source of bean definitions for the application context.
- `@EnableAutoConfiguration`: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings. For example, if `spring-webmvc` is on the classpath, this annotation flags the application as a web application and activates key behaviors, such as setting up a `DispatcherServlet`.
- `@ComponentScan`: Tells Spring to look for other components, configurations, and services in the `com/example` package, letting it find the controllers.

**E. Search online for the topic “Best practices for REST API design”. From what you could learn, select your “top 5” practices, and briefly explain them in you own words.**

1. Use JSON as the format for sending and receiving data

   Accepting and responding to API requests used to be done with XML and HTML. Nowadays, JSON is more used because it is way easier to encode and decode than XML, for example.

2. Use nouns instead of verbs in endpoints

   We should not use verbs in endpoints. Instead, we should use nouns explaining what each endpoint does.
   This is because HTTP methods such as `GET`, `POST`, `PUT`, etc are already in verb form.
   So, in short, we should keep the endpoint name only as a non and let the HTTP verbs handle what the endpoints do.

3. Name collections with plural nouns

   An API is a collection of different resources. If we have an endpoint named `/post` this can mislead the user to think that there is only one post and the user could mis-delete them all. To solve this, the best naming is `/posts` so that the user will know that there are many posts.

4. Use Nesting on Endpoints to show relationships

   Nesting endpoints like `/posts/author` and `/posts/postId/comments`are useful because it shows the relationship that exist and helps keeping the API more organized.

5. Provide accurate API documentation 

   It is important to have a clear and accurate documentation because it can help new consumers to understand how the API works and even old consumers to consolidate some aspects of the API usage.

   The documentation should contain:

   - relevant endpoints of the API
   - example requests of the endpoints
   - implementation in several programming languages
   - messages listed for different errors with their status codes

   One of the most common tools we can use for API documentation is Swagger.
