package db;

import java.sql.Connection;

public class ConnectionHolder {
    public static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    private ConnectionHolder() {
        //to hide implicit
    }

    public static ThreadLocal<Connection> getConnection() {
        return threadLocal;
    }

    public static void setConnection(Connection connection) {
        threadLocal.set(connection);
    }

    public static void remove() {
        threadLocal.remove();
    }
}
