package controller;

import dao.FlightDao;
import dbConnection.DBConnection;
import model.Flight;

import java.sql.Connection;
import java.util.List;

public class FlightController {

    private FlightDao flightDao;

   public FlightController(){
        Connection connection = DBConnection.getConnection();
        flightDao = new FlightDao(connection);
    }

    public boolean addFlight(Flight flight){ return flightDao.insert(flight);}

    public List<Flight> getFlights(){ return flightDao.select();}

    public boolean deleteFlightbyId(int id){return flightDao.deleteById(id);};

   public int getLastId(){return flightDao.getLastID();}
}
