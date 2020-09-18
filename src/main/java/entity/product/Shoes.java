package entity.product;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.Supplier;

public class Shoes implements Serializable,Cloneable {
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    private int id;
    private String name;
    private String description;
    private String image1;
    private String image2;
    private int category;
    private double price;
    private String brand;
    private int size;


    public Shoes() {

    }

    public Shoes(Supplier<? extends Shoes> ctor) {
        this((Shoes) ctor);
    }

    public <S extends Shoes> Shoes(S product) {
        name = product.getName();
        description = product.getDescription();
        category = product.getCategory();
        price = product.getPrice();
        id = product.getId();
        image1 = product.getImage1();
        image2 = product.getImage2();
        brand = product.getBrand();
    }

    public Shoes(String name, String description, int category, double price) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Shoes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image1='" + image1 + '\'' +
                ", image2='" + image2 + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shoes shoes = (Shoes) o;
        return id == shoes.id &&
                category == shoes.category &&
                Double.compare(shoes.price, price) == 0 &&
                size == shoes.size &&
                Objects.equals(name, shoes.name) &&
                Objects.equals(description, shoes.description) &&
                Objects.equals(image1, shoes.image1) &&
                Objects.equals(image2, shoes.image2) &&
                Objects.equals(brand, shoes.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, image1, image2, category, price, brand, size);
    }

}
