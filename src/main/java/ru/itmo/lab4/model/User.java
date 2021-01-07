package ru.itmo.lab4.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "users")
@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User{

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @SequenceGenerator(name="user_generator",
            sequenceName = "user_seq")
    private Long id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String hashPass;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Point> points;

    public void addPoint(Point point){
        if (points == null){
            points = new ArrayList<>();
        }
        points.add(point);
    }

    @Override
    public String toString() {
        return "{" + id + ":" + login + "}";
    }
}
