package org.samarskii.domain;

public class Product {
    private int id;
    private String name;
    private int amount;

    public Product(){}

    public Product(int id, String name, int amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String toHtml(){
        return  "<form class=\"product\" action=\"{actionPath}\" method=\"post\">" +
                "    <input type=\"text\" hidden value=\"" + this.getId() +"\" name=\"product-id\">" +
                "    <label>amount: </label><label>" + this.getAmount() + "</label><br/>" +
                "    <label class=\"product-name\">" + this.getName() + "</label>" +
                "    <input class=\"product-buy\" type=\"submit\" value=\"buy\">" +
                "</form>";
    }
    public String toHtmlForBasket(){
        return  "<div class=\"product\" >\n" +
                "    <input type=\"text\" hidden value=\"" + this.getId() +"\" name=\"product-id\">\n" +
                "    <label>amount: </label><label>{amountInBasket}</label><br/>\n" +
                "    <label class=\"product-name\">" + this.getName() + "</label>\n" +
                "</div>";
    }
}
