package lk.ijse.layeredarchitecture.bao.custom.impl;

import lk.ijse.layeredarchitecture.Dao.DAOFactory;
import lk.ijse.layeredarchitecture.Dao.custom.ItemDAO;
import lk.ijse.layeredarchitecture.Dao.custom.OrderDAO;
import lk.ijse.layeredarchitecture.Dao.custom.OrderDetailDAO;
import lk.ijse.layeredarchitecture.Dao.custom.impl.CustomerDAOImp;
import lk.ijse.layeredarchitecture.bao.custom.PlaceOrderBo;
import lk.ijse.layeredarchitecture.db.DBConnection;
import lk.ijse.layeredarchitecture.dto.CustomerDTO;
import lk.ijse.layeredarchitecture.dto.ItemDTO;
import lk.ijse.layeredarchitecture.dto.OrderDetailDTO;
import lk.ijse.layeredarchitecture.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBoIMPL implements PlaceOrderBo {
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


  /*  public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAOImp.search(id);
    }*/

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return orderDAOImp.generateNextId();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomerId() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO>customerDTOS=new ArrayList<>();
        ArrayList<Customer>customers=customerDAOImp.getAll();
        for(Customer customer:customers){
            customerDTOS.add(new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress()));
        }
        return customerDTOS;
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

