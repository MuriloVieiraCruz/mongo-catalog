package com.murilovieira.mongo_catalog.repository;

import com.murilovieira.mongo_catalog.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByName(String name);

    // 2. Query Aninhada (Nested Property)
    // O Spring entende que você quer buscar dentro do objeto 'FichaTecnica', no campo 'marca'.
    // Equivalente SQL: JOIN com WHERE
    List<Product> findByTechnicalSheetMark(String mark);

    // 3. @Query Personalizada (JSON puro)
    // Aqui é escrito "SQL de Mongo" (que é JSON).
    // ?0 representa o primeiro parâmetro do métod.
    // 'preco' é o campo no banco.
    // $lt é o operador "Less Than" (Menor Que). Ex: $eq, $gte, $lte, $in, $regex;
    @Query("{ 'price': { $lt: ?0 } }")
    List<Product> findForPriceLowerThan(double maxPrice);

    // 4. Buscar produtos que contenham uma conexão específica (Query em Arrays)
    // Se o campo é uma lista (List<String>), ele busca
    // se o valor existe DENTRO da lista.
    @Query("{ 'technicalSheet.connections': ?0 }")
    List<Product> findByConnection(String connectionType); // Ex: buscar "HDMI"
}
