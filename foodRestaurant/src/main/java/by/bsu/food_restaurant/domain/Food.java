package by.bsu.food_restaurant.domain;

/**
 * Created by evgeniy on 5.7.16.
 */
public class Food {
    private Long id;
    private Long fId;
    private String name;
    private Long price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Food food = (Food) o;

        if (!id.equals(food.id)) return false;
        if (!fId.equals(food.fId)) return false;
        if (!name.equals(food.name)) return false;
        return price.equals(food.price);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + fId.hashCode();
        result = 51 * result + name.hashCode();
        result = 71 * result + price.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", fId=" + fId +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
