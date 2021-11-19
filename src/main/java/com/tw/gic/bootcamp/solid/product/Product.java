package com.tw.gic.bootcamp.solid.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
@Setter
public class Product {

    private int serialNumber;

    private String name;

    private Category category;

    private int price;

    private ProductStatus productStatus;

}
