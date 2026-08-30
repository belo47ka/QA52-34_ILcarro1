package dto;


import lombok.*;
import utils.enums.Fuel;

import java.lang.reflect.Field;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CarLombok {
    private String location;
    private String manufacture;
    private String model;
    private String year;
    private Fuel fuel;
    private Integer seats;
    private String carClass;
    private String carRegistrationNumber;
    private Double price;
    private String about;

}
