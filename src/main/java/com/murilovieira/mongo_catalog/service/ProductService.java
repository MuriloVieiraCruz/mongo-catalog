package com.murilovieira.mongo_catalog.service;

import com.murilovieira.mongo_catalog.dto.ReportBrand;
import com.murilovieira.mongo_catalog.entity.Product;
import com.murilovieira.mongo_catalog.repository.ProductRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final MongoTemplate mongoTemplate;

    public ProductService(ProductRepository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
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

    public List<ReportBrand> generateReportByBrand() {
        // Construindo a pipeline

        Aggregation aggregation = Aggregation.newAggregation(

                // Estágio 1: Filtrar ($match)
                Aggregation.match(Criteria.where("active").is(true)),

                // Estágio 2: Agrupar ($group)
                // Agrupar pelo campo "fichaTecnica.marca".
                Aggregation.group("technicalSheet.mark")
                        .avg("price").as("averagePrice")
                        .sum("stockQuantity").as("totalStock"),

                // Estágio 3: Projetar/Formatar ($project)
                // O campo de agrupamento vira o "_id" do resultado.
                Aggregation.project("averagePrice", "totalStock")
                        .and("_id").as("mark")
        );

        // "Aplique essa agregação na coleção 'produtos' e jogue o resultado na classe 'ReportBrand'"
        AggregationResults<ReportBrand> results =
                mongoTemplate.aggregate(aggregation, "products", ReportBrand.class);

        return results.getMappedResults();
    }
}
