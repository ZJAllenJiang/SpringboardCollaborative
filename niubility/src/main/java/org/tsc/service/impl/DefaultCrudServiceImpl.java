package org.tsc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tsc.dao.DynamoDbDao;
import org.tsc.service.CrudService;

import java.util.List;

@Service
public class DefaultCrudServiceImpl implements CrudService {

    @Autowired
    DynamoDbDao dynamoDbDao;

    @Override
    public <S> void save(S s) {
        dynamoDbDao.save(s);
    }

    @Override
    public <S> S get(String id, Class<S> clazz) {
        return dynamoDbDao.get(id, clazz);
    }

    @Override
    public <S> void update(S s) {
        dynamoDbDao.update(s);
    }

    @Override
    public <S> void delete(S s) {
        dynamoDbDao.delete(s);
    }

    @Override
    public <S> List<S> getAll(Class<S> clazz) {
        return dynamoDbDao.getAll(clazz);
    }
    
}
