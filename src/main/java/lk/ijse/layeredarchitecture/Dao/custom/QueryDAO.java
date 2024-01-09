package lk.ijse.layeredarchitecture.Dao.custom;

import lk.ijse.layeredarchitecture.Dao.SuperDAO;
import lk.ijse.layeredarchitecture.dto.SearchDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    ArrayList<SearchDto> search(String id) throws SQLException, ClassNotFoundException;
}
