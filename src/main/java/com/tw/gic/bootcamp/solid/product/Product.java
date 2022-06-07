package com.tw.gic.bootcamp.solid.product;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

@ToString
@Getter
@AllArgsConstructor
@Setter


public class Product {

    private int serialNumber;

    private String name;

    private Category category;

    private ProductType productType;

    private int price;

    private ProductStatus productStatus;

}
