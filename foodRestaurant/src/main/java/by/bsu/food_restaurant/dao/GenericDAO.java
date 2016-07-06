package by.bsu.food_restaurant.dao;

import java.util.List;


/**
 * Created by evgeniy on 4.7.16.
 */
public interface  GenericDAO<K,T> {


    K create(T entity) throws DAOException;

    T read(K key) throws DAOException;


    List<T> readAll() throws DAOException;


    void update(K key, T entity) throws DAOException;


    void delete(K key) throws DAOException;

}

