package by.bsu.food_restaurant.service.impl;

import by.bsu.food_restaurant.dao.DAOException;
import by.bsu.food_restaurant.dao.IFoodDAO;
import by.bsu.food_restaurant.dao.IFoodTypeDAO;
import by.bsu.food_restaurant.dao.impl.FoodDAO;
import by.bsu.food_restaurant.dao.impl.FoodTypeDAO;
import by.bsu.food_restaurant.domain.Food;
import by.bsu.food_restaurant.domain.FoodType;
import by.bsu.food_restaurant.service.IFoodService;
import by.bsu.food_restaurant.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by evgeniy on 5.7.16.
 */
@Service("foodService")
public class FoodService implements IFoodService {

    @Autowired
    private IFoodDAO foodDAO;
    @Autowired
    private IFoodTypeDAO foodTypeDAO;

    @Override
    @Transactional
    public Long add(String foodType, String foodName, Long foodPrice) throws ServiceException {
        Long id = null;
        Long ft_id=checkType(foodType);
        if(validate(foodName)){
            Food food = new Food();
            food.setfId(ft_id);
            food.setName(foodName);
            food.setPrice(foodPrice);
            try {
                id = foodDAO.create(food);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return id;
    }

    @Override
    public Food findByTypeAndPrice(String foodType, Long price) throws ServiceException {
        Food food=null;
        try {
            Long ft_id=checkTypeId(foodType);
            if(ft_id!=null)
            food=foodDAO.read(ft_id,price);
            else
                throw new ServiceException("We don't have " + foodType );
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return food;
    }

    @Override
    public Food findType(Long ftId) throws ServiceException {
        Food food=null;
        try {
            food = foodDAO.read(ftId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return food;
    }

    @Override
    public List<Food> seeAll() throws ServiceException {
        List<Food> food=null;
        try {
            food=foodDAO.readAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return food;
    }

    @Override
    public void edit(Long ft_Id, String foodName) throws ServiceException {
        try {
            Food food = new Food();
            food.setName(foodName);
            foodDAO.update(ft_Id, food);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long ft_id) throws ServiceException {
        try {
            foodDAO.delete(ft_id);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    private boolean validate(String foodName){
        if(foodName != null && !foodName.isEmpty() ){
            return true;
        }
        return false;
    }

    private Long checkType(String foodType) {
        Long id=null;
        try {
            id=checkTypeId(foodType);
            if(id==null) {
                FoodType ft=new FoodType();
                ft.setFoodType(foodType);
                id=foodTypeDAO.create(ft);
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return id;
    }

    private Long checkTypeId(String foodType) throws DAOException {
        return foodTypeDAO.findIdByType(foodType);
    }
}
