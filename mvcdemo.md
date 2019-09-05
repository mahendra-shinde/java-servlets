# MVC Demo

1.  Create new dynamic web project 'mvcdemo'
2.  Create new HTML page inside `WebContent` directory.
    file name `index.htm`

    ```HTML
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>Search your result!</title>
    </head>
    <body>
    <h2>XYZ University</h2>
    <h3>Result Page</h3>
    <form action="check-result" method="get">
        Roll number: <input type="text" name="rollno"/>
        <input type="submit" value="search" />
    </form>
    </body>
    </html>
    ```
3.  Create new java class `com.mahendra.entities.Result` as model class.

    ```java
    public class Result {
	private int rollNo;
	private String name;
	private String result;
	
	
	public Result(int rollNo, String name, String result) {
		super();
		this.rollNo = rollNo;
		this.name = name;
		this.result = result;
	}

	public Result() {
		super();
	}
    //GETTERS and SETTERS here...
    ```
4.  