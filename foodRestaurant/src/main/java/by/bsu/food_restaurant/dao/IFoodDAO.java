package by.bsu.food_restaurant.dao;

import by.bsu.food_restaurant.domain.Food;

/**
 * Created by evgeniy on 5.7.16.
 */
public interface IFoodDAO extends GenericDAO<Long, Food> {
    public Food read(Long ft_id, Long f_price) throws DAOException;
}
