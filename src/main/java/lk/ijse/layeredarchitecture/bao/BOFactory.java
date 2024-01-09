package lk.ijse.layeredarchitecture.bao;

import lk.ijse.layeredarchitecture.Dao.SuperDAO;
import lk.ijse.layeredarchitecture.bao.custom.impl.CustomerBoIMPL;
import lk.ijse.layeredarchitecture.bao.custom.impl.ItemBoImpl;
import lk.ijse.layeredarchitecture.bao.custom.impl.PlaceOrderBoIMPL;

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