package model;


public class PersonDTO {

    private int id;
    private int parentId;
    private String name;
    private String surname;
    private Person parentPerson;

    public Person getParentPerson() {
        return parentPerson;
    }

    public void setParentPerson(Person parentPerson) {
        this.parentPerson = parentPerson;
    }

    public PersonDTO(){}

    public PersonDTO(int parentId, String name, String surname) {
        this.parentId = parentId;
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
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
}
