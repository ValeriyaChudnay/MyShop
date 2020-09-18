package db;

import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class TransactionManager {
    private DataSource dataSource;
    private static final Logger logger = Logger.getLogger(TransactionManager.class);


    public TransactionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> T doSQL(RepositoryFunction<T> repositoryFunction) {
        T res = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            ConnectionHolder.setConnection(connection);
            logger.debug("Connection" + connection);
            connection.setAutoCommit(false);
            res = repositoryFunction.apply();
            connection.commit();
        } catch (SQLException e) {
           logger.error(e.getMessage());
            rollback(connection);
        } finally {
            closeConnection(connection);
        }
        return res;
    }

    private void closeConnection(Connection connection) {
        try {
            if (Objects.nonNull(connection)) {
                connection.close();
                ConnectionHolder.remove();
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    private void rollback(Connection connection) {
        try {
            if (Objects.nonNull(connection)) {
                connection.rollback();
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }
}
