package au.nab.productservice.controller.impl;

import au.nab.productservice.controller.ProductController;
import au.nab.productservice.dtos.FilterCondition;
import au.nab.productservice.dtos.http.BaseResponseDto;
import au.nab.productservice.dtos.ProductDto;
import au.nab.productservice.dtos.http.PageResponse;
import au.nab.productservice.dtos.http.ProductResponse;
import au.nab.productservice.entities.Product;
import au.nab.productservice.repository.support.GenericFilterCriteriaBuilder;
import au.nab.productservice.service.FilterBuilderService;
import au.nab.productservice.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductControllerImpl implements ProductController {
    private final ProductService productService;
    private final FilterBuilderService filterBuilderService;

    public ProductControllerImpl(final ProductService productService,
                                 final FilterBuilderService filterBuilderService) {
        this.productService = productService;
        this.filterBuilderService = filterBuilderService;
    }

    @Override
    public ResponseEntity<BaseResponseDto> addProduct(@RequestBody() ProductDto product) {
        try {
            final ProductResponse response = productService.addProduct(product);
            return ResponseEntity.ok(BaseResponseDto
                    .builder()
                            .code(HttpStatus.CREATED.value())
                            .message(HttpStatus.CREATED.getReasonPhrase())
                            .data(response)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.of(Optional.of(BaseResponseDto
                    .<Product>builder()
                            .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .message(e.getMessage())
                            .data(null)
                    .build()));
        }
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
    public ResponseEntity<PageResponse<Product>> getSearchCriteriaPage(int page, int size, String filterOr, String filterAnd, String orders) {
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
    public ResponseEntity<List<Product>> getAllSearchCriteria(String filterOr, String filterAnd) {
        GenericFilterCriteriaBuilder filterCriteriaBuilder = new GenericFilterCriteriaBuilder();

        List<FilterCondition> andConditions = filterBuilderService.createFilterCondition(filterAnd);
        List<FilterCondition> orConditions = filterBuilderService.createFilterCondition(filterOr);

        Query query = filterCriteriaBuilder.addCondition(andConditions, orConditions);
        List<Product> employees = productService.getAll(query);

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
