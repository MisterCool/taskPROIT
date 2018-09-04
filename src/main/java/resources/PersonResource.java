package resources;

import com.google.gson.Gson;
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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("users")
public class PersonResource {

    @EJB
    private PersonService personService;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam(value = "id") int id){
        Person p = personService.get(id);
       // PersonDTO personDTO = Reformer.convertToPersonDTO(p);
        return Response.ok(p).build();
    }

    @GET
    @Path("tree")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTree(){

        List<Person> tree = personService.getTree();
        List<PersonDTO> treePersonDTO = new ArrayList<>();
        for(int i = 0; i < tree.size(); i++)
            treePersonDTO.add(Reformer.convertToPersonDTO(tree.get(i)));
        return Response.ok(treePersonDTO).header("Access-Control-Allow-Origin", "*").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPerson(PersonDTO personDTO) {
        Person person = Reformer.convertToPerson(personDTO);
        Integer id = personService.add(person, personDTO.getParentId());
        return Response.ok(id).build();
    }

    @DELETE
    @Path("{id}")
    @Produces("text/plain")
    public String deletePerson(@PathParam(value = "id") int id){
        personService.delete(id);
        return String.valueOf(id);
    }

}
