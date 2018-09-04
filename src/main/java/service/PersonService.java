package service;

import model.Person;

import javax.ejb.Stateless;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.stream.Collectors;


@Stateless
public class PersonService {

    @PersistenceContext(unitName = "persistenceUnit")
    private EntityManager em;

    public Person get(int id) {
        Person findPerson = em.find(Person.class, id);
        return findPerson;
    }

    public int add(Person person, Integer parentId) {

        if (parentId == null) {
            Person p = new Person(person.getName(),
                    person.getSurname(), null, null);
            em.persist(p);
            em.flush();
            return p.getId();
        } else {
            Person parentPerson = em.find(Person.class, parentId);
            Person p = new Person(person.getName(), person.getSurname(), parentPerson, null);
            parentPerson.getChildren().add(p);
            em.persist(p);
            em.merge(parentPerson);
            em.flush();
            return p.getId();
        }
    }

    public void delete(int id) {
        Person deletePerson = em.find(Person.class, id);
        em.remove(deletePerson);
    }

    public List<Person> getTree() {

        String s = "select p from Person p where p.parentPerson is null";
        List<Person> personList = em.createQuery(s).getResultList();

        return personList;
    }
}
