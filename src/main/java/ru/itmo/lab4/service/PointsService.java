package ru.itmo.lab4.service;


import ru.itmo.lab4.model.Point;

import java.util.List;

public interface PointsService {

    void save(Point point);
    List<Point> getAll();
    Point get(Long id);
    //void update(Point point);
    void delete(Long id);
    List<Point> getAllPointsForUser(Long id);
}
