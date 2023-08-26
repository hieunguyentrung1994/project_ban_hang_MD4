package ra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.Product;
import ra.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IGenericService<Product , Long> {
    @Autowired
    private CatagoryService catagoryService;

    @Override
    public List<Product> findAll() {
        Connection conn = ConnectionDB.getConnection();
        List<Product> products = new ArrayList<>();
        try {
            CallableStatement callSt = conn.prepareCall("{call findAllProduct}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getLong("id"));
                p.setNameProduct(rs.getString("nameProduct"));
                p.setStatus(rs.getBoolean("status"));
                p.setImg(rs.getString("img"));
                p.setPrice(rs.getDouble("price"));
                p.setContent(rs.getString("content"));
                p.setQuantityInStock(rs.getInt("quantityInStock"));
                p.setSex(rs.getBoolean("sex"));
                p.setIdCategory(catagoryService.findById(rs.getLong("idCatagory")));
                products.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return products;
    }

    @Override
    public void save(Product product) {
        Connection conn = ConnectionDB.getConnection();

        try {
            if (product.getId() == 0) {
                // thêm moi
                CallableStatement callSt = conn.prepareCall("{call insertProduct(?,?,?,?,?,?,?)}");
                callSt.setString(1, product.getNameProduct());
                callSt.setString(2, product.getImg());
                callSt.setDouble(3, product.getPrice());
                callSt.setString(4, product.getContent());
                callSt.setInt(5, product.getQuantityInStock());
                callSt.setBoolean(6, product.isSex());
                callSt.setLong(7,product.getIdCategory().getId());
                callSt.executeUpdate();



            } else {
                // cap nhat
                CallableStatement callSt = conn.prepareCall("{call updateProduct(?,?,?,?,?,?,?,?,?)}");
                callSt.setString(1, product.getNameProduct());
                callSt.setBoolean(2, product.isStatus());
                callSt.setString(3, product.getImg());
                callSt.setDouble(4, product.getPrice());
                callSt.setString(5, product.getContent());
                callSt.setInt(6, product.getQuantityInStock());
                callSt.setBoolean(7, product.isSex());
                callSt.setLong(8,product.getIdCategory().getId());
                callSt.setLong(9, product.getId());
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
            // xóa ảnh phụ
            CallableStatement callSt = conn.prepareCall("{call deleteProduct(?)}");
            callSt.setLong(1,id);
            callSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    @Override
    public Product findById(Long id) {
        Connection conn = ConnectionDB.getConnection();
        Product p = null;
        try {
            CallableStatement callSt = conn.prepareCall("{call findByIdProduct(?)}");
            callSt.setLong(1,id);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                p = new Product();
                p.setId(rs.getLong("id"));
                p.setNameProduct(rs.getString("nameProduct"));
                p.setStatus(rs.getBoolean("status"));
                p.setImg(rs.getString("img"));
                p.setPrice(rs.getDouble("price"));
                p.setContent(rs.getString("content"));
                p.setQuantityInStock(rs.getInt("quantityInStock"));
                p.setSex(rs.getBoolean("sex"));
                p.setIdCategory(catagoryService.findById(rs.getLong("idCatagory")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return p;
    }
    public boolean checkExistProductName(String name){
        for (Product p: findAll()) {
            if(p.getNameProduct().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
    public List<Product> getProductMen(){
        Connection conn = ConnectionDB.getConnection();
        List<Product> products = new ArrayList<>();
        try {
            CallableStatement callSt = conn.prepareCall("{call productBoy}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getLong("id"));
                p.setNameProduct(rs.getString("nameProduct"));
                p.setStatus(rs.getBoolean("status"));
                p.setImg(rs.getString("img"));
                p.setPrice(rs.getDouble("price"));
                p.setContent(rs.getString("content"));
                p.setQuantityInStock(rs.getInt("quantityInStock"));
                p.setSex(rs.getBoolean("sex"));
                p.setIdCategory(catagoryService.findById(rs.getLong("idCatagory")));
                products.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return products;
    }
    public List<Product> getProductgril(){
        Connection conn = ConnectionDB.getConnection();
        List<Product> products = new ArrayList<>();
        try {
            CallableStatement callSt = conn.prepareCall("{call productGril}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getLong("id"));
                p.setNameProduct(rs.getString("nameProduct"));
                p.setStatus(rs.getBoolean("status"));
                p.setImg(rs.getString("img"));
                p.setPrice(rs.getDouble("price"));
                p.setContent(rs.getString("content"));
                p.setQuantityInStock(rs.getInt("quantityInStock"));
                p.setSex(rs.getBoolean("sex"));
                p.setIdCategory(catagoryService.findById(rs.getLong("idCatagory")));
                products.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return products;
    }
    public List<Product> getProductNew(){
        Connection conn = ConnectionDB.getConnection();
        List<Product> products = new ArrayList<>();
        try {
            CallableStatement callSt = conn.prepareCall("{call productSortNew}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getLong("id"));
                p.setNameProduct(rs.getString("nameProduct"));
                p.setStatus(rs.getBoolean("status"));
                p.setImg(rs.getString("img"));
                p.setPrice(rs.getDouble("price"));
                p.setContent(rs.getString("content"));
                p.setQuantityInStock(rs.getInt("quantityInStock"));
                p.setSex(rs.getBoolean("sex"));
                p.setIdCategory(catagoryService.findById(rs.getLong("idCatagory")));
                products.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return products;
    }

}
