package com.faber.airmgr.models;

import lombok.Data;

import java.io.Serializable;


@Data
public class FlightOrderModel implements Serializable {
    private Long flightId;
    private Integer numOfAdults = 1;
    private Integer numOfChildren = 0;
    private Integer numOfSeniors = 0;
    private String returnType;
}
