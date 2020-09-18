package repository;

import entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

    <S extends User> boolean save(S entity) throws SQLException;

    List<User> getAll() throws SQLException;

    Optional<User> getUserByEmail(String email) throws SQLException;


}
