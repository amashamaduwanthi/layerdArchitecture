package lk.ijse.layeredarchitecture.view.tdm;

import java.math.BigDecimal;

public class SearchOrderTM {
    private String code;
    private String qty;
    private String unitPrice;



    public SearchOrderTM(String code, String qty, String unitPrice) {
        this.code=code;
        this.qty=qty;
        this.unitPrice=unitPrice;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }



    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }


    @Override
    public String toString() {
        return "SearchOrderTM{" +
                "code='" + code + '\'' +
                ", qty='" + qty + '\'' +
                ", unitPrice='" + unitPrice + '\'' +

                '}';
    }


}
