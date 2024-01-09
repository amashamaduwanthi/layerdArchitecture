package lk.ijse.layeredarchitecture.bao.custom.impl;

import lk.ijse.layeredarchitecture.Dao.DAOFactory;
import lk.ijse.layeredarchitecture.Dao.custom.CustomerDAO;
import lk.ijse.layeredarchitecture.bao.custom.CustomerBo;
import lk.ijse.layeredarchitecture.dto.CustomerDTO;
import lk.ijse.layeredarchitecture.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBoIMPL implements CustomerBo {
    CustomerDAO customerDAOImp = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER);
    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO>customerDTOS=new ArrayList<>();
        ArrayList<Customer>customers=customerDAOImp.getAll();
        for(Customer customer:customers){
            customerDTOS.add(new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress()));
        }
        return customerDTOS;
    }



    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAOImp.exist(id);
    }

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAOImp.save(new Customer(dto.getId(),dto.getName(),dto.getAddress()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAOImp.update(new Customer(dto.getId(),dto.getName(),dto.getAddress()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAOImp.delete(id);
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return customerDAOImp.generateNextId();
    }
}
