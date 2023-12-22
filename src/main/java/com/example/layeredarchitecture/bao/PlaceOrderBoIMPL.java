package com.example.layeredarchitecture.bao;

import com.example.layeredarchitecture.Dao.DAOFactory;
import com.example.layeredarchitecture.Dao.custom.ItemDAO;
import com.example.layeredarchitecture.Dao.custom.OrderDAO;
import com.example.layeredarchitecture.Dao.custom.OrderDetailDAO;
import com.example.layeredarchitecture.Dao.custom.impl.CustomerDAOImp;
import com.example.layeredarchitecture.Dao.custom.impl.ItemDAOImp;
import com.example.layeredarchitecture.Dao.custom.impl.OrderDAOImp;
import com.example.layeredarchitecture.Dao.custom.impl.OrderDetailsDAOImp;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBoIMPL implements PlaceOrderBo{
    CustomerDAOImp customerDAOImp = (CustomerDAOImp) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER);
    ItemDAO itemDAOImp = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);
    OrderDAO orderDAOImp= (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDER);
    OrderDetailDAO orderDetailsDAOImp = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDER_DETAIL);
    @Override
    public boolean PlaceOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        Connection connection = null;

            connection = DBConnection.getDbConnection().getConnection();


            boolean isExit = orderDAOImp.exitOrder(orderId);
            if (isExit){
                connection.setAutoCommit(false);

            }
            connection.setAutoCommit(false);

            boolean isSaved = orderDAOImp.saveOrder(orderId, orderDate, customerId);
            if(!isSaved){
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
            for (OrderDetailDTO detail:orderDetails){
                boolean isOk = orderDetailsDAOImp.save(orderId,detail);

                /*if order id already exist*/
                if(!isOk) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
                //                //Search & Update Item

               ItemDTO item = findItem(detail.getItemCode());

                item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());
                boolean updateitem = itemDAOImp.update(item);
                if (!updateitem){
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }

            }
            connection.commit();
            connection.setAutoCommit(true);
            return true;

    }
    @Override
    public ItemDTO findItem(String code) {
        try {
            return   itemDAOImp.FindItem(code);

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAOImp.search(id);
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return orderDAOImp.generateNextId();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAOImp.getAll();
    }

    @Override
    public ArrayList<ItemDTO> getAllItemrId() throws SQLException, ClassNotFoundException {
        return itemDAOImp.getAll();
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAOImp.exist(id);
    }

    @Override
    public boolean existItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAOImp.exist(id);
    }

}

