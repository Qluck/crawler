package ua.nure.moskal.crawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.nure.moskal.dto.Product;
import ua.nure.moskal.jpa.ProductJdbcRepository;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.*;

@Component
public class MySqlPipeline implements Pipeline {
    @Autowired
    private ProductJdbcRepository productJdbcRepository;

    @Override
    public void process(ResultItems resultItems, Task task) {
        Map<String, Object> fields = resultItems.getAll();

        List<Product> products = getProductList(fields);
        productJdbcRepository.save(products);
    }

    private List<Product> getProductList(Map<String, Object> fields) {
        List<String> images = (List<String>) fields.get("image");
        List<String> names = (List<String>) fields.get("name");
        List<String> prices = (List<String>) fields.get("price");

        List<Product> products = new ArrayList<>();
        images.forEach(image -> {
            Product product = new Product();
            product.setImageUrl(image);
            product.setPrice(Double.valueOf(prices.get(images.indexOf(image))));
            product.setName(names.get(images.indexOf(image)));
            products.add(product);
        });
        return products;
    }
}
