package ua.goit.timonov.enterprise.module_6_2.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Alex on 13.08.2016.
 */
@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    private Position position;

    public Job() {
    }

    public Job(Position position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return position.name();
    }
}
