package service;

import model.Person;
import model.PersonDTO;
import transform.PersonTransformer;

import javax.ejb.Stateless;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


@Stateless
public class PersonService {

    @PersistenceContext(unitName = "persistenceUnit")
    private EntityManager em;

    public Person get(int id) {
        Person findPerson = em.find(Person.class, id);
        return findPerson;
    }

    public Person add(Person person, Integer parentId) {

        if (parentId != null) {
            Person parentPerson = em.find(Person.class, parentId);
            Person p = new Person(person.getName(), person.getSurname(), parentPerson, null);
            parentPerson.getChildren().add(p);
            em.persist(p);
            em.flush();
            return p;
        } 
            Person p = new Person(person.getName(),
                    person.getSurname(), null, null);
            em.persist(p);
            em.flush();
            return p;
    }

    public void delete(int id) {
        Person deletePerson = em.find(Person.class, id);
        if (deletePerson.getParentPerson() == null)
            em.remove(deletePerson);
        else {
            Person parentPerson = deletePerson.getParentPerson();
            IntStream.range(0, parentPerson.getChildren().size())
                    .filter(i -> parentPerson.getChildren().get(i).getId() == id)
                    .forEach(i -> {
                        parentPerson.getChildren().remove(i);
                        em.remove(deletePerson);
                    });
        }
    }

    public List<PersonDTO> getTreeDTO() {

        String s = "select p from Person p where p.parentPerson is null";
        List<Person> personList = em.createQuery(s, Person.class).getResultList();

        List<PersonDTO> treePersonDTO = new ArrayList<>();
        for (int i = 0; i < personList.size(); i++)
            treePersonDTO.add(PersonTransformer.convertListToPersonDTO(personList.get(i)));
        return treePersonDTO;
    }

    public List<Person> search(String input) {
        String s = "select p from Person p " +
                "where p.name LIKE '%" + input + "%'" + "or p.surname LIKE '%" + input + "%'";
        return em.createQuery(s, Person.class).getResultList();
    }

    public void edit(Person personNew, Integer id) {
        Person p = em.find(Person.class, id);
        p.setName(personNew.getName());
        p.setSurname(personNew.getSurname());
        em.merge(p);
    }
}
