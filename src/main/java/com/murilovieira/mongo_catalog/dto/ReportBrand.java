package com.murilovieira.mongo_catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportBrand {

    private String mark;
    private Double averagePrice;
    private Integer totalStock;
}
