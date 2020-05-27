package ua.nure.moskal.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.nure.moskal.dto.Product;
import ua.nure.moskal.exception.MySqlException;
import ua.nure.moskal.jpa.ProductJdbcRepository;
import ua.nure.moskal.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductJdbcRepository productJdbcRepository;

    @Override
    public List<Product> getProducts(Integer pageNumber, Integer quantity) {
        try {
            Pageable pageable = new PageRequest(pageNumber, quantity);
            return productJdbcRepository.findAll(pageable).getContent();
        } catch (MySqlException e) {
            log.error("Can't execute query with this params", e);
        }
        return new ArrayList<>();
    }
}
