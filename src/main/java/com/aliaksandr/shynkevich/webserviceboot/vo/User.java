package com.aliaksandr.shynkevich.webserviceboot.vo;

/**
 * TODO: Please add some information about
 * <p>
 * Date: 2018-03-21
 *
 * @author Aliaksandr Shynkevich
 */
public class User {
    private String id;
    private String name;
    private int age;

    public User() {
    }

    public User(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Object -> User{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }
}
