package resources;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Person;
import model.PersonDTO;
import org.omg.CORBA.Object;
import sun.misc.IOUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Reformer {

    public static Person convertToPerson(PersonDTO personDTO){
        Person person = new Person(personDTO.getName(), personDTO.getSurname(), null, null);
        return person;
    }

    public static PersonDTO convertToPersonDTO(Person person){
        List<PersonDTO> listChild= new ArrayList<>();

        for(int i = 0; i < person.getChildren().size(); i++){
            listChild.add(convertToPersonDTO(person.getChildren().get(i)));
        }

        return new PersonDTO(person.getId(), person.getName(), person.getSurname(), listChild, null);
    }


}
