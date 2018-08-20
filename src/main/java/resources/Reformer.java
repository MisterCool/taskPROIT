package resources;

import model.Person;
import model.PersonDTO;

public class Reformer {

    public static Person convertToPerson(PersonDTO personDTO){
        Person person = new Person(personDTO.getName(), personDTO.getSurname(), personDTO.getParentPerson());
        return person;
    }
    public static PersonDTO convertToPersonDTO(Person person){
        PersonDTO personDTO = new PersonDTO(person.getParentPerson().getId(), person.getName(), person.getSurname());
        return personDTO;
    }
}
