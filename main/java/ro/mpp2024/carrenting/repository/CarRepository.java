package ro.mpp2024.carrenting.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.mpp2024.carrenting.JdbcUtils;
import ro.mpp2024.carrenting.domain.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Properties;

public class CarRepository implements CarRepositoryInterface{
    public JdbcUtils dbUtils;
    private static final Logger logger= LogManager.getLogger();

    public CarRepository(Properties properties){
        logger.info("Initializing AdminDBRerpository with properties {}",properties);
        dbUtils = new JdbcUtils(properties);
    }
    @Override
    public Optional<Car> findOne(Long id) {
        logger.traceEntry("Find Car by id:{},id");
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preparedStatement = con.prepareStatement("select * from Car where id = ?"))
        {
            preparedStatement.setLong(1,id);
            try(ResultSet result = preparedStatement.executeQuery())
            {
                if(result.next())
                {
                    String  uniqueCode= result.getString("uniqueCode");
                    String  carMake= result.getString("carMake");
                    String  carModel= result.getString("carModel");
                    String  fuel= result.getString("fuel");
                    String  vin= result.getString("vin");
                    Long year = result.getLong("year");
                    Long km = result.getLong("km");
                    String  status= result.getString("status");
                    String  color= result.getString("color");
                    Long numberOfSeats = result.getLong("numberOfSeats");
                    Long power = result.getLong("power");

                    Car car = new Car(uniqueCode,carMake,carModel,fuel,vin,year,km,status,color,numberOfSeats,power);
                    car.setId(id);
                    logger.traceExit(car);
                    return Optional.of(car);
                }
            }
        }
        catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB " + ex);
        }
        logger.traceExit();
        return Optional.empty();
    }

    @Override
    public Iterable<Car> findAll() {
        logger.traceEntry("Get all Cars");
        Connection con = dbUtils.getConnection();
        ArrayList<Car> listCar = new ArrayList<>();
        try(PreparedStatement preparedStatement = con.prepareStatement("select * from Car"))
        {
            try(ResultSet result = preparedStatement.executeQuery())
            {
                if(result.next())
                {   Long id = result.getLong("id");
                    String  uniqueCode= result.getString("uniqueCode");
                    String  carMake= result.getString("carMake");
                    String  carModel= result.getString("carModel");
                    String  fuel= result.getString("fuel");
                    String  vin= result.getString("vin");
                    Long year = result.getLong("year");
                    Long km = result.getLong("km");
                    String  status= result.getString("status");
                    String  color= result.getString("color");
                    Long numberOfSeats = result.getLong("numberOfSeats");
                    Long power = result.getLong("power");
                    Car car = new Car(uniqueCode,carMake,carModel,fuel,vin,year,km,status,color,numberOfSeats,power);
                    car.setId(id);
                    listCar.add(car);
                }
            }
        }
        catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB " + ex);
        }
        logger.traceExit(listCar);
        return listCar;
    }

    @Override
    public Optional<Car> save(Car entity) {
        logger.traceEntry("Saving Car {}",entity);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preparedStatement = con.prepareStatement("insert into Car(uniqueCode,carMake,carModel,fuel,vin,year,km,status,color,numberofSeats,power) values(?,?,?,?,?,?,?,?,?,?,?)"))
        {
            preparedStatement.setString(1, entity.getuniqueCode());
            preparedStatement.setString(2, entity.getCarMake());
            preparedStatement.setString(3, entity.getCarModel());
            preparedStatement.setString(4, entity.getFuel());
            preparedStatement.setString(5, entity.getVin());
            preparedStatement.setLong(6,entity.getYear());
            preparedStatement.setLong(7,entity.getKm());
            preparedStatement.setString(8, entity.getStatus());
            preparedStatement.setString(9, entity.getColor());
            preparedStatement.setLong(10,entity.getNumberOfSeats());
            preparedStatement.setLong(11,entity.getPower());

            int result = preparedStatement.executeUpdate();
            logger.trace("Saved {} Car instances",result);
            return Optional.of(entity);

        }
        catch(SQLException ex)
        {
            logger.error(ex);
            System.err.println("Error DB "+ ex);
        }
        logger.traceExit();
        return Optional.empty();    }

    @Override
    public Optional<Car> delete(Car entity) {
        logger.traceEntry("Deleting Car: {}", entity);
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("DELETE FROM Car WHERE uniqueCode = ?")) {
            preStmt.setString(1, entity.getuniqueCode());

            int rowsDeleted = preStmt.executeUpdate();
            if (rowsDeleted > 0) {
                logger.trace("Car {} deleted successfully", entity);
                // Return the deleted Car entity if needed
                return Optional.of(entity);
            } else {
                logger.warn("No Car found with username: {}", entity.getuniqueCode());
                return Optional.empty();
            }
        } catch (SQLException ex) {
            logger.error("Error while deleting Car: {}", entity, ex);
            System.err.println("Error DB " + ex);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Car> update(Car entity) {
        return Optional.empty();
    }

    @Override
    public Long findCarUniqueCode(String uniqueCode) {
        logger.traceEntry("Find Car by uniqueCode{}",uniqueCode);
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preparedStatement = con.prepareStatement("select id from Car where uniqueCode=?"))
        {
            preparedStatement.setString(1,uniqueCode);
            try(ResultSet result = preparedStatement.executeQuery())
            {
                if(result.next())
                {   Long id = result.getLong("id");
                    return id;
                }
            }
        }
        catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB " + ex);
        }
        logger.traceExit();
        return null;
    }
}
