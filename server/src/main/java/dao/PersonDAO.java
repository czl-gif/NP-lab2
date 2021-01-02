package dao;
import pojo.Person;
import java.util.List;

public interface PersonDAO {
    public int addPerson(Person person);
    public int removePerson(String username);
    public int updatePerson(Person person);
    public List<Person> getPersonList();
}
