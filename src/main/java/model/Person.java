package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "person")
public class Person {
    @Id
    private int id;

    private String name;
    private String surname;

    @OneToOne
    @JoinColumn(name = "parent_id")
    private Person parentPerson;

    @java.beans.ConstructorProperties({"name", "surname", "parentPerson"})
    public Person(String name, String surname, Person parentPerson) {
        this.name = name;
        this.surname = surname;
        this.parentPerson = parentPerson;
    }

    public Person() { }
}
