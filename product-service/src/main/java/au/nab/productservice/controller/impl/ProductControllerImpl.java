package au.nab.productservice.controller.impl;

import au.nab.productservice.controller.ProductController;
import au.nab.productservice.dtos.FilterCondition;
import au.nab.productservice.dtos.ProductDto;
import au.nab.productservice.dtos.http.PageResponse;
import au.nab.productservice.dtos.http.ProductResponse;
import au.nab.productservice.entities.Product;
import au.nab.productservice.handlers.etos.ProductEto;
import au.nab.productservice.repository.support.GenericFilterCriteriaBuilder;
import au.nab.productservice.service.FilterBuilderService;
import au.nab.productservice.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductControllerImpl implements ProductController {
    @Value("${product.topic.created}")
    private String productCreatedTopic;
    private final ProductService productService;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final FilterBuilderService filterBuilderService;

    public ProductControllerImpl(final ProductService productService,
                                 final FilterBuilderService filterBuilderService,
                                 final KafkaTemplate<String, String> kafkaTemplate) {
        this.productService = productService;
        this.filterBuilderService = filterBuilderService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public ResponseEntity<ProductResponse> addProduct(@RequestBody() ProductDto product) throws JsonProcessingException {
        final Optional<ProductResponse> response = productService.addProduct(product);

        final ProductResponse productResponse = response.get();
        final ProductEto productEto = ProductEto
                .builder()
                .name(productResponse.getName())
                .brand(productResponse.getBrand())
                .categories(productResponse.getCategories())
                .colors(productResponse.getColors())
                .description(productResponse.getDescription())
                .price(productResponse.getPrice())
                .quantity(productResponse.getQuantity())
                .id(productResponse.getId()).build();
        kafkaTemplate.send(productCreatedTopic, productEto.toString());
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.internalServerError().build());
    }

    /**
     * @param page      page number
     * @param size      size count
     * @param filterOr  string filter or conditions
     * @param filterAnd string filter and conditions
     * @param orders    string orders
     * @return PageResponse<Product>
     */
    @Override
    public ResponseEntity<PageResponse<Product>> getSearchCriteriaPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                       @RequestParam(value = "size", defaultValue = "20") int size,
                                                                       @RequestParam(value = "filterOr", required = false) String filterOr,
                                                                       @RequestParam(value = "filterAnd", required = false) String filterAnd,
                                                                       @RequestParam(value = "orders", required = false) String orders) {
        PageResponse<Product> response = new PageResponse<>();

        Pageable pageable = filterBuilderService.getPageable(size, page, orders);
        GenericFilterCriteriaBuilder filterCriteriaBuilder = new GenericFilterCriteriaBuilder();


        List<FilterCondition> andConditions = filterBuilderService.createFilterCondition(filterAnd);
        List<FilterCondition> orConditions = filterBuilderService.createFilterCondition(filterOr);

        Query query = filterCriteriaBuilder.addCondition(andConditions, orConditions);
        Page<Product> pg = productService.getPage(query, pageable);
        response.setPageStats(pg, pg.getContent());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * @param filterOr  string filter or conditions
     * @param filterAnd string filter and conditions
     * @return list of Product
     */
    @Override
    public ResponseEntity<List<Product>> getAllSearchCriteria(@RequestParam(value = "filterOr", required = false) String filterOr,
                                                              @RequestParam(value = "filterAnd", required = false) String filterAnd) {
        GenericFilterCriteriaBuilder filterCriteriaBuilder = new GenericFilterCriteriaBuilder();

        List<FilterCondition> andConditions = filterBuilderService.createFilterCondition(filterAnd);
        List<FilterCondition> orConditions = filterBuilderService.createFilterCondition(filterOr);

        Query query = filterCriteriaBuilder.addCondition(andConditions, orConditions);
        List<Product> products = productService.getAll(query);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") final String id) {
        final Optional<Product> product = productService.getProductById(id);
        return product.map(value -> ResponseEntity.ok(new ProductResponse(value)))
                .orElseGet(() -> ResponseEntity.internalServerError().build());
    }

    @Override
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") String id,
                                                 @RequestBody() ProductDto productDto) {
        final Optional<Product> product = productService.updateProduct(id, productDto);
        return product.map(value -> ResponseEntity.ok(new ProductResponse(value)))
                .orElseGet(() -> ResponseEntity.internalServerError().build());
    }
}
