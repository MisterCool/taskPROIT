package service;

import model.Person;

import javax.ejb.Stateless;

import javax.persistence.*;


@Stateless
public class PersonService {

    @PersistenceContext(unitName = "persistenceUnit")
    private EntityManager em;

    public Person get(int id){
        Person findPerson = em.find(Person.class, id);
        return findPerson;
    }

    public int add(Person person) {

        if (person.getParentPerson() == null) {
            Person p = new Person(person.getName(),
                    person.getSurname(), null);
            em.persist(p);
            return p.getId();
        } else {

            Person parentPerson = em.find(Person.class, person.getParentPerson().getId());
            Person p = new Person(person.getName(), person.getSurname(), parentPerson);
            em.persist(p);
            return p.getId();
        }
    }

    public void delete(int id){
        Person deletePerson = em.find(Person.class, id);
            em.remove(deletePerson);
    }
