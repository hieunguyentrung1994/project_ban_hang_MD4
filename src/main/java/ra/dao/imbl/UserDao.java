package ra.dao.imbl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.dao.IGenericDao;
import ra.dto.request.FormLoginDto;
import ra.model.Role;
import ra.model.User;
import ra.service.RoleService;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Component
public class UserDao implements IGenericDao<User,Long> {
@Autowired
    private RoleService roleService;
    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        Connection conn =null;
        try{
            conn = ConnectionDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call getAllUsers}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setFullName(rs.getString("fullName"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setAddress(rs.getString("address"));
                user.setAvantar(rs.getString("avantar"));
                user.setIdrole(roleService.findById(rs.getLong("idRole")));
                user.setStatus(rs.getBoolean("status"));
                list.add(user);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public User findById(Long id) {
        User user =null;
        Connection conn =null;
        try {
            // mỏ kết nối
            conn = ConnectionDB.getConnection();

            // chuẩn bị câu lệnh
            CallableStatement callSt = conn.prepareCall("{call findUserById(?)}");
            // truyền đối số
            // thực thi câu lệnh xóa
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFullName(rs.getString("fullName"));
                user.setEmail(rs.getString("email"));
                user.setAvantar(rs.getString("avantar"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setAddress(rs.getString("address"));
                user.setIdrole(roleService.findById(rs.getLong("idRole")));
                user.setStatus(rs.getBoolean("status"));
            }

        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn);
        }
        return user;
    }

    @Override
    public void save(User user) {
        Connection conn =null;
        try {
            // mỏ kết nối
            conn = ConnectionDB.getConnection();

            // chuẩn bị câu lệnh
            CallableStatement callSt = null;
            if(user.getId()==0){
                // chức năng thêm mới
                callSt=conn.prepareCall("{call insertUser(?,?,?)}");
                callSt.setString(1,user.getUserName());
                callSt.setString(2,user.getEmail());
                callSt.setString(3,user.getPassword());

                // thực thi câu lệnh sql
                callSt.executeUpdate();
            }else {
                // cập nhật
                callSt = conn.prepareCall("{call updateUser(?,?,?,?,?,?)}");
                callSt.setString(1, user.getFullName());
                callSt.setString(2,user.getPassword());
                callSt.setString(3, user.getAddress());
                callSt.setString(4,user.getAvantar());
                callSt.setString(5,user.getPhoneNumber());
                callSt.setLong(6,user.getId());
                // thực thi câu lệnh sql
                callSt.executeUpdate();
            }

        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn);
        }

    }

    @Override
    public void delete(Long id) {
    }
    public User login (FormLoginDto formLoginDto){
        User user =null;
        Connection conn =null;
        try {
            conn = ConnectionDB.getConnection();

            CallableStatement callSt = conn.prepareCall("{call login(?,?)}");
            callSt.setString(1,formLoginDto.getUsername());
            callSt.setString(2,formLoginDto.getPassword());
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setFullName(rs.getString("fullName"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setAvantar(rs.getString("avantar"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setAddress(rs.getString("address"));
                user.setIdrole(roleService.findById(rs.getLong("idRole")));
                user.setStatus(rs.getBoolean("status"));
            }

        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn);
        }
        return user;
    }
    public void tongleUserStatus(Integer id){
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call tongleUserStatus(?)}");
            callSt.setInt(1, id);
            callSt.executeUpdate();
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    public boolean checkUsername(String username){
        Connection conn =null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call checkUser(?)}");
            callSt.setString(1, username);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()){
                return false;
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn);
        }
        return true;
    }
    public boolean checkEmail(String email){
        Connection conn =null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call checkEmail(?)}");
            callSt.setString(1, email);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()){
                return false;
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn);
        }
        return true;
    }
}
