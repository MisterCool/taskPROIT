package service;

import model.Person;
import model.PersonDTO;
import transform.PersonTransformer;

import javax.ejb.Stateless;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Stateless
public class PersonService {

    @PersistenceContext(unitName = "persistenceUnit")
    private EntityManager em;

    public Person get(int id) {
        Person findPerson = em.find(Person.class, id);
        return findPerson;
    }

    public Person add(Person person, Integer parentId) {

        if (parentId == null) {
            Person p = new Person(person.getName(),
                    person.getSurname(), null, null);
            em.persist(p);
            em.flush();
            return p;
        }
        Person parentPerson = em.find(Person.class, parentId);
        Person p = new Person(person.getName(), person.getSurname(), parentPerson, null);
        parentPerson.getChildren().add(p);
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
            for (int i = 0; i < parentPerson.getChildren().size(); i++)
                if (parentPerson.getChildren().get(i).getId() == id) {
                    parentPerson.getChildren().remove(i);
                    em.remove(deletePerson);
                }
        }
    }

    public List<PersonDTO> getTreeDTO() {

        String s = "select p from Person p where p.parentPerson is null";
        List<Person> personList = em.createQuery(s, Person.class).getResultList();

        List<PersonDTO> treePersonDTO = new ArrayList<>();
        for (int i = 0; i < personList.size(); i++)
            treePersonDTO.add(PersonTransformer.convertTreeToPersonDTO(personList.get(i)));
        return treePersonDTO;
    }

    public List<PersonDTO> searchInTree(Person personToFind) {
        String s = "select p from Person p where p.parentPerson is null";
        List<Person> personList = em.createQuery(s, Person.class).getResultList();
        List<Person> result = new ArrayList<>();
        for (Person person : personList) {
            if (personToFind.getName().equals(person.getName())
                    || personToFind.getSurname().equals(person.getSurname()))
                result.add(person);
            result.addAll(search(personToFind, person));
        }

        return PersonTransformer.convertListToPersonDTO(result);
    }

    public List<Person> search(Person personFind, Person person) {
        List<Person> result = new ArrayList<>();

        for (int i = 0; i < person.getChildren().size(); i++) {
            if (personFind.getName().equals(person.getChildren().get(i).getName())
                    || personFind.getSurname().equals(person.getChildren().get(i).getSurname()))
                result.add(person.getChildren().get(i));
            search(personFind, person.getChildren().get(i));
        }
        return result;
    }

    public void edit(Person personNew, Integer id) {
        Person p = em.find(Person.class, id);
        p.setName(personNew.getName());
        p.setSurname(personNew.getSurname());
        em.merge(p);
    }
}
