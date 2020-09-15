package com.faber.airmgr.data.entities;

import com.faber.airmgr.infrastructure.domain.DomainEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirPortEntity extends DomainEntity<Long> {

    private Long id;

    private String name;

    private String city;
}
