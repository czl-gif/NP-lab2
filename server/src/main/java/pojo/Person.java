package pojo;

public class Person {
    private String username;
    private String name;
    private  Integer age;
    private String telenum;
    public Person() {}
    public Person(String username, String name, Integer age, String telenum) {
        this.username = username;
        this.name = name;
        this.age = age;
        this.telenum = telenum;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelenum() {
        return telenum;
    }

    public void setTelenum(String telenum) {
        this.telenum = telenum;
    }
    @Override
    public String toString() {
        return "person{" +
                "username='" + username + '\'' +
                ",name='" + name +'\'' +
                ",age='" + age + '\'' +
                ",telenum" + telenum + '\'' +
                '}';
    }
}
