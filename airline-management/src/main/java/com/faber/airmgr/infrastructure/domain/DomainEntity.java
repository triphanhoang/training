package com.faber.airmgr.infrastructure.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class DomainEntity<T> implements Serializable {
    public abstract T getId();

    public abstract void setId(T id);
}
