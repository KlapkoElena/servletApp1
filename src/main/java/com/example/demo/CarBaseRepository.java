package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public static Connection getConnection() {

        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/employee";
        String car_base = "postgres";
        String password = "1234";

        try {
            connection = DriverManager.getConnection(url, car_base, password);
            if (connection != null) {
                System.out.println("Connected to the PostgreSQL server successfully.");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    public static int save(CarBase carBase) {
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
        } finally {
            try {
                getConnection().close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return status;
    }

    public static int update(CarBase carBase) {

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
        } finally {
            try {
                getConnection().close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return status;
    }

    public static int delete(int number) {

        int status = 0;

        try {
            Connection connection = CarBaseRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from car_base where number=?");
            ps.setInt(1, number);
            status = ps.executeUpdate();

            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            try {
                getConnection().close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return status;
    }

    public static CarBase getCarBaseById(int carNumber) {

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
        } finally {
            try {
                getConnection().close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return carBase;
    }

    public static List<CarBase> getAllCarBases() {

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
        } finally {
            try {
                getConnection().close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return listCarBases;
    }
}
