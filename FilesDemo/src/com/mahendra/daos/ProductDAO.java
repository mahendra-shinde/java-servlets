package com.mahendra.daos;

import java.io.*;

import java.util.LinkedList;
import java.util.List;

import com.mahendra.entites.Product;

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
