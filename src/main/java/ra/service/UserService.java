package ra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.dao.imbl.UserDao;
import ra.dto.request.FormLoginDto;
import ra.dto.request.FromRegisterDto;
import ra.model.Catagory;
import ra.model.User;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<User> findAll() {
        return userDao.findAll();
    }


    public User findById(int id) {
        return userDao.findById((long) id);
    }


    public void save(FromRegisterDto formRegiterDto) {
        // chuyen doi dto sang model
        User user = new User();
        user.setUserName(formRegiterDto.getUserName());
        user.setPassword(formRegiterDto.getPassword());
        user.setEmail(formRegiterDto.getEmail());
        userDao.save(user);
    }

    public boolean checkUsername(String username) {
        return   userDao.checkUsername(username);
    }
    public boolean checkEmail(String email) {
        return userDao.checkEmail(email);
    }


    public void delete(Long id) {

    }
    public User login(FormLoginDto formLoginDto){
        return userDao.login(formLoginDto);
    }

    public void toggleUserStatus(int id){
        userDao.tongleUserStatus(id);
    }

    public void status(Long id){
        Connection conn = ConnectionDB.getConnection();
        try {
            CallableStatement callSt = conn.prepareCall("{call editStatusUser(?)}");
            callSt.setLong(1,id);
            callSt.executeUpdate();
            }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }

}
