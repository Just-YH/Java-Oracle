package by.bsu.food_restaurant.dao.impl;

/**
 * Created by evgeniy on 4.7.16.
 */

import by.bsu.food_restaurant.dao.DAOException;
import by.bsu.food_restaurant.dao.IFoodTypeDAO;
import by.bsu.food_restaurant.domain.FoodType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Component
public class FoodTypeDAO implements IFoodTypeDAO {

    private static final String SQL_CREATE_FOOD_TYPE = " INSERT INTO FOOD_TYPE (FT_ID, FT_TYPE) VALUES (null, ?) ";
    private static final String SQL_READ_FOOD_TYPE = " SELECT * FROM FOOD_TYPE WHERE FT_ID=? ";
    private static final String SQL_READ_EVERYTHING = " SELECT * FROM FOOD_TYPE ";
    private static final String SQL_UPDATE_TYPE = " UPDATE FOOD_TYPE SET FT_TYPE=? WHERE FT_ID=? ";
    private static final String SQL_DELETE_ROW = " DELETE FROM FOOD_TYPE WHERE FT_ID=?";
    private static final String SQL_FIND_ID = " SELECT FT_ID FROM FOOD_TYPE WHERE FT_TYPE=?";

    private static final String COLUMN_NAME_FT_ID = "FT_ID";
    private static final String COLUMN_NAME_FT_TYPE = "FT_TYPE";


    @Autowired
    private DataSource dataSource;

    @Override
    public Long create( FoodType foodType) throws DAOException {

        Long ftId = null;
        int countRows = 0;
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try (PreparedStatement ps = connection.prepareStatement(SQL_CREATE_FOOD_TYPE, new String[] {COLUMN_NAME_FT_ID})) {
            ps.setString(1, foodType.getFoodType());
            countRows = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            ftId = rs.getLong(1);
        } catch (SQLException e) {
            throw new DAOException(e + "\nCan not create raw : " + foodType);
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

        return ftId;
    }


    @Override
    public FoodType read(Long ftId) throws DAOException {
        FoodType foodtype = null;
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try (PreparedStatement ps = connection.prepareStatement(SQL_READ_FOOD_TYPE)) {
            ps.setLong(1,ftId);
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){
                foodtype = new FoodType();
                foodtype.setId(ftId);
                foodtype.setFoodType(rs.getString(COLUMN_NAME_FT_TYPE));
            }
        } catch (SQLException e) {
            throw new DAOException(e + "\nCan not find food type: " + ftId);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }

        /**
         * We check null because if there is no such author, exception will not be thrown and
         * result set will be empty. That is why author will not ve initialized.
         */
        if(foodtype == null){
            throw new DAOException("Author with id: \" + authorId + \" doesn't exist.");
        }

        return foodtype;
    }


    @Override
    public List<FoodType> readAll() throws DAOException {
        List<FoodType> foodtype = null;
        FoodType food = null;
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try (PreparedStatement ps = connection.prepareStatement(SQL_READ_EVERYTHING)) {
            ResultSet rs = ps.executeQuery();
            foodtype=new ArrayList<>();
            while (rs.next()) {
                food=new FoodType();
                food.setId(rs.getLong(COLUMN_NAME_FT_ID));
                food.setFoodType(rs.getString(COLUMN_NAME_FT_TYPE));
                foodtype.add(food);
            }
        } catch (SQLException e) {
            throw new DAOException(e + "\nCan not find any info: ");
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }

        return foodtype;
    }

        @Override
    public void update(Long ftId, FoodType foodType) throws DAOException {

            int countRows = 0;
            Connection connection = DataSourceUtils.getConnection(dataSource);
            try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_TYPE)) {
                ps.setLong(2,ftId);
                ps.setString(1, foodType.getFoodType());
                countRows = ps.executeUpdate();
            } catch (SQLException e) {
                throw new DAOException(e + "\nCan not update type with id : " + ftId + " to type : " + foodType.getFoodType());
            } finally {
                DataSourceUtils.releaseConnection(connection, dataSource);
            }

            if(countRows == 0)
                throw new DAOException("Didn't update FoodType.");
        }



    @Override
    public void delete(Long ftId) throws DAOException {
        int countRows = 0;
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try (PreparedStatement ps = connection.prepareStatement(SQL_DELETE_ROW)) {
           ps.setLong(1, ftId);
            countRows = ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e + "\nCan not delete FoodType with id : " + ftId);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }

        /**
         * Because: preparedStatement returns either the row count for
         * SQL Data Manipulation Language (DML) statements or 0 for
         * SQL statements that return nothing
         */
        if(countRows == 0)
            throw new DAOException("Didn't delete news.");

    }

    @Override
    public Long findIdByType(String foodType) throws DAOException {
        Connection connection = DataSourceUtils.getConnection(dataSource);
        Long ftId=null;
        try (PreparedStatement ps = connection.prepareStatement(SQL_FIND_ID)) {
            ps.setString(1, foodType);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                ftId=rs.getLong(COLUMN_NAME_FT_ID);
        } catch (SQLException e) {
            throw new DAOException(e + "\nCan not delete Foodtype with id : " + ftId);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
        return ftId;


    }


}

