package service;

import pojo.Person;
import java.util.List;

public interface PersonService {
    public boolean addPerson(Person person);
    public List<Person> getPersonList();
    public boolean iscorrectTelenum(String username, String telenum);
}
