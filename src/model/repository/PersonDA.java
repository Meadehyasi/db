package model.repository;

import model.entity.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PersonDA {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public PersonDA() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "amirsam", "myjava123");
    }

    public void insert(Person person) throws Exception {
        preparedStatement = connection.prepareStatement("insert into person (id,name,family) values (?,?,?)");
        preparedStatement.setLong(1, person.getId());
        preparedStatement.setString(2, person.getName());
        preparedStatement.setString(3, person.getFamily());
        preparedStatement.executeUpdate();
    }

    public void update(Person person) throws Exception {
        preparedStatement = connection.prepareStatement("update person set name=?, family=? where id=?");
        preparedStatement.setString(1, person.getName());
        preparedStatement.setString(2, person.getFamily());
        preparedStatement.setLong(3, person.getId());
        preparedStatement.executeUpdate();
    }

    public void delete(Person person) throws Exception {
        preparedStatement = connection.prepareStatement("delete from person where id=?");
        preparedStatement.setLong(1, person.getId());
        preparedStatement.executeUpdate();
    }

    public List<Person> select() throws Exception {
        preparedStatement = connection.prepareStatement("select * from person");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Person> personList = new ArrayList<>();
        while (resultSet.next()) {
            Person person = new Person();
            person.setId(resultSet.getLong("id"));
            person.setName(resultSet.getString("name"));
            person.setFamily(resultSet.getString("family"));
            personList.add(person);
        }
        return personList;
    }

    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
