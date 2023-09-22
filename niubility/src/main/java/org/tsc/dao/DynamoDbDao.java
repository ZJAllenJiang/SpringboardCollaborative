package org.tsc.dao;

import java.util.List;

public interface DynamoDbDao {

    <S> void save(S s);

    <S> void saveAll(List<S> ss);

    <S> S get(String id, Class<S> clazz);

    <S> void update(S s);

    <S> void delete(S s);

    <S> List<S> getAll(Class<S> clazz);

}
