package com.example.demo;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Logged
public class CarBaseRepository {

//    public static void main(String[] args) {
//        getConnection();
//
//        CarBase carBase = new CarBase();
//
//        carBase.setBrand("oleg");
//        carBase.setColor(" ");
//        carBase.setModel(" ");
//        save(carBase);
//    }

    @Logged
    public static Connection getConnection() {
        log.info("getConnection () is Ok");
        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/employee";
        String car_base = "postgres";
        String password = "user";

        try {
            connection = DriverManager.getConnection(url, car_base, password);
            if (connection != null) {
                System.out.println("Connected to the PostgreSQL server successfully.");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
        return connection;
    }

    @Logged
    public static int save(CarBase carBase) {
        log.info("added new auto - start: carBase = {}", carBase);
        int status = 0;
        try {
            Connection connection = CarBaseRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert into car_base(brand,model,color) values (?,?,?)");
            ps.setString(1, carBase.getBrand());
            ps.setString(2, carBase.getColor());
            ps.setString(3, carBase.getModel());

            status = ps.executeUpdate();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        log.info("added new auto - end: carBase = {}", carBase);
        return status;
    }

    @Logged
    public static int update(CarBase carBase) {
        log.info("update auto - start: carBase = {}", carBase);
        int status = 0;

        try {
            Connection connection = CarBaseRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("update car_base set brand=?,model=?,color=? where number=?");
            ps.setString(1, carBase.getBrand());
            ps.setString(2, carBase.getColor());
            ps.setString(3, carBase.getModel());
            ps.setInt(4, carBase.getNumber());

            status = ps.executeUpdate();
            connection.close();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        log.info("update auto - stop: carBase = {}", carBase);
        return status;
    }

    @Logged
    public static int delete(int number) {

        int status = 0;
        log.info("auto delete - start: number = {}", number);
        try {
            Connection connection = CarBaseRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from car_base where number=?");
            ps.setInt(1, number);
            status = ps.executeUpdate();

            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        log.info("auto delete - end: number = {}", number);
        return status;
    }

    @Logged
    public static CarBase getCarBaseById(int carNumber) {
        log.info("get auto by carNumber - start: carNumber = {}", carNumber);
        CarBase carBase = new CarBase();

        try {
            Connection connection = CarBaseRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from car_base where number=?");
            ps.setInt(1, carNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                carBase.setNumber(rs.getInt(1));
                carBase.setBrand(rs.getString(2));
                carBase.setColor(rs.getString(3));
                carBase.setModel(rs.getString(4));
            }
            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        log.info("get auto by carNumber - end: carNumber = {}", carNumber);
        return carBase;
    }

    @Logged
    public static List<CarBase> getAllCarBases() {
        log.info("get all cars - start");
        List<CarBase> listCarBases = new ArrayList<>();

        try {
            Connection connection = CarBaseRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from car_base");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                CarBase carBase = new CarBase();

                carBase.setNumber(rs.getInt(1));
                carBase.setBrand(rs.getString(2));
                carBase.setColor(rs.getString(3));
                carBase.setModel(rs.getString(4));

                listCarBases.add(carBase);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("get all cars - end");
        return listCarBases;
    }
}
