package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.models.Direction;

import java.util.List;

public interface DirectionService
{
    List<Direction> findAll();

    Direction findDirectionById(long id);

    Direction save(Direction direction);

    void delete(long id);
}
