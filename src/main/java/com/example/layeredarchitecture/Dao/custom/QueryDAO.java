package com.example.layeredarchitecture.Dao.custom;

import com.example.layeredarchitecture.model.SearchDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO {
    ArrayList<SearchDto> search(String id) throws SQLException, ClassNotFoundException;
}
