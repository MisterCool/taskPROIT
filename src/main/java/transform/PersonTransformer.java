package transform;

import model.Person;
import model.PersonDTO;

import java.util.ArrayList;
import java.util.List;

public class PersonTransformer {

    public static Person convertToPerson(PersonDTO personDTO){
        Person person = new Person(personDTO.getName(), personDTO.getSurname(), personDTO.getParentPerson(), null);
        return person;
    }

    public static PersonDTO convertListToPersonDTO(Person person){
        List<PersonDTO> listChild= new ArrayList<>();

        for(int i = 0; i < person.getChildren().size(); i++){
            listChild.add(convertListToPersonDTO(person.getChildren().get(i)));
        }

        return new PersonDTO(person.getId(), person.getName(), person.getSurname(), listChild, null);
    }

    public static PersonDTO convertToPersonDTO(Person person){
        return new PersonDTO(person.getId(), person.getName(),
                person.getSurname(), null, null);
    }

    public static List<PersonDTO> getParents(Person person, List<PersonDTO> list){
        if(person.getParentPerson() == null) {
            return null;
        }
        list.add(convertToPersonDTO(person.getParentPerson()));
        getParents(person.getParentPerson(), list);
        return list;
    }
}
