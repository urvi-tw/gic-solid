package com.tw.gic.bootcamp.solid.product;

import lombok.*;

@ToString
@Getter
@AllArgsConstructor
@Setter


public class ProductUpdate {

    private String name;

    private Category category;

    private ProductType productType;

    private int price;

}
