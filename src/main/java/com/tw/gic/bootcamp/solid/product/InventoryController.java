package com.tw.gic.bootcamp.solid.product;

import com.tw.gic.bootcamp.solid.error.ServiceException;
import lombok.AllArgsConstructor;
import io.swagger.annotations.Api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
@Api(value = "Inventory Controller", description = "Inventory APIs")
public class InventoryController {

    private InventoryService inventoryService;

    @GetMapping("/search")
    public ResponseEntity searchProducts(String name) {
        return ResponseEntity.ok(inventoryService.searchProducts(name));
    }

    @GetMapping("")
    public ResponseEntity getProduct(int id) throws ServiceException {
        return ResponseEntity.ok(inventoryService.getProduct(id));
    }

    @GetMapping("/all")
    public ResponseEntity getAllProducts() {
        return ResponseEntity.ok(inventoryService.getAllProducts());
    }

    @PostMapping("")
    public ResponseEntity addProduct(Product product) {
        String err = inventoryService.addProduct(product);
        return err == null ? ResponseEntity.ok("Successfully added product: " + product) : ResponseEntity.internalServerError().body(err);
    }

    @DeleteMapping("")
    public ResponseEntity removeProduct(int id) throws ServiceException {
        String err = inventoryService.removeProduct(id);
        return err == null ? ResponseEntity.ok("Successfully removed product: " + id) : ResponseEntity.internalServerError().body(err);
    }

    @PutMapping
    public ResponseEntity modifyProduct(int id, ProductUpdate product) throws ServiceException {
        String err = inventoryService.updateProduct(id, product);
        return err == null ? ResponseEntity.ok("Successfully updated product: " + product) : ResponseEntity.internalServerError().body(err);
    }

}
