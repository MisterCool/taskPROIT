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

    public static PersonDTO convertTreeToPersonDTO(Person person){
        List<PersonDTO> listChild= new ArrayList<>();

        for(int i = 0; i < person.getChildren().size(); i++){
            listChild.add(convertTreeToPersonDTO(person.getChildren().get(i)));
        }


        return new PersonDTO(person.getId(), person.getName(), person.getSurname(), listChild, null);
    }

    public static PersonDTO convertToPersonDTO(Person person){
        return new PersonDTO(person.getId(), person.getName(),
                person.getSurname(), null, null);
    }

    public static List<PersonDTO> convertListToPersonDTO(List<Person> personList){
        List<PersonDTO> result = new ArrayList<>();

        for(int i = 0; i < personList.size(); i++)
            result.add(convertToPersonDTO(personList.get(i)));

        return result;
    }
}
