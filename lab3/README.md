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

Answer

***Where is the data being saved?***

Answer

***Where is the rule for the “not empty” email address defined?***

Answer



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





