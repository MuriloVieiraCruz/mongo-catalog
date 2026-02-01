package com.murilovieira.mongo_catalog.config;

import com.murilovieira.mongo_catalog.entity.Product;
import com.murilovieira.mongo_catalog.entity.TechnicalSheet;
import com.murilovieira.mongo_catalog.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
public class LoadData implements CommandLineRunner {

    private final ProductRepository repository;

    public LoadData(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();

        TechnicalSheet notebookCard = new TechnicalSheet(
                "Dell",
                "Prata",
                "Windows 11 Pro",
                Arrays.asList("USB-C", "HDMI 2.1", "Thunderbolt 4")
        );

        TechnicalSheet mouseCard = new TechnicalSheet(
                "Logitech",
                "Preto",
                null,
                Arrays.asList("Bluetooth", "Wireless 2.4Ghz")
        );

        TechnicalSheet monitorCard = new TechnicalSheet(
                "Samsung",
                "Preto",
                null,
                Arrays.asList("HDMI", "DisplayPort")
        );

        Product p1 = new Product(
                null,
                "Dell XPS 13",
                "Ultrabook potente e leve",
                new BigDecimal("12500.00"),
                5,
                true,
                notebookCard
        );

        Product p2 = new Product(
                null,
                "Mouse MX Master 3",
                "Melhor mouse para produtividade",
                new BigDecimal("650.00"),
                20,
                true,
                mouseCard
        );

        Product p3 = new Product(
                null,
                "Monitor 4K 27pol",
                "Alta resolução para designers",
                new BigDecimal("2100.00"),
                8,
                true,
                monitorCard
        );

        repository.saveAll(Arrays.asList(p1, p2, p3));

        System.out.println("--- Carga de Dados Concluída! ---");
        System.out.println("Produtos salvos: " + repository.count());
    }
}
