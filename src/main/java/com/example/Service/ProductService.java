package com.example.Service;

import java.util.List;

import com.example.Entities.Customer;
import com.example.Entities.Product;

public interface ProductService {
	public List<Product> listAll();
	public Product findById(int idToFind);
	public void save(Product newProduct);
	public void delete(int idToDelete);
}
