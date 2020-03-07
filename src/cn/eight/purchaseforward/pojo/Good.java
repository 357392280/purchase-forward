package cn.eight.purchaseforward.pojo;

public class Good {
    private String goodname;
    private String goodtype;
    private Double price;
    private String pic;
    private int id;

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    public String getGoodtype() {
        return goodtype;
    }

    public void setGoodtype(String goodtype) {
        this.goodtype = goodtype;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Good(int id,String goodname, String goodtype, Double price, String pic) {
        this.goodname = goodname;
        this.goodtype = goodtype;
        this.price = price;
        this.pic = pic;
        this.id = id;
    }

    public Good() {
    }
}
