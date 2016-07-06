package by.bsu.food_restaurant.domain;

/**
 * Created by evgeniy on 4.7.16.
 */
public class FoodType {
    private Long id;
    private String foodType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    @Override
    public String toString() {
        return "FoodType{" +
                "id=" + id +
                ", foodType='" + foodType + '\'' +
                '}';
    }

    @Override
    public int hashCode(){
        int hash = id.hashCode()*3 + foodType.hashCode()*5;
        return hash;
    }
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(this.getClass() != obj.getClass()){
            return false;
        }
        FoodType o = (FoodType) obj;
        if(! this.foodType.equals(o.foodType)){
            return false;
        }
        return true;
    }

}
