package db;


import java.sql.SQLException;

public interface RepositoryFunction<T> {
    T apply() throws SQLException;
}
