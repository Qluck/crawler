package ua.nure.moskal.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ua.nure.moskal.dto.Product;

@Repository
public interface ProductJdbcRepository extends PagingAndSortingRepository<Product, Long> {
}
