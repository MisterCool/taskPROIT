package model;


import java.util.List;

public class PersonDTO {

    private int id;
    private Integer parentId;
    private String name;
    private String surname;
    private Person parentPerson;
    private List<PersonDTO> children;

    public Person getParentPerson() {
        return parentPerson;
    }

    public void setParentPerson(Person parentPerson) {
        this.parentPerson = parentPerson;
    }

    public PersonDTO(){}

    public PersonDTO(int id, String name, String surname, List<PersonDTO> children, Person parentPerson) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.children = children;
        this.parentPerson = parentPerson;
    }

    public PersonDTO(Integer parentId, String name, String surname) {
        this.parentId = parentId;
        this.name = name;
        this.surname = surname;
    }
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PersonDTO> getChildren() {
        return children;
    }

    public void setChildren(List<PersonDTO> children) {
        this.children = children;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
