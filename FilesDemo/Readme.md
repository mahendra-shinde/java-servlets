## Using Files for data persistence

1. Create Model class `com.mahendra.entities.Product`

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
		private String datafile;
		private static String DELIMITER = ",";
		public ProductDAO(String datafile) {
			super();
			this.datafile = datafile;
		}
	
		/**
		 * Write single record to file and add line break at the end
		 * @param product
		 * @throws IOException
		 */
		public void save(Product product) throws IOException{
			FileWriter out = new FileWriter(datafile,true);
			out.write(convertFrom(product));
			out.write("\n");
			out.flush();
			out.close();
		}
	
		/**
		 * Read all records from CSV file
		 * @return
		 * @throws IOException
		 */
		public List<Product> getAll()throws IOException {
			List<Product> products = new LinkedList<Product>();
			BufferedReader br = new BufferedReader(new FileReader(datafile));
			String line = br.readLine();
			while(line!=null) {
				Product p = convertFrom(line);
				products.add(p);
				line = br.readLine();
			}
			br.close();
			return products;
		}
	
		/**
		 * Convert Product instance into String (Single line for CSV file using DELIMITER to seperate fields
		 * @param prd Product instance
		 * @return String as CSV record 
		 */
		private String convertFrom(Product prd) {
			StringBuilder line = new StringBuilder();
			
			line.append(prd.getProductId())
				.append(DELIMITER)
				.append(prd.getName())
				.append(DELIMITER)
				.append(prd.getQuantity())
				.append(DELIMITER)
				.append(prd.getRate());
			
			return line.toString();
		}
		/**
		 * Convert String (line inside CSV file) into Product instance
		 * @param row Single line from CSV file as String 
		 * @return product object
		 */
		private Product convertFrom(String row) {
			if(row.trim().isEmpty())
				return null;
			
			Product p = new Product();
			String[] fields = row.split(DELIMITER);
			int productId = Integer.parseInt(fields[0].trim());
			String name = fields[1].trim();
			int qty = Integer.parseInt(fields[2].trim());
			double rate = Double.parseDouble(fields[3].trim());
			
			p.setProductId(productId);
			p.setName(name);
			p.setQuantity(qty);
			p.setRate(rate);
			return p;
		}
	}
		
	```
5.	Create new Servlet `com.mahendra.servlets.IndexServlet` with URL pattern `index.htm`

	```java
	@WebServlet("/index.htm")
	public class IndexServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
	       
	
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String datafile = getServletContext().getRealPath("/WEB-INF/data/mydata.csv");
	 		ProductDAO dao = new ProductDAO(datafile); 
	 
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
	    
	  
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String datafile = getServletContext().getResource("/WEB-INF/data/mydata.csv").getPath();
		
			ProductDAO dao = new ProductDAO(datafile);
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
