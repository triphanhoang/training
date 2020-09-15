package com.faber.airmgr.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class FlightFilterModel implements Serializable {
    private Long fromAirPort;
    private Long toAirPort;
    private Integer page = 0;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date arrivalDate;
    private Integer adultsCount;
    private Integer childrenCount;
    private Integer seniorsCount;
}
