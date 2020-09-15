package com.faber.airmgr.data.entities;

import com.faber.airmgr.data.enums.TypeFlight;
import com.faber.airmgr.infrastructure.domain.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity extends DomainEntity<Long> {
    private Long id;

    private Date orderTime;

    private TypeFlight returnType;

    private FlightEntity flight;

    private Integer adultsCount;

    private Integer seniorsCount;

    private Integer childrenCount;

    private Long flightPrice;

    private Long totalPrice;

    public Integer totalTickets() {
        return adultsCount + seniorsCount + childrenCount;
    }
}
