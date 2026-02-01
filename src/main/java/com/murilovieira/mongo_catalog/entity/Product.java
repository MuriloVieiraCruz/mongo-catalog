package com.murilovieira.mongo_catalog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product {

    // Aqui poderia ser utilizado a variável ObjectId, mas o mongo
    // converte automaticamente de String para ObjectId e vice-versa
    @Id
    private String id;
    private String name;
    private String description;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal price;
    private Integer stockQuantity;
    private boolean active;
    private TechnicalSheet technicalSheet;
}
