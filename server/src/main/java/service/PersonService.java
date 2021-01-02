package service;

import pojo.Person;
import java.util.List;

public interface PersonService {
    public boolean addPerson(Person person);
    public List<Person> getPersonList();
}
