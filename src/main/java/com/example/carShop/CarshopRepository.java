package com.example.carShop;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Logged
public class CarshopRepository {

    public static void main(String[] args) {
        getAllisCarshops();
    }

    @Logged
    public static Connection getConnection() {
        log.info("getConnection() is Ok");
        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/employee";
        String user = "postgres";
        String password = "wildfly";

        try {
            connection = DriverManager.getConnection(url, user, password);
            if (connection == null) {
                System.out.println("Failed to make connection!");
            } else {
                System.out.println("Connected to the PostgreSQL server successfully.");
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
        return connection;
    }

    @Logged
    public static int save(com.example.carShop.Carshop carshop) {
        log.info("added new auto part - start: carshop = {}", carshop);
        int status = 0;
        try {
            Connection connection = CarshopRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert into auto_parts(name,price,availability) values (?,?,?)");
            ps.setString(1, carshop.getName());
            ps.setString(2, carshop.getPrice());
            ps.setString(3, carshop.getAvailability());

            status = ps.executeUpdate();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        log.info("added new auto part - end: status = {}", status);
        return status;
    }

    @Logged
    public static int update(com.example.carShop.Carshop carshop) {
        log.info("update auto part - start: carshop = {}", carshop);
        int status = 0;

        try {
            Connection connection = CarshopRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("update auto_parts set name=?,price=?,availability=? where code=?");
            ps.setString(1, carshop.getName());
            ps.setString(2, carshop.getPrice());
            ps.setString(3, carshop.getAvailability());
            ps.setInt(4, carshop.getCode());

            status = ps.executeUpdate();
            connection.close();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        log.info("update auto part - end: status = {}", status);
        return status;
    }

    @Logged
    public static int delete(int code) {
        log.info("auto part deleted - start: code = {}", code);
        int status = 0;

        try {
            Connection connection = CarshopRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from auto_parts where code=?");
            ps.setInt(1, code);
            status = ps.executeUpdate();

            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        log.info("auto part deleted - end: status = {}", status);
        return status;
    }

    @Logged
    public static int isDeleted(int code) {
        log.info("auto part isDeleted - start: code = {}", code);
        int status = 0;

        try {
            Connection connection = CarshopRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "update auto_parts set isdeleted = true where code=?");
            ps.setInt(1, code);
            status = ps.executeUpdate();

            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        log.info("auto part isDeleted - end: status = {}", status);
        return status;
    }

    @Logged
    public static com.example.carShop.Carshop getCarshopByCode(int code) {
        log.info("get auto part by code - start: code = {}", code);
        com.example.carShop.Carshop carshop = new com.example.carShop.Carshop();

        try {
            Connection connection = CarshopRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "select * from auto_parts where code=? and isdeleted = false");
            ps.setInt(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                carshop.setCode(rs.getInt(1));
                carshop.setName(rs.getString(2));
                carshop.setPrice(rs.getString(3));
                carshop.setAvailability(rs.getString(4));

            }
            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        log.info("get auto part by code - end: carshop = {}", carshop);
        return carshop;
    }



    @Logged
    public static com.example.carShop.Carshop getCarByCode() {
        log.info("get auto part by code - start: code = {}");
        com.example.carShop.Carshop carshop = new com.example.carShop.Carshop();

        try {
            Connection connection = CarshopRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "select * from auto_parts where isdeleted = false");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                carshop.setCode(rs.getInt(1));
                carshop.setName(rs.getString(2));
                carshop.setPrice(rs.getString(3));
                carshop.setAvailability(rs.getString(4));

            }
            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        log.info("get auto part by code - end: carshop = {}", carshop);
        return carshop;
    }

    @Logged
    public static List<com.example.carShop.Carshop> getAllCarshops() {
        log.info("get all auto parts - start");
        List<com.example.carShop.Carshop> listCarshops = new ArrayList<>();

        try {
            Connection connection = CarshopRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from auto_parts");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                com.example.carShop.Carshop carshop = new com.example.carShop.Carshop();

                carshop.setCode(rs.getInt(1));
                carshop.setName(rs.getString(2));
                carshop.setPrice(rs.getString(3));
                carshop.setAvailability(rs.getString(4));
                carshop.setIsDeleted(rs.getBoolean(5));
                listCarshops.add(carshop);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("get all auto parts - end");
        return listCarshops;
    }

    @Logged
    public static List<com.example.carShop.Carshop> getCarshopByCountry() {
        log.info("get all auto parts - start");
        List<com.example.carShop.Carshop> listCarshops = new ArrayList<>();

        try {
            Connection connection = CarshopRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from auto_parts");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                com.example.carShop.Carshop carshop = new com.example.carShop.Carshop();

                carshop.setCode(rs.getInt(1));
                carshop.setName(rs.getString(2));
                carshop.setPrice(rs.getString(3));
                carshop.setAvailability(rs.getString(4));
                carshop.setIsDeleted(rs.getBoolean(5));
                carshop.setColor(rs.getString(6));
                carshop.setCountry(rs.getString(7));
                listCarshops.add(carshop);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("get all auto parts - end");
        return listCarshops;
    }

    @Logged
    public static List<com.example.carShop.Carshop> getAllChinaCarshops() {
        log.info("get all China auto parts - start");
        List<com.example.carShop.Carshop> listCarshops = new ArrayList<>();

        try {
            Connection connection = CarshopRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from auto_parts");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                com.example.carShop.Carshop carshop = new com.example.carShop.Carshop();

                carshop.setCode(rs.getInt(1));
                carshop.setName(rs.getString(2));
                carshop.setPrice(rs.getString(3));
                carshop.setAvailability(rs.getString(4));
                carshop.setIsDeleted(rs.getBoolean(5));
                listCarshops.add(carshop);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("get all China auto parts - end");
        return listCarshops;
    }

    @Logged
    public static List<com.example.carShop.Carshop> getAllisCarshops() {
        log.info("get all auto parts - start");
        List<com.example.carShop.Carshop> listCarshops = new ArrayList<>();

        try {
            Connection connection = CarshopRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from auto_parts where isdeleted = false");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                com.example.carShop.Carshop carshop = new com.example.carShop.Carshop();

                carshop.setCode(rs.getInt(1));
                carshop.setName(rs.getString(2));
                carshop.setPrice(rs.getString(3));
                carshop.setAvailability(rs.getString(4));
                carshop.setIsDeleted(rs.getBoolean(5));
                carshop.setColor(rs.getString(6));
                carshop.setCountry(rs.getString(7));
                if (carshop.getCountry().equals("cn")){
                    listCarshops.add(carshop);
                }

            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("get all auto parts - end");
        return listCarshops;
    }

    @Logged
    public static List<com.example.carShop.Carshop> getCarsForChina() {
        log.info("get all auto parts - start");
        List<com.example.carShop.Carshop> listCarshops = new ArrayList<>();

        try {
            Connection connection = CarshopRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from auto_parts");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                com.example.carShop.Carshop carshop = new com.example.carShop.Carshop();

                carshop.setCode(rs.getInt(1));
                carshop.setName(rs.getString(2));
                carshop.setPrice(rs.getString(3));
                carshop.setAvailability(rs.getString(4));
                carshop.setIsDeleted(rs.getBoolean(5));
                carshop.setColor(rs.getString(6));
                carshop.setCountry(rs.getString(7));
                if (carshop.getCountry().equals("cn")){
                    listCarshops.add(carshop);
                }

            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("get all auto parts - end");
        return listCarshops;
    }
}