package au.nab.productservice.repository.support;

import au.nab.productservice.repository.ResourceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;

public class ResourceRepositoryImpl<T, I extends Serializable> extends SimpleMongoRepository<T, I>
        implements ResourceRepository<T, I> {
    private MongoOperations mongoOperations;
    private MongoEntityInformation entityInformation;

    public ResourceRepositoryImpl(final MongoEntityInformation entityInformation,
                                  final MongoOperations mongoOperations) {
        super(entityInformation, mongoOperations);
        this.mongoOperations = mongoOperations;
        this.entityInformation = entityInformation;
    }

    @Override
    public Page<T> findAll(Query query, Pageable pageable) {
        Assert.notNull(query, "Query must not be null or empty");
        long total = mongoOperations.count(query, entityInformation.getJavaType(), entityInformation.getCollectionName());
        List<T> content = mongoOperations.find(query.with(pageable), entityInformation.getJavaType(), entityInformation.getCollectionName());
        return new PageImpl<T>(content, pageable, total);
    }

    @Override
    public List<T> findAll(Query query) {
        Assert.notNull(query, "Query must not be null");
        return mongoOperations.find(query, entityInformation.getJavaType(), entityInformation.getCollectionName());
    }
}
