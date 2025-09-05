package com.gtarp.tabaricobackend.entities;

public interface UpdatableEntity<T, Dto> {

    T update(Dto dto);

}