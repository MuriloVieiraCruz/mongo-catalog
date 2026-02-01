package com.murilovieira.mongo_catalog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TechnicalSheet {

    private String mark;
    private String color;
    private String operatingSystem;

    private List<String> connections;
}
