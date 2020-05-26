package ua.nure.moskal.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.nure.moskal.dto.Product;

@Repository
public interface ProductJdbcRepository extends CrudRepository<Product,Long> {
}
