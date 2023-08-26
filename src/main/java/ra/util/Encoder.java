package ra.util;

// mã hóa mật khẩu chống chộm mật khẩu

// Import các thư viện cần thiết
import javax.xml.bind.DatatypeConverter; // Thư viện để chuyển đổi byte[] sang chuỗi hex
import java.security.MessageDigest; // Thư viện hỗ trợ tính toán mã hóa dựa trên thuật toán
import java.security.NoSuchAlgorithmException; // Lớp ngoại lệ cho các trường hợp thuật toán mã hóa không tồn tại

public class Encoder {
    // Hàm tĩnh mã hóa một chuỗi dùng thuật toán MD5
    public static String encode(String password){
        MessageDigest md = null; // Khởi tạo đối tượng MessageDigest với giá trị ban đầu là null

        try {
            // Cố gắng lấy thể hiện của thuật toán MD5
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            // Nếu thuật toán MD5 không tồn tại, ném ngoại lệ RuntimeException
            throw new RuntimeException(e);
        }

        // Cập nhật dữ liệu cần được mã hóa vào đối tượng md
        md.update(password.getBytes());

        // Thực hiện mã hóa và lấy kết quả là mảng byte
        byte[] digest = md.digest();

        // Chuyển đổi mảng byte sang chuỗi hex và viết hoa tất cả các ký tự
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();

        // Trả về chuỗi đã được mã hóa
        return myHash;
    }
}

