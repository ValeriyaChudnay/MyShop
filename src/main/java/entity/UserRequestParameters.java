package entity;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class UserRequestParameters implements Serializable {
    private String pageNumber;
    private List<String> brand;
    private String priceFrom;
    private String priceTo;
    private String sortType;
    private List<String> category;
    private int count;
    private String name;
    private String amountOnPage;

    public UserRequestParameters(HttpServletRequest request) {
        this.pageNumber = request.getParameter("pageNumber");
        if (Objects.nonNull(request.getParameterValues("idCategory"))) {
            this.category = Arrays.asList(request.getParameterValues("idCategory"));
        }
        if (Objects.nonNull(request.getParameterValues("idBrand"))) {
            this.brand = Arrays.asList(request.getParameterValues("idBrand"));
        }
        this.priceFrom = request.getParameter("priceFrom");
        this.priceTo = request.getParameter("priceTo");
        this.sortType = request.getParameter("sortType");
        this.name = request.getParameter("productName");
        this.amountOnPage = request.getParameter("amountOnPage");
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(String priceFrom) {
        this.priceFrom = priceFrom;
    }

    public String getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(String priceTo) {
        this.priceTo = priceTo;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmountOnPage() {
        return amountOnPage;
    }

    public void setAmountOnPage(String amountOnPage) {
        this.amountOnPage = amountOnPage;
    }

    @Override
    public String toString() {
        return "UserRequestParameters{" +
                "pageNumber='" + pageNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", priceFrom='" + priceFrom + '\'' +
                ", priceTo='" + priceTo + '\'' +
                ", sortType='" + sortType + '\'' +
                ", category='" + category + '\'' +
                ", count=" + count +
                ", name='" + name + '\'' +
                ", amountOnPage='" + amountOnPage + '\'' +
                '}';
    }

    public List<String> getBrand() {
        return brand;
    }

    public void setBrand(List<String> brand) {
        this.brand = brand;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }
}
