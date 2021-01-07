package ru.itmo.lab4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


@Table(name = "points")
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Point {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @SequenceGenerator(name="point_generator",
            sequenceName = "point_seq", allocationSize=50)
    private Long id;

    @Column(nullable = false)
    private Double x;

    @Column(nullable = false)
    private Double y;

    @Column(nullable = false)
    private Double r;

    @Column(nullable = false)
    private boolean inArea;

    @Column(nullable = false)
    private String queryTime;

    @JsonIgnore
    @JoinColumn()
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @Override
    public String toString() {
        return "{p" + id + " of user " + user.getLogin() + "}";
    }
}
