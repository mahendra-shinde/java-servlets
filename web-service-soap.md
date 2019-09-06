## SOAP Web Services (Top Down approach)


### Setting up the dev environment

1. Download Apache [TomEE Server] (http://apachemirror.wuchna.com/tomee/tomee-8.0.0-M3/apache-tomee-8.0.0-M3-plus.zip)

2.  Extract the contents of downloaded zip into D:\  
3.  Open eclipse with new workspace "work4"
4.  Add new server > Apache Tomcat 9.x > choose the folder d:\apache-tomee-plus-8.0.0-M3

### Develop the _Service_ project

1.  File> New> Dynamic Web Project> 

    ```yaml
    ProjectName:    MyService
    Server-Runtime: Tomcat 9
    ```

2.  Create a new java class `com.mahendra.services.InterestCalculator`

    ```java
    @WebService
    public class InterestService {

        @WebMethod
        public double calc(double principal,double rate, int duration) {
            return principal*(rate/100/12)*duration;
        }
    }
    ```

3.  File > New > Web Service 

    ```yaml
    ServiceType:   Bottom up java bean web service
    Service Implementation: com.mahendra.services.MyService
    ```

4.  Right click on project __MyService__ and **Run On Server**

    Now, Browser should give you `404: Not Found` error.
    
    Now add following at the end of URL:
    
    /wsdl/InterestService.wsdl

    The complete URL should be like:

    http://localhost:8080/MyService/wsdl/InterestService.wsdl

    > Use External web browser to view the service definition (WSDL) document.

    > DO NOT STOP the tomEE server.

### Creating Service client

1.  New > Java Project > Name: ServiceClient
2.  New > Web Service > Web Service Client

    Copy and paste the WSDL URL from previous section.
    Click Next>Finish

3.  Create new main class `com.mahendra.app.Main`

    ```java
    public class Main {

        public static void main(String[] args) {
            InterestServiceProxy proxy = new InterestServiceProxy();
            try {
                double amt = proxy.calc(123000D, 4.5, 36);
                System.out.println("Interest "+amt);
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    ```
> NOTE:   If you stop TomEE, the service stops and client would get Connection refused error.