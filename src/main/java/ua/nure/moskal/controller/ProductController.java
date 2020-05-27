package ua.nure.moskal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.moskal.dto.Product;
import ua.nure.moskal.service.ProductService;

import java.util.List;

@RestController("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(name = "/all")
    public ResponseEntity<List<Product>> getProducts(@RequestParam Integer pageNumber, @RequestParam Integer quantity) {
        List<Product> result = productService.getProducts(pageNumber, quantity);
        return new ResponseEntity<List<Product>>(result, HttpStatus.OK);
    }
}
