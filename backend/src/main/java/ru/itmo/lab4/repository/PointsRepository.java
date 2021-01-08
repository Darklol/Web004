package ru.itmo.lab4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.lab4.model.Point;

public interface PointsRepository extends JpaRepository<Point, Long> {
}
