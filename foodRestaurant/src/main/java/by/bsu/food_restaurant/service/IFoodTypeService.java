package by.bsu.food_restaurant.service;

import by.bsu.food_restaurant.domain.FoodType;

import java.util.List;

/**
 * Created by evgeniy on 4.7.16.
 */
public interface IFoodTypeService {

    public Long addType(String foodTypeName) throws ServiceException;
    public FoodType findType(Long ftId) throws ServiceException;
    public List<FoodType> SeeAll() throws ServiceException;
    public void edit(Long ftId, String foodTypeName) throws ServiceException;
    public void delete(Long ftId) throws ServiceException;
}
