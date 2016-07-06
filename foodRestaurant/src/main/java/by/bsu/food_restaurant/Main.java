package by.bsu.food_restaurant;

import by.bsu.food_restaurant.dao.impl.FoodTypeDAO;
import by.bsu.food_restaurant.domain.Food;
import by.bsu.food_restaurant.domain.FoodType;
import by.bsu.food_restaurant.service.IFoodService;
import by.bsu.food_restaurant.service.IFoodTypeService;
import by.bsu.food_restaurant.service.ServiceException;
import by.bsu.food_restaurant.service.impl.FoodService;
import by.bsu.food_restaurant.service.impl.FoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.function.LongFunction;

/**
 * Created by evgeniy on 4.7.16.
 */
public class Main {

    private static final String CONTEXT_FILE = "ApplicationContext.xml";
    private static final ApplicationContext CONTEXT = new ClassPathXmlApplicationContext(CONTEXT_FILE);
    private static final IFoodTypeService FOOD_TYPE_SERVICE = CONTEXT.getBean("foodTypeService", FoodTypeService.class);
    private static final IFoodService FOOD_SERVICE = CONTEXT.getBean("foodService", FoodService.class);


    public static void main(String[] args) throws ServiceException {

       /* String foodTypeName = "Soup";
        Long ftId = FOOD_TYPE_SERVICE.addType(foodTypeName);
        System.out.println("Id=" + ftId);
*/
       /* Long id=3L;
        FoodType first=FOOD_TYPE_SERVICE.findType(id);
        System.out.println(first);
*/
       /* List<FoodType> foodList=FOOD_TYPE_SERVICE.SeeAll();
        for (FoodType ft : foodList) {
            System.out.println(ft);
        }
*/
       /* String foodTypeName = "Ice Cream";
        Long id=6L;
        FOOD_TYPE_SERVICE.edit(id,foodTypeName);
*/
       /* Long id=5L;
        FOOD_TYPE_SERVICE.delete(id);
*/
       /* String foodName = "Beaf";
        Long foodPrice=9L;
        String foodType="Meats";
        Long Id = FOOD_SERVICE.add(foodType, foodName, foodPrice);
        System.out.println("Id=" + Id);
*/
       /* String foodType="Meat";
        Long price=9L;
        Food first=FOOD_SERVICE.findByTypeAndPrice(foodType,price);
        System.out.println(first);
*/
       /* Long id=3L;
        Food first=FOOD_SERVICE.findType(id);
        System.out.println(first);
*/
       /* List<Food> foodList=FOOD_SERVICE.SeeAll();
        for (Food ft : foodList) {
            System.out.println(ft);
        }
*/
       /* String foodName = "Salmon with Lemon";
        Long id=21L;
        FOOD_SERVICE.edit(id,foodName);
*/
        Long id=21L;
        FOOD_TYPE_SERVICE.delete(id);
    }
}
