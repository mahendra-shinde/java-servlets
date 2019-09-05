## JDBC Connection Pool with JNDI

1. Copy `ojdbc7.jar` or `ojdbc6.jar` into tomcat's lib directory. 
	On my system, it was `/home/mahendra/apache-tomcat-8.5.45/lib`

2.	Inside WebContent/META-INF directory create a file with name `context.xml` (Must exactly match).

	```xml
	<Context>
	<Resource name="jdbc/mydb"
	          auth="Container"
	          type="javax.sql.DataSource"
	
	          maxActive="10"
	          minIdle="2"
	          maxWait="10000"
	
	          initialSize="5"
	
	          username="hr"
	          password="hr"
	          driverClassName="oracle.jdbc.OracleDriver"
	          url="jdbc:oracle:thin:@localhost:1521/xe"
	/>
	</Context>
	```

3.	Create Model class `com.mahendra.entities.Product`

	```java
	public class Product implements Serializable {
		private int productId;
		private String name;
		private int quantity;
		private double rate;
		//Getters and Setters...
		//Parameterized and Empty constructor...
	```
4.	Create a DAO Class `com.mahendra.daos.ProductDAO`	

	```java
	public class ProductDAO {

		private static String INSERT_PRODUCT = "INSERT into PRODUCTS(product_id, p_name,quantity, rate ) VALUES (?,?,?,?)";
		private static String FETCH_ALL = "select product_id, p_name, quantity, rate from products ";
		private DataSource ds;
	
		public ProductDAO(DataSource ds) {
			super();
			this.ds = ds;
		}
		public void save(Product product) {
			try {
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(INSERT_PRODUCT);
				ps.setInt(1, product.getProductId());
				ps.setString(2, product.getName());
				ps.setInt(3, product.getQuantity());
				ps.setDouble(4, product.getRate());
				ps.executeUpdate();
				System.out.println("Record saved!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Unable to save record ", e);
			}
		}
	
		public List<Product> getAll() {
			List<Product> products = new LinkedList<Product>();
			try {
				Connection con = ds.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(FETCH_ALL);
				while(rs.next()) {
					System.out.println("Extracting results");
					products.add(convertFrom(rs));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("Unable to fetch records", e);
			}
			return products;
	
		}
	
		/**
		 * Convert ResultSet row into product object!
		 * @param ResultSet row
		 * @return Product object
		 */
		private Product convertFrom(ResultSet row) {
			Product p = new Product();
			try {
				p.setProductId(row.getInt("product_id"));
				p.setName(row.getString("p_name"));
				p.setRate(row.getDouble("rate"));
				p.setQuantity(row.getInt("quantity"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Unable to extract values", e);
			}
			return p;
		}
	}
	
	```
5.	Create new Servlet `com.mahendra.servlets.IndexServlet` with URL pattern `index.htm`

	```java
	@WebServlet("/index.htm")
	public class IndexServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
	       
		
		@Resource(name="jdbc/mydb")
		private DataSource ds;
	
	
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			ProductDAO dao = new ProductDAO(ds);
			List<Product> products = dao.getAll();
			request.setAttribute("products", products);
			RequestDispatcher view = request.getRequestDispatcher("list.jsp");
			view.forward(request, response);
		}
	
	}
	```

6.	Copy following TWO files into WEB-INF/lib directory
	
	1. taglib-standard-impl-1.2.5.jar
	2. taglib-standard-spec-1.2.5.jar

	> You would find these files under `examples` directory in tomcat.
	
7.	Create a JSP page `list.jsp` inside WebContent directory.

	```jsp
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<html>
	<head>
	<meta charset="UTF-8">
	<title>Product Home</title>
	</head>
	<body>
	<h2>List of Products</h2>
	<h3>${param.err }</h3>
	<c:if test="${requestScope.products ==null }">
		<c:redirect url="index.htm"/>
	</c:if>
	
	<c:if test="${requestScope.products !=null }">
	<table>
		<tr>
		<td>Product ID</td>
		<td>Name</td>
		<td>Quantity</td>
		<td>Price</td>
		</tr>
		<c:forEach items="${requestScope.products}" var="p">
		<tr>
		<td>${p.productId }</td>
		<td>${p.name }</td>
		<td>${p.quantity }</td>
		<td>${p.rate }</td>
		</tr>
		</c:forEach>
	</table>
	</c:if>
	
	<form action="add-product" method="post">
	Product ID : <input type="number" name="id" /><br/>
	Product name: <input type="text" name="pname"/><br/>
	Quantity : <input type="number" name="qty" /><br/>
	Rate : <input type="number" name="rate"/><br/>	
	<input type="submit" value="Save"/>
	</form>
	</body>
	</html>
	```

8.	Create another servlet `com.mahendra.servlets.AddProductServlet` with URL Mapping `add-product`.

	```java
	@WebServlet("/add-product")
	public class AddProductServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
	    
		@Resource(name="jdbc/mydb")
		private DataSource ds;
	  
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			ProductDAO dao = new ProductDAO(ds);
			String pid = request.getParameter("id");
			int id = Integer.parseInt(pid);
			String pname = request.getParameter("pname");
			String sqty = request.getParameter("qty");
			int qty = Integer.parseInt(sqty);
			String srate = request.getParameter("rate");
			double rate = Double.parseDouble(srate);
			String msg = null;
			Product product = new Product(id,pname,qty,rate);
			try {
			dao.save(product);
			msg = "Record saved!";
			}catch(Exception ex) {
				msg = "Unable to save record "+ex.getMessage();
			}
			response.sendRedirect("index.htm?err="+msg);
		}
	
	}
	```
