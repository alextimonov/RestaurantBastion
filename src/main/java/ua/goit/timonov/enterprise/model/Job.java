package ua.goit.timonov.enterprise.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Provides job positions in database
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Job)) return false;

        Job job = (Job) o;

        return position == job.position;

    }

    @Override
    public int hashCode() {
        return position != null ? position.hashCode() : 0;
    }
}
