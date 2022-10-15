package com.probitacademy.biblioteka.dao;
import java.util.List;
public interface Dao<T> {
public T get(long id) throws Exception;
public List<T> getAll() throws Exception;
public int save(T record) throws Exception;
public int update(T record) throws Exception;
public int delete(T record) throws Exception;
}
