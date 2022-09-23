package com.example.Service.implementations;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entities.Customer;
import com.example.Entities.Product;
import com.example.Repositories.ProductRepository;
import com.example.Service.ProductService;


@Service
public class ProductServiceImpl implements ProductService {

	
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public List<Product> listAll() {
		return productRepository.findAll();
	}

	@Override
	public Product findById(int idToFind) {
		Optional<Product> foundProduct = productRepository.findById(idToFind);
		Product newProduct = null;
		if (foundProduct.isPresent()) {
			 newProduct = foundProduct.get();
		}
		else {
			throw new RuntimeException("Product not found.");
		}
		return newProduct;
	}

	@Override
	public void save(Product newProduct) {
		productRepository.save(newProduct);

	}

	@Override
	public void delete(int idToDelete) {
		productRepository.deleteById(idToDelete);

	}
	
	@Override
	public List<Product> listOwnerProducts(Customer owner) {
		return owner.getOwnedProducts();
	}

}
