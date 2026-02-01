package com.murilovieira.mongo_catalog.service;

import com.murilovieira.mongo_catalog.entity.Product;
import com.murilovieira.mongo_catalog.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> listAll() {
        return repository.findAll();
    }

    public Product save(Product produto) {
        return repository.save(produto);
    }

    public List<Product> findByConnection(String conexao) {
        return repository.findByConnection(conexao);
    }

    public List<Product> findForMaxPrice(double preco) {
        return repository.findForPriceLowerThan(preco);
    }
}
