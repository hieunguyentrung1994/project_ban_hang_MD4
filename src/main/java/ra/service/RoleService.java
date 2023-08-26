package ra.service;

import org.springframework.stereotype.Service;
import ra.model.Product;
import ra.model.Role;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Service
public class RoleService implements IGenericService<Role,Long>{
    @Override
    public List<Role> findAll() {
        Connection conn = ConnectionDB.getConnection();
        List<Role> roles= new ArrayList<>();
        try {
            CallableStatement callSt = conn.prepareCall("{call findAllRole}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Role r = new Role();
                r.setId(rs.getLong("id"));
                r.setName("nameRole");
                roles.add(r);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return roles;
    }

    @Override
    public void save(Role role) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Role findById(Long id) {
        Connection conn = ConnectionDB.getConnection();
        Role r = null;
        try {
            CallableStatement callSt = conn.prepareCall("{call findByIdRole(?)}");
            callSt.setLong(1,id);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                r = new Role();
                r.setId(rs.getLong("id"));
                r.setName(rs.getString("nameRole"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return r;
    }
}
