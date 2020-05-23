package gui;

import controller.FlightController;
import model.Flight;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;

public class MainPage extends JFrame {

    private JLabel timeText;
    ExecutorService service = Executors.newSingleThreadExecutor();
    JPanel panel = new JPanel(new GridLayout(1, 1));
    JTable table;

    MainPage()
    {
        initDefaults();
     //  initDateAndTime();

        add(panel);
        setVisible(true);

    }

    private void initDefaults() {
        setTitle("Main Page");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initDateAndTime(){
        timeText = new JLabel();
        panel.add(timeText);
        service.execute(timeThread);
    }

    void initTable()
    {
        String []collumNames={"Source","Destination","Departure Time","Arrival Time","Days","Price"};
        FlightController flightController = new FlightController();
        List<Flight>flights = flightController.getFlights();
        Object[][] flightsData= new Object[10][6];
        for(int i=0;i<flights.size();i++)
        {
            Flight flight = flights.get(i);
            flightsData[i][0]= flight.getSource();
            flightsData[i][1]= flight.getDestination();
            flightsData[i][2]=flight.getDeparture();
            flightsData[i][3]=flight.getArrival();
            flightsData[i][4]= flight.getDay();
            flightsData[i][5]= flight.getPrice();

        }
        table=new JTable(flightsData, collumNames);
        panel.add(table);
    }

    private Runnable timeThread = () ->{
        try {

            LocalDateTime time = LocalDateTime.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            timeText.setText(dtf.format(time));
            System.out.println("salut");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    };

    private void closeWindow(){
        service.shutdown();
        dispose();
    }

}
