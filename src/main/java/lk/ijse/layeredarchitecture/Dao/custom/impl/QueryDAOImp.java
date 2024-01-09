package lk.ijse.layeredarchitecture.Dao.custom.impl;

import lk.ijse.layeredarchitecture.Dao.custom.QueryDAO;
import lk.ijse.layeredarchitecture.SqlUtil.Sqlutil;
import lk.ijse.layeredarchitecture.dto.SearchDto;
import lk.ijse.layeredarchitecture.dto.SearchOrderDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImp implements QueryDAO {
    @Override
    public ArrayList<SearchDto> search(String id) throws SQLException, ClassNotFoundException {

            ResultSet rst = Sqlutil.execute("SELECT c.name, o.oid, o.date from Customer c join Orders o on c.id = o.customerID where o.customerID = ?",id);

            ArrayList<SearchDto> getAllsearch = new ArrayList<>();

            while (rst.next()) {
                SearchDto searchDto = new SearchDto(rst.getString(1), rst.getString(2), rst.getString(3));
                getAllsearch.add(searchDto);
            }

            return getAllsearch;
        }
    public ArrayList<SearchOrderDTO> searchOrderDetail(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst =Sqlutil.execute("SELECT o.oid, o.date, od.itemCode, od.qty, od.unitPrice from OrderDetails od join Orders o on od.oid = o.oid where o.oid = ?",id);

        ArrayList<SearchOrderDTO> getAlldetails = new ArrayList<>();

        while (rst.next()) {
            SearchOrderDTO addtblDto = new SearchOrderDTO(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5));
            getAlldetails.add(addtblDto);
        }

        return getAlldetails;
    }


}

