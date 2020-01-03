package model.service;

import model.entity.Person;
import model.repository.PersonDA;

import java.util.List;

public class PersonService {
    public static void main1(String[] args)throws Exception {
        Person person = new Person();
        person.setId(1377);
        person.setName("Soheil");
        person.setFamily("Rezaei");
        save(person);
    }
    public static void save(Person person) throws Exception {
        //person.setId(person.getId()-(person.getId()*20)/100);
        PersonDA personDA = new PersonDA();
        personDA.insert(person);
        personDA.close();
    }



    public static void main2(String[] args)throws Exception {
        Person person = new Person();
        person.setId(1102);
        person.setName("Sina");
        person.setFamily("Rezaei");
        update(person);
    }
    public static void update(Person person) throws Exception {
        PersonDA personDA = new PersonDA();
        personDA.update(person);
        personDA.close();
    }

    public static void main(String[] args)throws Exception {
      List<Person> personList = findAll();
        for (Person person : personList) {
            System.out.println(person.getId());
            System.out.println(person.getName());
            System.out.println(person.getFamily());
        }

    }
    public static List<Person> findAll() throws Exception {
        PersonDA personDA = new PersonDA();
        List<Person> personList = personDA.select();
        personDA.close();
        return personList;


    }




    public static void main3(String[] args)throws Exception {
        Person person = new Person();
        person.setId(1102);
        delete(person);
    }
    public static void delete(Person person)throws Exception
    {
        PersonDA personDA = new PersonDA();
        personDA.delete(person);
        personDA.close();
    }


}
