package com.murilovieira.mongo_catalog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "stores")
public class Store {

    @Id
    private String id;
    private String name;
    private String address;

    // 1. GeoJsonPoint para dizer que é um ponto no mapa (X, Y)
    // 2. Índice GEO_2DSPHERE automaticamente
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint localization;

    public Store(String name, String address, GeoJsonPoint localization) {
        this.name = name;
        this.address = address;
        this.localization = localization;
    }
}
