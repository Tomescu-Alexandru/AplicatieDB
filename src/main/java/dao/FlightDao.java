package dao;


import model.Flight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlightDao {


    Connection connection;

    PreparedStatement insertQuery;
    PreparedStatement selectQueryId;
    PreparedStatement deleteQueryId;
    PreparedStatement lastId;

    public FlightDao(Connection connection){
        this.connection = connection;

        try {
            insertQuery = connection.prepareStatement("INSERT INTO flights VALUES(?,?,?,?,?,?,?)");
            selectQueryId = connection.prepareStatement("SELECT * FROM flights");
            deleteQueryId = connection.prepareStatement("DELETE * FROM flights where id_cursa=?");
            lastId = connection.prepareStatement("SELECT id FROM flights ORDER BY id DESC LIMIT 1");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean insert(Flight flight){
        try {
            insertQuery.setInt(1,flight.getId());
            insertQuery.setString(2,flight.getSource());
            insertQuery.setString(3, flight.getDestination());
            insertQuery.setString(4,flight.getDeparture().toString());
            insertQuery.setString(5,flight.getArrival().toString());
            insertQuery.setString(6,flight.getDay());
            insertQuery.setInt(7,flight.getPrice());

            return insertQuery.executeUpdate()!=0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Flight> select(){
        try {
            ResultSet resultSet = selectQueryId.executeQuery();
            List<Flight> flights = new ArrayList<>();

            while(resultSet.next()){
                Flight flight = new Flight(
                        resultSet.getInt("id"),
                        resultSet.getString("source"),
                        resultSet.getString("destination"),
                        resultSet.getString("departure"),
                        resultSet.getString("arrival"),
                        resultSet.getString("day"),
                        resultSet.getInt("price")
                );
                flights.add(flight);
            }

            return flights;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    public boolean deleteById(int id){
        try {
            deleteQueryId.setInt(1,id);

            return deleteQueryId.executeUpdate()!=0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getLastID(){
        try {
            ResultSet set = lastId.executeQuery();
            set.next();
            return set.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
