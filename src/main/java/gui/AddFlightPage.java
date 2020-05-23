package gui;

import controller.FlightController;
import model.Flight;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class AddFlightPage extends JFrame{

    enum days {
            Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
    }

    int id;
    JLabel sourceLabel, destinationLabel, departureLabel, durationLabel, dayLabel,priceLabel;
    JTextField sourceText, destinationText, departureText, durationText, priceText;

    JButton cancelButon, addButton;
    EmptyBorder border = new EmptyBorder(0, 10, 0, 0);
    JPanel panel = new JPanel(new GridLayout(7,2));

    List<JCheckBox> daysCheckboxes= new ArrayList<>();

    AddFlightPage()
    {
        initDefaults();
        initSource();
        initDestination();
        initDeparture();
        initDuration();
        initDays();
        initPrice();
        initAddButton();
        initCancelButton();
        add(panel);
        setVisible(true);

    }

    private void initSource(){
        sourceLabel= new JLabel("Source");
        sourceLabel.setBorder(border);

        panel.add(sourceLabel);

        sourceText = new JTextField();
        panel.add(sourceText);
    }

    private void initDestination(){
        destinationLabel= new JLabel("Destination");
        destinationLabel.setBorder(border);

        panel.add(destinationLabel);

        destinationText = new JTextField();
        panel.add(destinationText);
    }

    private void initDeparture(){
        departureLabel= new JLabel("Departure Time (HH:MM)");
        departureLabel.setBorder(border);

        panel.add(departureLabel);

        departureText = new JTextField();
        panel.add(departureText);
    }

    private void initDuration(){
        durationLabel= new JLabel("Duration (HH:MM)");
        durationLabel.setBorder(border);

        panel.add(durationLabel);

        durationText = new JTextField();
        panel.add(durationText);
    }

    private void initDays(){

        dayLabel = new JLabel("Days");
        dayLabel.setBorder(border);
        panel.add(dayLabel);

        JPanel checkboxesPanel= new JPanel(new GridLayout(7,1));
        daysCheckboxes.add(new JCheckBox(days.Monday.toString()));
        daysCheckboxes.add(new JCheckBox(days.Tuesday.toString()));
        daysCheckboxes.add(new JCheckBox(days.Wednesday.toString()));
        daysCheckboxes.add(new JCheckBox(days.Tuesday.toString()));
        daysCheckboxes.add(new JCheckBox(days.Friday.toString()));
        daysCheckboxes.add(new JCheckBox(days.Saturday.toString()));
        daysCheckboxes.add(new JCheckBox(days.Sunday.toString()));

        for (JCheckBox daysCheckbox : daysCheckboxes)
            checkboxesPanel.add(daysCheckbox);

        panel.add(checkboxesPanel);

    }

    private void initPrice(){
        priceLabel = new JLabel("Price");
        priceLabel.setBorder(border);
        panel.add(priceLabel);

        priceText= new JTextField();
        panel.add(priceText);
    }

    private void initAddButton(){

        addButton = new JButton("Add");
        panel.add(addButton);

        addButton.addActionListener(e->{
            if(validateInput())
            {
                String arrivalTime = arrivalCalculation();
                if(arrivalTime != null)
                {
                    FlightController flightController = new FlightController();
                    id = flightController.getLastId()+1;
                    for (JCheckBox daysCheckbox : daysCheckboxes)
                    {
                        if(daysCheckbox.isSelected()){
                            String day= daysCheckbox.getText();
                            Flight flight = new Flight(id,sourceText.getText(),destinationText.getText(), departureText.getText(), arrivalTime, day, Integer.parseInt(priceText.getText()));
                            flightController.addFlight(flight);
                        }
                    }
                    id++;
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Flight must arrive in the same day");
                    durationText.setText("");
                    durationText.requestFocus();
                }

            }
        });
    }

    private void initCancelButton(){
        cancelButon = new JButton("Cancel");
        panel.add(cancelButon);

        cancelButon.addActionListener(e->{
            dispose();
            new MainPage();
        });
    }

    private void initDefaults() {
        setTitle("Add Flight");
        setSize(500,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private boolean validateInput(){
        if(!Validations.validLocation(sourceText.getText()))
        {
            JOptionPane.showMessageDialog(null,"Source must have at least 3 caracters");
            sourceText.setText("");
            sourceText.requestFocus();
            return false;
        }
        else if(!Validations.validLocation(destinationText.getText()))
        {
            JOptionPane.showMessageDialog(null,"Destination must have at least 3 caracters");
            destinationText.setText("");
            destinationText.requestFocus();
            return false;
        }
        else if(!Validations.validTime(departureText.getText(),"Departure"))
        {
            JOptionPane.showMessageDialog(null,"Departure must be as following HH:MM");
            departureText.setText("");
            departureText.requestFocus();
            return false;
        }
        else if(!Validations.validTime(durationText.getText()," "))
        {
            JOptionPane.showMessageDialog(null,"Duration must be as following HH:MM");
            durationText.setText("");
            durationText.requestFocus();
            return false;
        }
        else if(!validDays())
        {
            JOptionPane.showMessageDialog(null,"You must select at least one day");
            return false;
        }
        else if(!Validations.validPrice(priceText.getText()))
        {
            JOptionPane.showMessageDialog(null,"Price must be and integer");
            return false;
        }

        return true;
    }

    private List<Integer> intParser(String text){
        String []hoursAndMinutes = text.split(":");
        List<Integer> parsedValues = new ArrayList<>();
        parsedValues.add(Integer.parseInt(hoursAndMinutes[0]));
        parsedValues.add(Integer.parseInt(hoursAndMinutes[1]));

        return parsedValues;
    }

    private String arrivalCalculation() {
        List<Integer> departureTime = intParser(departureText.getText());
        List<Integer> duration = intParser(durationText.getText());
        Integer arrivalHour, arrivalMinute;

        arrivalMinute = departureTime.get(1)+duration.get(1);
        arrivalHour = departureTime.get(0)+duration.get(0);

        if(arrivalMinute>60)
        {
            arrivalHour+=arrivalMinute/60;
            arrivalMinute%=60;
        }

        if(arrivalHour >= 24) {
            return null;
        }

        String s;
        if(arrivalMinute>10)
        s = arrivalHour.toString() + ":" + arrivalMinute.toString();
        else
            s=arrivalHour.toString() + ":0" + arrivalMinute.toString();
        return s;
    }

    private boolean validDays() {
        for(JCheckBox daysCheckbox: daysCheckboxes)
        {
            if(daysCheckbox.isSelected())
                return true;
        }
        return false;
    }
}
