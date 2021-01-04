package service;

import org.apache.ibatis.session.SqlSession;
import utils.MybatisUtil;
import pojo.Person;
import dao.PersonDAO;
import java.util.List;

public class PersonServiceImpl implements PersonService{
    @Override
    public List<Person> getPersonList() {
        List<Person> personList = null;
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        PersonDAO personDAO = sqlSession.getMapper(PersonDAO.class);

        personList = personDAO.getPersonList();

        sqlSession.close();
        return personList;
    }
    @Override
    public boolean addPerson(Person person) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        PersonDAO personDAO = sqlSession.getMapper(PersonDAO.class);

        int i = personDAO.addPerson(person);
        if(i > 0) {
            System.out.println("succeed");
            sqlSession.commit();//事务的提交
        }

        sqlSession.close();
        return i > 0 ? true : false;
    }
    @Override
    public boolean iscorrectTelenum(String username, String telenum){
        boolean isCorrect = false;
        List<Person> personList = getPersonList();
        for(Person person:personList){
            if(username.equals(person.getUsername())){
                if(telenum.equals(person.getTelenum())){
                    isCorrect = true;
                }
                break;
            }
        }
        return isCorrect;
    }
}
