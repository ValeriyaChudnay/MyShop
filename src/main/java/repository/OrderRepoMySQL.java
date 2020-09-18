package repository;

import db.ConnectionHolder;
import db.SQLConst;
import entity.Order;
import entity.ShoesInfo;
import entity.product.Shoes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OrderRepoMySQL implements OrderRepository {

    public <S extends Shoes> boolean save(S product) throws SQLException {
        PreparedStatement pstmt = ConnectionHolder.getConnection().get().prepareStatement(SQLConst.INSERT_INTO_PRODUCT_VALUES);
        int placeHolder = 1;
        pstmt.setString(placeHolder++, product.getName());
        pstmt.setString(placeHolder++, product.getDescription());
        pstmt.setString(placeHolder++, product.getImage1());
        pstmt.setString(placeHolder++, product.getImage2());
        pstmt.setInt(placeHolder++, product.getCategory());
        pstmt.setDouble(placeHolder++, product.getPrice());
        pstmt.setInt(placeHolder, Integer.parseInt(product.getBrand()));
        pstmt.executeUpdate();
        pstmt.close();
        return true;
    }

    @Override
    public int add(Order order) throws SQLException {
        PreparedStatement pstmt = ConnectionHolder.getConnection().get().prepareStatement(
                "insert into orders(id,orderDate, userEmail, orderMethod, orderComment, orderStatus,address) values (default,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        int placeHolder = 1;
        pstmt.setDate(placeHolder++, new java.sql.Date(order.getDate()));
        pstmt.setString(placeHolder++, order.getUserMail());
        pstmt.setString(placeHolder++, order.getOrderMethod());
        pstmt.setString(placeHolder++, order.getStatusDetail());
        pstmt.setInt(placeHolder++, order.getOrderStatus());
        pstmt.setString(placeHolder++, order.getAddress());
        pstmt.executeUpdate();
        ResultSet res = pstmt.getGeneratedKeys();
        res.next();
        int orderId=res.getInt(1);
        addProduct(order,orderId);
        pstmt.close();
        return orderId;
    }

    public boolean addProduct(Order order,int orderId) throws SQLException {
        for(ShoesInfo shoes:order.getProductList()){
            PreparedStatement pstmt = ConnectionHolder.getConnection().get().prepareStatement(
                    "insert into ordersproduct (orderId, productId, productPrice, productCount) VALUES (?,?,?,?)");
            int placeHolder = 1;
            pstmt.setInt(placeHolder++,orderId);
            pstmt.setInt(placeHolder++,shoes.getShoesId());
            pstmt.setDouble(placeHolder++,shoes.getPrice());
            pstmt.setInt(placeHolder,shoes.getAmount());
            pstmt.executeUpdate();
        }
        return true;
    }
}
