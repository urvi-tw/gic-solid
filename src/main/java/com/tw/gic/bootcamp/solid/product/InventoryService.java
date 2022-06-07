package com.tw.gic.bootcamp.solid.product;

import com.tw.gic.bootcamp.solid.util.Logger;
import com.tw.gic.bootcamp.solid.error.ServiceException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class InventoryService {

    private Inventory inventory;

    public String addProduct(Product p) {
        Category c = p.getCategory();

        Map<Category, Map<Integer, Product>> productList = inventory.getProductList();

        Map<Integer, Product> productMap = productList.get(c);

        if (productMap == null) {
            productMap = new HashMap<>();
            productMap.put(p.getSerialNumber(),p);
            productList.put(c, productMap);
        } else {
            Product existingProduct = productMap.get(p.getSerialNumber());
            if (existingProduct == null) {
                productMap.put(p.getSerialNumber(), p);
                productList.put(c, productMap);
                Logger.instance().info("Inventory", "Added " + p);
            } else {
                return "Serial number already used by " + existingProduct;
            }
        }
        return null;
    }

    public String removeProduct(int productId) throws ServiceException {
        Product product = getProduct(productId);

        if (product == null) {
            return "No such product " + productId;
        }

        if (product.getProductStatus() == ProductStatus.AVAILABLE) {
            Map<Category, Map<Integer, Product>> productList = inventory.getProductList();

            for (Category category : productList.keySet()) {
                Map<Integer, Product> productMap = productList.get(category);
                productMap.remove(productId);
                productList.put(category, productMap);
            }
        } else {
            return "Product is already sold: " + productId;
        }
        return null;
    }

    public String updateProduct(int productId, ProductUpdate productUpdate) throws ServiceException {
        Product existingProduct = getProduct(productId);

        if (existingProduct == null) {
            return "No such product " + productId;
        }

        if (existingProduct.getProductStatus() == ProductStatus.SOLD) {
            return "Product is already sold " + productId;
        }

        Product updatedProduct = new Product(productId, productUpdate.getName(), productUpdate.getCategory(), productUpdate.getProductType(),productUpdate.getPrice(), existingProduct.getProductStatus());

        Map<Category, Map<Integer, Product>> productList = inventory.getProductList();

        for (Category category : productList.keySet()) {
            Map<Integer, Product> productMap = productList.get(category);
            productMap.put(productId, updatedProduct);
            productList.put(category, productMap);
        }

        return null;
    }

    public List<Product> getAllProducts() {
        Map<Category, Map<Integer, Product>> productList = inventory.getProductList();

        List<Product> products = new ArrayList<>();

        for (Category cat : productList.keySet()) {
            Map<Integer, Product> integerProductMap = productList.get(cat);
            products.addAll(integerProductMap.values());
        }

        return products;
    }

    public Product getProduct(int id) throws ServiceException {
        Map<Category, Map<Integer, Product>> productList = inventory.getProductList();

        for (Category category : productList.keySet()) {
            Map<Integer, Product> productMap = productList.get(category);
            Product p = productMap.get(id);
            if (p != null) {
                return p;
            }
        }
        throw new ServiceException("no such product "+id);
    }

    public List<Product> searchProducts(String name) {
        Map<Category, Map<Integer, Product>> productList = inventory.getProductList();

        List<Product> foundProducts = new ArrayList<>();

        for (Category category : productList.keySet()) {
            Map<Integer, Product> productMap = productList.get(category);
            for (Integer id : productMap.keySet()) {
                Product p = productMap.get(id);
                if (p.getName().contains(name)) {
                    foundProducts.add(p);
                }
            }
        }
        return foundProducts;
    }

}
