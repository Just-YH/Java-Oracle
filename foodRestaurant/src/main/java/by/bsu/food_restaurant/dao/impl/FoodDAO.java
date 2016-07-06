package by.bsu.food_restaurant.dao.impl;

import by.bsu.food_restaurant.dao.DAOException;
import by.bsu.food_restaurant.dao.IFoodDAO;
import by.bsu.food_restaurant.domain.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by evgeniy on 5.7.16.
 */
@Component
public class FoodDAO implements IFoodDAO {

    private static final String SQL_CREATE_FOOD= "INSERT INTO FOOD (F_ID,FT_ID,F_NAME,F_PRICE) VALUES (null, ?, ?, ?)";
    private static final String SQL_READ_FOOD = " SELECT * FROM FOOD WHERE FT_ID=? AND F_PRICE=?";
    private static final String SQL_READ_FOOD_EASY = " SELECT * FROM FOOD WHERE F_ID=?";
    private static final String SQL_READ_EVERYTHING = " SELECT * FROM FOOD ";
    private static final String SQL_UPDATE_FOOD = " UPDATE FOOD SET F_NAME=? WHERE FT_ID=? ";
    private static final String SQL_DELETE_ROW = " DELETE FROM FOOD WHERE FT_ID=?";

    private static final String COLUMN_NAME_F_ID = "F_ID";
    private static final String COLUMN_NAME_FT_ID = "FT_ID";
    private static final String COLUMN_NAME_F_NAME = "F_NAME";
    private static final String COLUMN_NAME_F_PRICE = "F_PRICE";

    @Autowired
    private DataSource dataSource;

    @Override
    public Long create(Food food) throws DAOException {
        Long Id = null;
        int countRows = 0;
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try (PreparedStatement ps = connection.prepareStatement(SQL_CREATE_FOOD, new String[] {COLUMN_NAME_F_ID})) {
            ps.setLong(1, food.getfId());
            ps.setString(2, food.getName());
            ps.setLong(3, food.getPrice());
            countRows = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            Id = rs.getLong(1);
        } catch (SQLException e) {
            throw new DAOException(e + "\nCan not create raw : " + food);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }

        /**
         * Because: preparedStatement returns either the row count for
         * SQL Data Manipulation Language (DML) statements or 0 for
         * SQL statements that return nothing
         */
        if(countRows == 0)
            throw new DAOException("Didn't create author.");

        return Id;
    }

    @Override
    public Food read(Long f_id) throws DAOException {
        Food food = null;
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try (PreparedStatement ps = connection.prepareStatement(SQL_READ_FOOD_EASY)) {
            ps.setLong(1,f_id);
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){
                food = new Food();
                food.setId(f_id);
                food.setfId(rs.getLong(COLUMN_NAME_FT_ID));
                food.setName(rs.getString(COLUMN_NAME_F_NAME));
                food.setPrice(rs.getLong(COLUMN_NAME_F_PRICE));
            }
        } catch (SQLException e) {
            throw new DAOException(e + "\nCan not find food type: " + f_id);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }

        /**
         * We check null because if there is no such author, exception will not be thrown and
         * result set will be empty. That is why author will not ve initialized.
         */
        if(food == null){
            throw new DAOException("Food_id doesn't exist.");
        }

        return food;
    }

    @Override
    public Food read(Long ft_id, Long price) throws DAOException {
        Food food = null;
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try (PreparedStatement ps = connection.prepareStatement(SQL_READ_FOOD)) {
            ps.setLong(1,ft_id);
            ps.setLong(2, price);
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){
                food = new Food();
                food.setId(rs.getLong(COLUMN_NAME_F_ID));
                food.setfId(ft_id);
                food.setName(rs.getString(COLUMN_NAME_F_NAME));
                food.setPrice(price);
            }
        } catch (SQLException e) {
            throw new DAOException(e + "\nCan not find food type: " + ft_id);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }

        /**
         * We check null because if there is no such author, exception will not be thrown and
         * result set will be empty. That is why author will not ve initialized.
         */
        if(food == null){
            throw new DAOException("Food with this type or price doesn't exist");
        }

        return food;
    }

    @Override
    public List<Food> readAll() throws DAOException {
        List<Food> foods = null;
        Food food = null;
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try (PreparedStatement ps = connection.prepareStatement(SQL_READ_EVERYTHING)) {
            ResultSet rs = ps.executeQuery();
            foods=new ArrayList<>();
            while (rs.next()) {
                food=new Food();
                food.setId(rs.getLong(COLUMN_NAME_F_ID));
                food.setfId(rs.getLong(COLUMN_NAME_FT_ID));
                food.setName(rs.getString(COLUMN_NAME_F_NAME));
                food.setPrice(rs.getLong(COLUMN_NAME_F_PRICE));
                foods.add(food);
            }
        } catch (SQLException e) {
            throw new DAOException(e + "\nCan not find any info: ");
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }

        return foods;
    }

    @Override
    public void update(Long f_Id, Food food) throws DAOException {
        int countRows = 0;
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_FOOD)) {
            ps.setLong(2,f_Id);
            ps.setString(1, food.getName());
            countRows = ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e + "\nCan not update type with Ft_id : " + f_Id + " to Name : " + food.getName());
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }

        if(countRows == 0)
            throw new DAOException("Didn't update FoodType.");
    }

    @Override
    public void delete(Long ft_id) throws DAOException {
        int countRows = 0;
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try (PreparedStatement ps = connection.prepareStatement(SQL_DELETE_ROW)) {
            ps.setLong(1, ft_id);
            countRows = ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e + "\nCan not delete food with id : " + ft_id);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
        if(countRows == 0)
            throw new DAOException("Didn't delete food.");
    }

}
