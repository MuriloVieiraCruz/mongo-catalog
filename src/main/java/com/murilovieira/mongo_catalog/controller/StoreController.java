package com.murilovieira.mongo_catalog.controller;

import com.murilovieira.mongo_catalog.entity.Store;
import com.murilovieira.mongo_catalog.repository.StoreRepository;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lojas")
public class StoreController {

    private final StoreRepository repository;

    public StoreController(StoreRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/perto")
    public List<Store> findNearbyStores(
            @RequestParam("lat") double latitude,
            @RequestParam("long") double longitude,
            @RequestParam(value = "km", defaultValue = "10") double raioKm) {

        // Ponto onde o usuário está
        // Point do Spring aceita (x, y)
        Point userPoint = new Point(longitude, latitude);
        Distance distancia = new Distance(raioKm, Metrics.KILOMETERS);

        return repository.findByLocalizationNear(userPoint, distancia);
    }
}
