package ra.service;

import org.springframework.stereotype.Service;
import ra.model.Catagory;
import ra.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Service
public class CatagoryService implements IGenericService<Catagory,Long> {
    @Override
    public List<Catagory> findAll() {
        Connection conn = ConnectionDB.getConnection();
        List<Catagory> catagorys = new ArrayList<>();
        try {
            CallableStatement callSt = conn.prepareCall("{call findAllCtategory}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Catagory p = new Catagory();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("nameCatagory"));
                p.setStatus(rs.getBoolean("status"));
                catagorys.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return catagorys;

    }

    @Override
    public void save(Catagory catagory) {
        Connection conn = ConnectionDB.getConnection();

        try {
            if (catagory.getId() == 0) {
                // thêm moi
                CallableStatement callSt = conn.prepareCall("{call insertCategory(?)}");
                callSt.setString(1, catagory.getName());
                callSt.executeUpdate();
            } else {
                // cap nhat
                CallableStatement callSt = conn.prepareCall("{call updateCategory(?,?)}");
                callSt.setLong(2, catagory.getId());
                callSt.setString(1, catagory.getName());
                callSt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }

    }

    @Override
    public void delete(Long id) {
        Connection conn = ConnectionDB.getConnection();
        try {
            boolean check = false;
            Catagory cat = findById(id);
            if (cat.isStatus() == false) {
                check=true;
            }
            CallableStatement callSt = conn.prepareCall("{call editStatusCategory(?,?)}");
            callSt.setLong(1,id);
            callSt.setBoolean(2,check);
            callSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    @Override
    public Catagory findById(Long id) {
        Connection conn = ConnectionDB.getConnection();
        Catagory p = null;
        try {
            CallableStatement callSt = conn.prepareCall("{call findByIdCategory(?)}");
            callSt.setLong(1,id);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                p = new Catagory();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("nameCatagory"));
                p.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return p;
    }
    public boolean checkExistCatagoryName(String name){
        for (Catagory p: findAll()) {
            if(p.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
}
