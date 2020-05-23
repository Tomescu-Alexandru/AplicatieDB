package model;

import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    int id;
    private String source, destination;
    private String departure, arrival;
    private String day;
    private int price;


}
