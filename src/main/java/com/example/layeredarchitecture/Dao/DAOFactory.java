package com.example.layeredarchitecture.Dao;

import com.example.layeredarchitecture.Dao.custom.impl.*;

public class DAOFactory {
    public static DAOFactory daoFactory;
    private DAOFactory(){
    }
    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)?daoFactory=new DAOFactory():daoFactory;
    }
    public enum DAOType{
        CUSTOMER,ITEM,ORDER,ORDER_DETAIL,QUERY
    }
    public SuperDAO getDAO(DAOType daoType) {
        switch (daoType) {
            case CUSTOMER:
                return new CustomerDAOImp();
            case ITEM:
                return new ItemDAOImp();
            case ORDER:
                return new OrderDAOImp();
            case ORDER_DETAIL:
                return new OrderDetailsDAOImp();
            case QUERY:
                return new QueryDAOImp();
            default:
                return null;
        }

    }
}
