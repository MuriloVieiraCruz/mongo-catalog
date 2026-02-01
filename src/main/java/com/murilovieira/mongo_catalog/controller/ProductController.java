package com.murilovieira.mongo_catalog.controller;

import com.murilovieira.mongo_catalog.dto.ReportBrand;
import com.murilovieira.mongo_catalog.entity.Product;
import com.murilovieira.mongo_catalog.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.save(product);
    }

    @GetMapping
    public List<Product> list() {
        return service.listAll();
    }

    @GetMapping("/buscar-conexao")
    public ResponseEntity<List<Product>> findByConnection(@RequestParam("q") String connection) {
        List<Product> products = service.findByConnection(connection);

        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/baratos")
    public List<Product> findCheap(@RequestParam("max") double max) {
        return service.findForMaxPrice(max);
    }

    @GetMapping("/relatorio-marcas")
    public List<ReportBrand> getMarkReport() {
        return service.generateReportByBrand();
    }
}
