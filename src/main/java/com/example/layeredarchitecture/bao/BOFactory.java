package com.example.layeredarchitecture.bao;

import com.example.layeredarchitecture.Dao.DAOFactory;
import com.example.layeredarchitecture.Dao.SuperDAO;
import com.example.layeredarchitecture.Dao.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBoFactory() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public void getBO() {

    }

    public enum BOType {
        CUSTOMER, ITEM, PLACE_ORDER;

    }

    public SuperDAO getBO(BOType boType) {
        switch (boType) {
            case CUSTOMER:
                return new CustomerBoIMPL();
            case ITEM:
                return new ItemBoImpl();
            case PLACE_ORDER:
                return new PlaceOrderBoIMPL();

            default:
                return null;
        }
    }
}