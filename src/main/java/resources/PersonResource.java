package resources;

import model.Person;
import model.PersonDTO;
import service.PersonService;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("users")
public class PersonResource {

    @EJB
    private PersonService personService;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonDTO getPerson(@PathParam(value = "id") int id){
        PersonDTO personDTO = Reformer.convertToPersonDTO(personService.get(id));
        return personDTO;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("text/plain")
    public int addPerson(PersonDTO personDTO) {
        Person person = Reformer.convertToPerson(personDTO);
        personService.add(person);
        return person.getId();
    }

    @DELETE
    @Path("{id}")
    @Produces("text/plain")
    public String deletePerson(@PathParam(value = "id") int id){
        personService.delete(id);
        return String.valueOf(id);
    }
}
