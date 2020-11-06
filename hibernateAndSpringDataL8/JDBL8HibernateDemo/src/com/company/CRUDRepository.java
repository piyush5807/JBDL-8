package com.company;

public interface CRUDRepository<T> {
    void create(T t);

    Object read(Class tClass, Long id);

    void update(Long id, Object newObject, Class tClass) throws Exception;


    void delete(Long id, Object obj, Class tClass) throws Exception;
}
