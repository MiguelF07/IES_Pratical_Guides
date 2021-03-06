<h1>Lab 3 | P1</h1>

Miguel Ferreira 98599




<h2>Multi-layer web applications with Spring Boot</h2>



<h3>Spring Boot vs. Spring MVC vs. Spring</h3>

Spring Boot does not compete with Spring or Spring MVC. It makes it easy to use them.

### Spring Framework

The most important feature of Spring Framework is Dependency Injection. At the core of all Spring Modules is Dependency Injection or IOC — Inversion of Control.

### Spring MVC

Spring MVC provides a decoupled way of developing web applications. With simple concepts like Dispatcher Servlet, ModelAndView, and View Resolver, it makes it easy to develop web applications.

<h2>Accessing databases in Spring Boot</h2>

The Java Persistence API (JPA) defines a standard interface to manage data over relational databases; there are several frameworks that implement the JPA specification, such as the Hibernate framework. JPA offers a specification for ORM (object-relational mapping).

<h3>Following the Spring Boot CRUD Application with Thymeleaf Tutorial</h3>

[Tutorial](https://www.baeldung.com/spring-boot-crud-thymeleaf)

<h3>The Domain Layer</h3>

Let's keep in mind that we've annotated the class with the *@Entity* annotation. **Therefore, the JPA implementation, which is Hibernate, in** **this case, will be able to perform CRUD operations on the domain entities.**

In addition, we've constrained the *name* and *email* fields with the *@NotBlank* constraint. This implies that we can use Hibernate Validator for validating the constrained fields before persisting or updating an entity in the database.

The @Autowired annotation provides **more fine-grained control over where and how autowiring should be accomplished**. The @Autowired annotation can be used to autowire bean on the setter method just like @Required annotation, constructor, a property or methods with arbitrary names and/or multiple arguments.

<h3>The Repository Layer</h3>

[Spring Data JPA](https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa) is a key component of Spring Boot's *spring-boot-starter-data-jpa* that makes it easy to add CRUD functionality through a powerful layer of abstraction placed on top of a JPA implementation.

To provide our application with basic CRUD functionality on *User* objects all that we need to do is to extend the *CrudRepository* interface:

```java
@Repository
public interface UserRepository extends CrudRepository<User, Long> {}
```

<h3>The Controller Layer</h3>

In this case, a single controller class will suffice for handling GET and POST HTTP requests and then map them to calls to our *UserRepository* implementation.

The *showSignUpForm()* and *addUser()* controller methods:

```java
@GetMapping("/signup")
public String showSignUpForm(User user) {
	return "add-user";
}
```

This function will return the sign-up form.

```java
@PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        
        userRepository.save(user);
        return "redirect:/index";
    }
```

This one will persist a new entity in the database after validating the constrained fields.

The *showUpdateForm()* method is responsible for fetching the *User* entity that matches the supplied *id* from the database.

```java
@GetMapping("/edit/{id}")
public String showUpdateForm(@PathVariable("id") long id, Model model) {
    User user = userRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    
    model.addAttribute("user", user);
    return "update-user";
}
```

The *updateUser()* will persist the updated entity in the database.

```java
@PostMapping("/update/{id}")
public String updateUser(@PathVariable("id") long id, @Valid User user, 
  BindingResult result, Model model) {
    if (result.hasErrors()) {
        user.setId(id);
        return "update-user";
    }
        
    userRepository.save(user);
    return "redirect:/index";
}
```

The deleteUser() will remove the given entity.

```java
@GetMapping("/delete/{id}")
public String deleteUser(@PathVariable("id") long id, Model model) {
    User user = userRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    userRepository.delete(user);
    return "redirect:/index";
}
```



<h3>The View Layer</h3>

For the app to function correctly, we need to create the HTML templates required for displaying the signup form, the update form, and rendering the list of persisted *User* entities.

To run the app we'll run it on the IDE and access it on *http://localhost:8080*.

Answering the questions:

***The “UserController” class gets an instance of “userRepository” through its constructor; how is this new repository instantiated?***

This happens because of the `@AutoWired`annotation that is before the UserController constructor.
Autowiring feature of **spring framework enables you to inject the object dependency implicitly**. It internally uses setter or constructor injection.

***List the methods invoked in the “userRepository” object by the “UserController”. Where are these methods defined?***

The `findById()` method that doesn't need to be defined, and the `findByEmail()`that is declared on the repository body.

***Where is the data being saved?***

The data is being saved in a local database that is not persistent.

***Where is the rule for the “not empty” email address defined?***

The `@NotBlank`annotation that is on every field of the user.



<h2>3.2</h2>

<h3>What is an Entity in JPA?</h3>

An entity represents a table stored in a database. Every instance of an entity represents a row in the table.

Each JPA entity must have a primary key which uniquely identifies it. [The *@Id* annotation](https://www.baeldung.com/hibernate-identifiers) defines the primary key. We can generate the identifiers in different ways which are specified by the *@GeneratedValue*annotation.

In most cases, **the name of the table in the database and the name of the entity will not be the same.** 
In these cases, we can specify the table name using the *@Table* annotation.
If we do not use the *@Table* annotation, the name of the entity will be considered the name of the table.

Just like the *@Table* annotation, we can use the *@Column* annotation to mention the details of a column in the table.

The *@Column* annotation has many elements such as *name, length, nullable, and unique*.

[This website](https://www.baeldung.com/jpa-entities) has a tutorial that explains these and other annotations of the JPA.

`@Repository` is a Spring annotation that indicates that the **decorated class is a repository**. A repository is a mechanism for encapsulating storage, retrieval, and search behavior which emulates a collection of objects.



<h3>Docker command to install an instance of MYSQL</h3>

```bash
docker run --name mysql5 -e MYSQL_ROOT_PASSWORD=secret1 -e MYSQL_DATABASE=demo -e MYSQL_USER=demo -e MYSQL_PASSWORD=secret2 -p 33061:3306 -d mysql/mysql-server:5.7
```

In this section we'll build **CRUD RESTFul APIs** for a Simple *Employee Management System* using Spring Boot 2 JPA and MySQL, following [this tutorial](https://www.javaguides.net/2018/09/spring-boot-2-jpa-mysql-crud-example.html).

We have five REST APIs created for the Employee:

* GET Employees
* POST Employee
* GET Employee
* PUT Employee
* DELETE Employee

Now the first step is to create a JPA Entity named Employee, stored in the `Employee.java` file.
The second step is to create the Spring Data Repository named `EmployeeRepository.java`.
The third step is to create the Spring Rest Controller named `EmployeeController.java`.

Now we'll have to deal with the Exceptions: we can specify the Response Status for a specific exception along with the definition of the Exception with *‘@ResponseStatus’* annotation.

`GlobalExceptionHandler`(that has the *@ControllerAdvice* annotation) handles exception-specific and global exceptions in a single place.

<h3>Testing the application</h3>

Now, using the Postman utility to send a POST request to `http:localhost:8080/api/v1/employees` we get an auto generated ID:

<img width="1392" alt="Screenshot 2021-11-13 at 22 28 44" src="https://user-images.githubusercontent.com/66647922/141662198-c0c71e43-0bd7-4b74-b17e-c19dcb4c85e9.png">

Using the GET to get one Employee:

<img width="1392" alt="Screenshot 2021-11-13 at 22 39 26" src="https://user-images.githubusercontent.com/66647922/141662213-4eef55be-e2b0-45cc-a09d-1307b217cf66.png">

Using the GET to get all Employees:

<img width="1392" alt="Screenshot 2021-11-13 at 22 40 21" src="https://user-images.githubusercontent.com/66647922/141662221-c15462c8-63d6-48a5-b6f8-5f52bfa452b8.png">

Using PUT method to update one of the Employees:

<img width="1392" alt="Screenshot 2021-11-13 at 22 42 08" src="https://user-images.githubusercontent.com/66647922/141662228-614c5289-ec46-4a40-bc4f-d4a0c374a1cd.png">

Using the DELETE method to delete an Employee:

<img width="1392" alt="Screenshot 2021-11-13 at 22 42 56" src="https://user-images.githubusercontent.com/66647922/141662236-0e050eb5-48c4-4c28-b037-5c0040d606d5.png">

Now, on g) I added a findByEmail feature to the repository.

To get the user we use the GET method:

<img width="1392" alt="Screenshot 2021-11-14 at 19 38 11" src="https://user-images.githubusercontent.com/66647922/141695890-41115e7c-35d6-4042-bac7-48e3d0cc22d6.png">

<h2>3.3 Wrapping-up and integrating concepts</h2>

The meaning of `CascadeType.ALL` is that the persistence will propagate (cascade) all operations to the relating entities.

[This Link](https://stackoverflow.com/questions/2990799/difference-between-fetchtype-lazy-and-eager-in-java-persistence-api) explains what `FetchType.LAZY` is about, but, to sum up, it only fetches the list of quotes when the `getQuotes()` method is called

For this project I created a new database, using the previous docker container with modified parameters:

```bash
$ docker run --name mysql3_3 -e MYSQL_ROOT_PASSWORD=secret1 -e MYSQL_DATABASE=3_3 -e MYSQL_USER=3_3 -e MYSQL_PASSWORD=secret2 -p 33062:3306 -d mysql/mysql-server:5.7 
```

And changed the properties file to match those settings.

In this project, we have different endpoints to manage data in the API. I also added two methods to retrieve random movies and random quotes.

To summarize the methods are:

​	**For the Movies:**

​	GET /movies : Gets the list of all the movies in the database.
​	GET /movies/random: Gets a random movie.
​	GET /movies/{id}: Gets a specific movie, given the ID.
​	POST /movies: Puts a new movie in the database.
​	PUT /movies/{id}: Updates a given Movie in the database.
​	DELETE /movies/{id}: Deletes a given Movie from the database.

​	**For the Quotes:**

​	GET /quotes : Gets all the quotes in the database.
​	GET /movies/{id}/quotes/random : Gets a random quote from a specific movie from the database.
​	GET /quotes/random : Gets a random quote from all the movies.
​	GET /quotes/{id} : Gets a specific quote from the given ID.
​	POST /quotes: Posts a quote into the database.
​	PUT /quotes/{id}: Updates the quote of the given ID.
​	DELETE /quotes/{id}: Deletes the quote with the given ID.

------------------------------------

<h2>Some Movies Examples</h2>

GET /movies :

<img width="1552" alt="Screenshot 2021-11-16 at 15 09 29" src="https://user-images.githubusercontent.com/66647922/142011113-c1c1c648-b314-4e61-aeb4-2a42c1416b93.png">

GET /movies/random

<img width="1552" alt="Screenshot 2021-11-16 at 15 45 38" src="https://user-images.githubusercontent.com/66647922/142017796-3911830a-25fa-4dc5-aa8a-fda1514126b1.png">

GET /movies/{id}:

<img width="1552" alt="Screenshot 2021-11-16 at 15 10 35" src="https://user-images.githubusercontent.com/66647922/142011258-5282ee83-7fc2-4bed-b9a6-5e17d81b6e98.png">

POST /movies:

<img width="1552" alt="Screenshot 2021-11-16 at 15 12 13" src="https://user-images.githubusercontent.com/66647922/142011549-58b90b99-79d6-4f94-bbe8-de327244c5f0.png">

PUT /movies/{id}:

<img width="1552" alt="Screenshot 2021-11-16 at 16 44 37" src="https://user-images.githubusercontent.com/66647922/142028120-289fe45c-20c0-459d-b254-ff9f93bfea2c.png">

DELETE /movies/{id}:

<img width="1552" alt="Screenshot 2021-11-16 at 15 26 54" src="https://user-images.githubusercontent.com/66647922/142014436-5ab2e4a9-91a1-4708-a3e3-427ebf7ca05b.png">

<h2>Some Quotes Examples</h2>

GET /quotes:

<img width="1552" alt="Screenshot 2021-11-16 at 15 31 32" src="https://user-images.githubusercontent.com/66647922/142015203-f3331860-5564-4a80-bbdd-21a1d8c724ef.png">

GET /quotes/random:

<img width="1552" alt="Screenshot 2021-11-16 at 18 38 15" src="https://user-images.githubusercontent.com/66647922/142045540-20fa10df-23b2-427e-892c-fc5a7bc2804f.png">

GET /movies/{id}/quotes/random:

<img width="1552" alt="Screenshot 2021-11-16 at 18 36 03" src="https://user-images.githubusercontent.com/66647922/142045354-a109429c-0e05-46d7-b819-1f1a7f7fb632.png">

GET /quotes/{id}:

<img width="1552" alt="Screenshot 2021-11-16 at 15 32 58" src="https://user-images.githubusercontent.com/66647922/142015424-6d1e07b9-2dec-4221-aa92-ff73f61167d6.png">

POST /quotes:

<img width="1552" alt="Screenshot 2021-11-16 at 15 35 31" src="https://user-images.githubusercontent.com/66647922/142015893-923cecd1-0293-4bc8-8a03-ca1048503f9b.png">

PUT /quotes{id}:

<img width="1552" alt="Screenshot 2021-11-16 at 16 52 13" src="https://user-images.githubusercontent.com/66647922/142029379-130a49a7-697c-482c-81aa-693972f85211.png">

DELETE /quotes{id}:

<img width="1552" alt="Screenshot 2021-11-16 at 16 53 55" src="https://user-images.githubusercontent.com/66647922/142029614-ec363de3-bbd9-4d73-840c-b4bc823dd9aa.png">

<h2>Dockerizing the Application</h2>

To dockerize I tried two different methods but only the last one succeeded.

Following the [guide tutorial](https://spring.io/guides/topicals/spring-boot-docker/) I tried this implementation:

```bash
$ cd pathToProject
$ ./mvnw clean package
$ cd target
$ mkdir dependency
$ cd dependency
$ jar -xf ../*.jar
$ docker build -t myorg/myapp .
```

With this Dockerfile:

```dockerfile
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","Ex33Application.java"]
```

But it didn't work, I had an error trying to build the docker image.

Then I tried following [this tutorial](https://www.callicoder.com/spring-boot-mysql-react-docker-compose-example/).

I have the following DockerFile:

```dockerfile
FROM openjdk:11 as build

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn

COPY pom.xml .


RUN ./mvnw dependency:go-offline -B

COPY src src

RUN ./mvnw package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:11

ARG DEPENDENCY=/app/target/dependency

COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","com.ies.ex3_3.Ex33Application"]
```

The image was built but running the application didn't work because somehow there was a problem with connecting to MySQL.
This happened because the apps were running on two different containers.

The solution was to create a Docker-Compose file that linked the two containers and ran them.

The docker-compose file has the following content:

```dockerfile
version: "3"
services:

  mysql:
    image: mysql/mysql-server:5.7
    container_name: mysql5
    command: mysqld 
    volumes:
      - mysql-data:/var/local/mysql/data
    ports:
      - "33061:3306"
    hostname: mysql
    environment:
      - MYSQL_ROOT_PASSWORD=secret1
      - MYSQL_USER=demo
      - MYSQL_DATABASE=demo
      - MYSQL_PASSWORD=secret2

  maven-app:
    container_name: ex3-maven-app-1
    image: maven-app:latest
    build:
      context: .
      dockerfile: Dockerfile
    ports:
    - 8080:8080
    depends_on:
      - "mysql"
    command: [ "./mvnw", "spring-boot:run" ]
    environment:
      - spring.datasource.url=jdbc:mysql://mysql:3306/demo
      - spring.datasource.username=demo
      - spring.datasource.password=secret2
      - spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect
      - spring.jpa.hibernate.ddl-auto=update

volumes:
  mysql-data:
    driver: local
```



<h2>Review Questions</h2>

**A) Explain the differences between the RestController and Controller components used in different parts of this lab.**

The `@Controller` annotation represents the classic controllers. This annotation is typically used with `@RequestMapping` annotation for request handling methods. When we use this method, we need a separate `@ResponseBody`annotation to enable automatic serialization of the returned object into the *HttpResponse*.

The `@RestController` is a specialized version of the controller. It includes the `@Controller` and `@ResponseBod` annotations, and as a result, simplifies the controller implementation.

**B) Create a visualization of the Spring Boot layers (UML diagram or similar), displaying the key abstractions in the solution of 3.3, in particular: entities, repositories, services and REST controllers. Describe the role of the elements modeled in the diagram.**

![UML_Diagram_3_3](https://user-images.githubusercontent.com/66647922/142046644-82db714e-a6be-420c-9636-1c64289d2754.png)

Role of the elements:

* **Movie** : Defines `Movie` objects.
* **Quote** : Defines `Quote` objects.
* **MovieRepository** : Manages the Movies database data (with CRUD operations)
* **QuoteRepository** : Manages the Quotes database data (with CRUD operations)
* **MovieQuoteService** : Separates the Repository from the RestController, creating a bridge between them. The RestController makes operations in the repository only through this service.
* **MovieQuoteController** : Rest Controller that manages HTTP Requests. It has many endpoints and manages what happens on each.
* **ResourceNotFoundException** : Is a class where we can specify the Response Status for a specific exception
* **ErrorDetails** : Specific error response structure
* **GlobalExceptionHandler** : Handles exception-specific and global exceptions in a single place.

**C) Explain the annotations @Table, @Colum, @Id found in the Employee entity.**

`@Table` -> When we create a `@Entity` annotation we're representing a Table stored in the database. When we use `@Table` after `@Entity` we're specifying the name of the table, that will not be the same as the name of the entity, in this case. If we do not use the `@Table` annotation, the name of the entity and the name of the table will be the same.

**D) Explain the use of the annotation @AutoWired (in the Rest Controller class).**

Just like mentioned before, the `@Autowired` annotation provides **more fine-grained control over where and how autowiring should be accomplished**. The `@Autowired` annotation can be used to autowire bean on the setter method just like `@Required` annotation, constructor, a property or methods with arbitrary names and/or multiple arguments.
