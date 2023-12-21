package com.example.layeredarchitecture.model;

public class SearchOrderDTO {
    private String oid;
    private String date;
    private String itemcode;
    private String qty;
    private String unitprice;

    public SearchOrderDTO() {
    }

    public SearchOrderDTO(String oid, String date, String itemcode, String qty, String unitprice) {
        this.oid = oid;
        this.date = date;
        this.itemcode = itemcode;
        this.qty = qty;
        this.unitprice = unitprice;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }


    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(String unitprice) {
        this.unitprice = unitprice;
    }

    @Override
    public String toString() {
        return "AddtblDto{" +
                "oid='" + oid + '\'' +
                ", date='" + date + '\'' +
                ", itemcode='" + itemcode + '\'' +
                ", qty='" + qty + '\'' +
                ", unitprice='" + unitprice + '\'' +
                '}';
    }
}

