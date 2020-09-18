package chain;

import entity.UserRequestParameters;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BrandMiddleware extends Middleware {
    private List<Integer> brands;

    @Override
    public String check(UserRequestParameters urp, String sql) {
        if (Objects.nonNull(urp.getBrand())) {
            String[] idBrand = urp.getBrand().toArray(new String[0]);
            brands = Arrays.stream(idBrand)
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
            sql += " idBrand in ( ";
            StringBuilder sqlBuilder = new StringBuilder(sql);
            for (String c : idBrand) {
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
        if (Objects.nonNull(brands)) {
            for (int i : brands) {
                statement.setInt(count++, i);
            }
        }
        return setNextParameterToStatement(count, statement);
    }
}
