package db;

public class SQLConst {
    public static final String INSERT_INTO_USER_VALUES = "Insert into user values (?,?,?,?,?,?)";
    public static final String INSERT_INTO_PRODUCT_VALUES = "Insert into product values (default,?,?,?,?,?,?,?)";
    public static final String SELECT_FROM_USER_WHERE_EMAIL = "Select * from User where email=?";

    private SQLConst() {
        //to hide the implicit
    }
}
