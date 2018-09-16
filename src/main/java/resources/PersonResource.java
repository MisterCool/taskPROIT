package resources;

import model.Person;
import model.PersonDTO;
import service.PersonService;
import transform.PersonTransformer;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
        PersonDTO personDTO = PersonTransformer.convertTreeToPersonDTO(p);
        return Response.ok(personDTO).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("tree")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTree(){
        List<PersonDTO> tree = personService.getTreeDTO();
        return Response.ok(tree).header("Access-Control-Allow-Origin", "*").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPerson(PersonDTO personDTO) {
        Person person = PersonTransformer.convertToPerson(personDTO);
        PersonDTO postPerson = PersonTransformer.convertToPersonDTO(personService.add(person, personDTO.getParentId()));
        return Response.ok((postPerson)).build();
    }

    @POST
    @Path("search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchPerson(PersonDTO personDTO) {
        Person person = PersonTransformer.convertToPerson(personDTO);
        List<PersonDTO> result = personService.searchInTree(person);
        return Response.ok(result).build();
    }

    @POST
    @Path("edit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(PersonDTO personDTO) {
        Person person = PersonTransformer.convertToPerson(personDTO);
        personService.edit(person, personDTO.getId());
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    @Produces("text/plain")
    public String deletePerson(@PathParam(value = "id") int id){
        personService.delete(id);
        return String.valueOf(id);
    }


}
