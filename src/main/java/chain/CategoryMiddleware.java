package chain;

import entity.UserRequestParameters;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CategoryMiddleware extends Middleware {
    private List<Integer> categories;


    @Override
    public String check(UserRequestParameters urp, String sql) {
        if (Objects.nonNull(urp.getCategory())) {
            String[] idCategory = urp.getCategory().toArray(new String[0]);
            categories = Arrays.stream(idCategory)
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
            sql += " idCategory in ( ";
            StringBuilder sqlBuilder = new StringBuilder(sql);
            for (String c : idCategory) {
                sqlBuilder.append("?,");
            }
            sql = sqlBuilder.toString();
            sql = sql.substring(0, sql.length() - 1);
            sql += ") and";
        }
        return checkNext(urp, sql);
    }

    @Override
    public ResultSet setParameterToStatement(int count, PreparedStatement statement) throws SQLException {
        if (Objects.nonNull(categories)) {
            for (int i : categories) {
                statement.setInt(count++, i);
            }
        }
        return setNextParameterToStatement(count, statement);
    }
}
