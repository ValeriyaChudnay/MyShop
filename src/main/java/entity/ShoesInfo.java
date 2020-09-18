package entity;

import entity.product.Shoes;

public final class ShoesInfo {
    private final int shoesId;
    private final int amount;
    private final double price;
    private final String title;

    public int getShoesId() {
        return shoesId;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public ShoesInfo(Shoes shoes, int amount){
        this.amount = amount;
        shoesId=shoes.getId();
        price=shoes.getPrice();
        title=shoes.getName();
    }
}
