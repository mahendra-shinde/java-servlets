## REst Services

1. Create new Dynamic web project

    ```yaml
    Projectname:    RestService
    TargetRuntime:  Apache Tomcat 9.x (TomEE)
    ```

2.  Create Rest Application configuration class.
    `com.mahendra.app.ApplicationConfig`

    ```java
    @ApplicationPath("/api")
    public class ApplicationConfig extends Application {
        public Set<Class<?>> getClasses() {
            //Add / Register all your REST services 
            //May get an error as classes are created in next steps
            return new HashSet<Class<?>>(Arrays.asList(InterestService.class,FindEmployeeService.class));
        }
    }
    ```

3.  Create the model class `com.mahendra.entities.Employee` for demonstrating the XML output.

    ```java
    @XmlRootElement
    public class Employee {

        private String firstName;
        private String lastName;
        private int age;
        //Getters and Setters...
        //Constructors...
    ```

4.  Create an employee service  `com.mahendra.services.FindEmployeeService`

    ```java
    @Path("/employee")
    public class FindEmployeeService {

        @GET
        @Produces("application/xml")
        public Employee find() {
            return new Employee("Donald","Trump",76);
        }
    }
    ```

5.  Create the interest calculator service `com.mahendra.services.InterestService`

    ```java
    @Path("/interest")
    public class InterestService {

        // The url should look like
        // /interest?p=12000&r=4.3&d=36
        @GET
        @Produces("text/plain")
        public double calc(@QueryParam("p") double principal,
                    @QueryParam("r") double rate,
                    @QueryParam("d") int duration) {
            System.out.println("Service is invoked");
            return principal * (rate / 100 / 12) * duration;
        }
    }
    ```
6.  Right click on project name and run on server.

    > http://localhost:8080/RestService/api/employee
    > http://localhost:8080/RestService/api/interest?p=12000&r=5.5&d=50 
    