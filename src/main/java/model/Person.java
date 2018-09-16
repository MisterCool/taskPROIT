package model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.deploy.security.ValidationState;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String surname;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Person parentPerson;

    @OneToMany(cascade=CascadeType.REMOVE)
    @JoinColumn(name = "parent_id")
    private List<Person> children;

    public Person(String name, String surname, Person parentPerson, List<Person> children) {
        this.name = name;
        this.surname = surname;
        this.parentPerson = parentPerson;
        this.children = children;
    }

    public List<Person> getChildren() {
        return children;
    }

    public void setChildren(List<Person> children) {
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Person getParentPerson() {
        return parentPerson;
    }

  public void setParentPerson(Person parentPerson) {
        this.parentPerson = parentPerson;
    }

    public Person() { }

}
