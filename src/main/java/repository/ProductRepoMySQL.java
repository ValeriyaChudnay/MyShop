package repository;

import chain.Middleware;
import db.ConnectionHolder;
import db.SQLConst;
import entity.Cart;
import entity.product.Shoes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductRepoMySQL implements ProductRepository {

    @Override
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
    public List<Shoes> query(String s, Middleware middleware) throws SQLException {
        PreparedStatement pstmt = ConnectionHolder.getConnection().get().prepareStatement("select * from product" + s);
        ResultSet resultSet = middleware.setParameterToStatement(1, pstmt);
        List<Shoes> shoesList = new ArrayList<>();
        while (resultSet.next()) {
            Shoes shoes = new Shoes();
            shoes.setId(resultSet.getInt("id"));
            shoes.setImage2(resultSet.getString("image2"));
            shoes.setImage1(resultSet.getString("image1"));
            shoes.setBrand(resultSet.getString("idBrand"));
            shoes.setCategory(resultSet.getInt("idCategory"));
            shoes.setDescription(resultSet.getString("description"));
            shoes.setPrice(resultSet.getDouble("price"));
            shoes.setName(resultSet.getString("name"));
            shoesList.add(shoes);
        }
        return shoesList;
    }

    @Override
    public int count(String s, Middleware middleware) throws SQLException {
        PreparedStatement pstmnt = ConnectionHolder.getConnection().get().prepareStatement("Select Count(*)  as count from product " + s);
        ResultSet rs = middleware.setParameterToStatement(1, pstmnt);
        rs.next();
        return rs.getInt("count");
    }

    public Shoes getById(int id) throws SQLException {
        PreparedStatement pstmnt = ConnectionHolder.getConnection().get().prepareStatement("Select * from product where id=?");
        pstmnt.setInt(1, id);
        ResultSet rs = pstmnt.executeQuery();
        Shoes shoes = new Shoes();
        while (rs.next()) {
            shoes.setId(rs.getInt("id"));
            shoes.setImage2(rs.getString("image2"));
            shoes.setImage1(rs.getString("image1"));
            shoes.setBrand(rs.getString("idBrand"));
            shoes.setCategory(rs.getInt("idCategory"));
            shoes.setDescription(rs.getString("description"));
            shoes.setPrice(rs.getDouble("price"));
            shoes.setName(rs.getString("name"));
        }
        return shoes;
    }

    public Cart getCart(String mail) throws SQLException {
        PreparedStatement pstmnt = ConnectionHolder.getConnection().get().prepareStatement("select * from product left join shop.temporarycart t on product.id = t.ProductId where UserMail=?");
        pstmnt.setString(1, mail);
        ResultSet rs = pstmnt.executeQuery();
        Cart cart = new Cart();
        while (rs.next()) {
            Shoes shoes = new Shoes();
            shoes.setId(rs.getInt("id"));
            shoes.setImage2(rs.getString("image2"));
            shoes.setImage1(rs.getString("image1"));
            shoes.setBrand(rs.getString("idBrand"));
            shoes.setCategory(rs.getInt("idCategory"));
            shoes.setDescription(rs.getString("description"));
            shoes.setPrice(rs.getDouble("price"));
            shoes.setName(rs.getString("name"));
            cart.add(shoes,rs.getInt("Amount"));
        }
        return cart;
    }

    public boolean saveInTemporaryCart(String mail, Cart cart) throws SQLException {
        for(Map.Entry<Shoes,Integer> e :cart.getCart().entrySet()){
        PreparedStatement pstmt = ConnectionHolder.getConnection().get().prepareStatement("replace into temporarycart values (?, ?, ?)");
        int placeHolder = 1;
        pstmt.setString(placeHolder++, mail);
        pstmt.setInt(placeHolder++, e.getKey().getId());
        pstmt.setInt(placeHolder++,e.getValue());
        pstmt.executeUpdate();
        }
        return true;
    }
    public boolean cleanUserTemporaryCart(String mail) throws SQLException {
            PreparedStatement pstmt = ConnectionHolder.getConnection().get().prepareStatement("delete from temporarycart where UserMail=?");
            pstmt.setString(1, mail);
            pstmt.executeUpdate();
        return true;
    }
}
