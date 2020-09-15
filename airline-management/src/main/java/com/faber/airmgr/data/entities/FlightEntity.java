package com.faber.airmgr.data.entities;

import com.faber.airmgr.infrastructure.domain.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightEntity extends DomainEntity<Long> {
    private Long id;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private AirPortEntity departurePort;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private AirPortEntity arrivalPort;

    private Date departureTime;

    private Date arrivalTime;

    private Long price;
}
