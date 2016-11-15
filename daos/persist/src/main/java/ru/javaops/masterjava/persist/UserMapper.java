package ru.javaops.masterjava.persist;

import org.apache.ibatis.annotations.*;
import ru.javaops.masterjava.model.User;

import java.util.List;

/**
 * Created by Restrictor on 14.11.2016.
 */
public interface UserMapper {

    @Insert("INSERT INTO users(full_name, email, flag) VALUES(#{fullName}, #{email}, #{flag})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insertUser(User user);

    @Select("SELECT id AS id, full_name as fullName, email as email, flag as flag FROM users WHERE id=#{id}")
    User getUserById(Integer id);

    @Update("UPDATE users SET full_name=#{fullName}, email=#{email}, flag=#{flag} WHERE id=#{id}")
    void updateUser(User user);

    @Delete("DELETE FROM users WHERE id=#{id}")
    void deleteUser(Integer id);

    @Select("SELECT * FROM users LIMIT 20")
    @Results({
            @Result(id=true, property="id", column="id"),
            @Result(property="fullName", column="full_name"),
            @Result(property="email", column="email"),
            @Result(property="flag", column="flag")
    })
    List<User> getAllUsers();
}
