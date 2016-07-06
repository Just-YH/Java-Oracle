package by.bsu.food_restaurant.service;

import by.bsu.food_restaurant.domain.Food;

import java.util.List;

/**
 * Created by evgeniy on 5.7.16.
 */
public interface IFoodService {
    public Long add(String foodType, String foodName, Long foodPrice) throws ServiceException;
    public Food findByTypeAndPrice(String foodType, Long price) throws ServiceException;
    public Food findType(Long ftId) throws ServiceException;
    public List<Food> SeeAll() throws ServiceException;
    public void edit(Long ft_Id, String foodName) throws ServiceException;
    public void delete(Long ft_id) throws ServiceException;
}
