package ra.dto.request;

import org.springframework.web.multipart.MultipartFile;
import ra.model.Catagory;

public class FormProductDto {
    private Long id;
    private String nameProduct;
    private MultipartFile img;
    private String content;
    private double price;
    private int quantityInStock;
    private boolean sex;
    private boolean status;
    private Catagory idCategory;

    public FormProductDto() {
    }

    public FormProductDto(Long id, String nameProduct, MultipartFile img, String content, double price, int quantityInStock, boolean sex, boolean status, Catagory idCategory) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.img = img;
        this.content = content;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.sex = sex;
        this.status = status;
        this.idCategory = idCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Catagory getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Catagory idCategory) {
        this.idCategory = idCategory;
    }
}
