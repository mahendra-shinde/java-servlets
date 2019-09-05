package com.mahendra.daos;

import javax.sql.DataSource;

import com.mahendra.entites.Product;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

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
