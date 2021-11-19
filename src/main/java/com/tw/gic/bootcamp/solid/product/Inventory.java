package com.tw.gic.bootcamp.solid.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Map;

@AllArgsConstructor
@Getter
@Component
public class Inventory {

    private Map<Category, Map<Integer,Product>> productList;
}
