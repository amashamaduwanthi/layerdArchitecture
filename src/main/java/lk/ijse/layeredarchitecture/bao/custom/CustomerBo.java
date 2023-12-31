package lk.ijse.layeredarchitecture.bao.custom;

import lk.ijse.layeredarchitecture.Dao.SuperDAO;
import lk.ijse.layeredarchitecture.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBo extends SuperDAO {
    ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException ;
    boolean existCustomer(String id) throws SQLException, ClassNotFoundException;
    boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException ;
    boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException ;
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException ;
    String generateNextId() throws SQLException, ClassNotFoundException ;
}
