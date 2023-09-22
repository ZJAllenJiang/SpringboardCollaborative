package org.tsc.dao.impl;

import io.awspring.cloud.dynamodb.DynamoDbOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tsc.dao.DynamoDbDao;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
public  class DynamoDbDaoImpl implements DynamoDbDao {

    @Autowired
    private DynamoDbOperations dynamoDbOperations;


    @Override
    public <S> void save(S s) {
        dynamoDbOperations.save(s);
    }

    @Override
    public <S> void saveAll(List<S> ss) {
        for (S s:
             ss) {
            save(s);
        }
    }


    @Override
    public <S> S get(String id, Class<S> clazz) {
        PageIterable<S> navigatorPageIterable = dynamoDbOperations.query(
                QueryEnhancedRequest.builder()
                        .queryConditional(
                                QueryConditional.keyEqualTo(Key.builder().partitionValue(id).build()))
                        .build(),
                clazz);
        return navigatorPageIterable.items().stream().findFirst().orElse(null);
    }

    @Override
    public <S> void update(S s) {
        dynamoDbOperations.update(s);
    }

    @Override
    public <S> void delete(S s) {
        dynamoDbOperations.delete(s);
    }

    @Override
    public <S> List<S> getAll(Class<S> clazz) {
        return dynamoDbOperations.scanAll(clazz).items().stream().collect(Collectors.toList());
    }
}
