package ua.nure.moskal.service;

import ua.nure.moskal.dto.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts(Integer pageNumber, Integer quantity);
}
