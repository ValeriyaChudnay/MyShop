package entity;
import dto.UserDTO;
import entity.product.Shoes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static entity.OrderStatus.accepted;

public class Order {
    private long orderId;
    private OrderStatus orderStatus;
    private String statusDetail;
    private String orderMethod;
    private long date;
    private String userMail;
    private List<ShoesInfo> productList;
    private String address;

    public String getAddress() {
        return address;
    }

    public String getOrderMethod() {
        return orderMethod;
    }

    public long getOrderId() {
        return orderId;
    }

    public int getOrderStatus() {
        return orderStatus.ordinal();
    }

    public String getStatusDetail() {
        return statusDetail;
    }

    public long getDate() {
        return date;
    }

    public String getUserMail() {
        return userMail;
    }

    public List<ShoesInfo> getProductList() {
        return productList;
    }

    public Order(HttpServletRequest request) {
        UserDTO userDTO= (UserDTO) request.getSession().getAttribute("currentUser");
        this.userMail =  userDTO.getEmail();
        orderStatus= accepted;
        statusDetail="order accepted";
        date=System.currentTimeMillis();
        productList=makeList((Cart) request.getSession().getAttribute("cart"));
        address=request.getParameter("address");
        orderMethod=request.getParameter("orderMethod");
    }

    private List<ShoesInfo> makeList(Cart cart) {
        List<ShoesInfo> shoes=new ArrayList<>();
        for(Map.Entry<Shoes,Integer> e:cart.getCart().entrySet()){
            ShoesInfo shoesInfo=new ShoesInfo(e.getKey(),e.getValue());
            shoes.add(shoesInfo);
        }
        return shoes;
    }
}
