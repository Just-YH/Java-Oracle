package by.bsu.food_restaurant.dao;

import by.bsu.food_restaurant.domain.FoodType;

/**
 * Created by evgeniy on 4.7.16.
 */
public interface IFoodTypeDAO extends GenericDAO<Long, FoodType>{
    public Long findIdByType(String foodType) throws DAOException;


}
