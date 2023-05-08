package com.example.paygoal.factories;

public interface FactoryConvert<E, DTO> {

    E createEntity(DTO dto);

    DTO createDto(E e);
}
