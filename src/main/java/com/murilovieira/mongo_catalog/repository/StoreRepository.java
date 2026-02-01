package com.murilovieira.mongo_catalog.repository;

import com.murilovieira.mongo_catalog.entity.Store;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StoreRepository extends MongoRepository<Store, String> {

    // Busque pela propriedade 'localizacao' que esteja 'perto'
    // do ponto 'p', respeitando a distância máxima 'd'.
    List<Store> findByLocalizationNear(Point p, Distance d);
}
