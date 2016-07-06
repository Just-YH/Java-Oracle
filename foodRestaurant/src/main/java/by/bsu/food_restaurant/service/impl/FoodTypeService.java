package by.bsu.food_restaurant.service.impl;

import by.bsu.food_restaurant.dao.DAOException;
import by.bsu.food_restaurant.dao.IFoodDAO;
import by.bsu.food_restaurant.dao.IFoodTypeDAO;
import by.bsu.food_restaurant.dao.impl.FoodTypeDAO;
import by.bsu.food_restaurant.domain.FoodType;
import by.bsu.food_restaurant.service.IFoodTypeService;
import by.bsu.food_restaurant.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by evgeniy on 4.7.16.
 */

@Service("foodTypeService")
public class FoodTypeService implements IFoodTypeService {

    @Autowired
    private IFoodTypeDAO foodTypeDAO;
    @Autowired
    private IFoodDAO foodDAO;

    @Override
    public Long addType(String foodTypeName) throws ServiceException {
        Long ftId = null;
        //FoodType foodType;
        if(validate(foodTypeName)){
            FoodType foodType = new FoodType();
            foodType.setFoodType(foodTypeName);
            try {
                ftId = foodTypeDAO.create(foodType);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return ftId;
    }

    @Override
    public FoodType findType(Long ftId) throws ServiceException {
        FoodType foodtype=null;
        try {
            foodtype = foodTypeDAO.read(ftId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return foodtype;
    }

    @Override
    public List<FoodType> SeeAll() throws ServiceException {
        List<FoodType> food=null;
        try {
            food=foodTypeDAO.readAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return food;
    }

    @Override
    public void edit(Long ftId, String foodTypeName) throws ServiceException {
        try {
            FoodType foodType = new FoodType();
            foodType.setFoodType(foodTypeName);
            foodTypeDAO.update(ftId, foodType);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long ftId) throws ServiceException {
        try {
            foodDAO.delete(ftId);
            foodTypeDAO.delete(ftId);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    private boolean validate(String foodTypeName){
        if(foodTypeName != null && !foodTypeName.isEmpty() ){
            return true;
        }
        return false;
    }

}
