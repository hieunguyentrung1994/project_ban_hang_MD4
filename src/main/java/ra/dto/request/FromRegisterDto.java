package ra.dto.request;

import org.springframework.validation.Errors;
import ra.service.UserService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FromRegisterDto {
    private String userName;
    private String password;
    private String email;


    public FromRegisterDto(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;

    }

    public FromRegisterDto() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    private boolean isValidEmail(String email) {
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}");
    }

    private boolean isValidPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public void checkValidateRegister(Errors errors, UserService userService){

        // kiểm tra trường username có để trống hay không
        if(this.userName.trim().equals("")) {
            errors.rejectValue("username", "username.empty");
        }else if(this.userName.length()<6){
            errors.rejectValue("password", "password.regex");
        } else if(!userService.checkUsername(this.userName)){
            errors.rejectValue("username","username.duplicate");
        }else  if (this.email == null || this.email.trim().isEmpty()) {
            errors.rejectValue("email", "email.empty");
        } else if (!isValidEmail(this.email)) {
            errors.rejectValue("email", "email.invalid");
        }else if(!userService.checkEmail(this.email)){
            errors.rejectValue("email", "email.duplicate");
        }else if (this.password == null ){
            errors.rejectValue("password", "password.empty");
        }else if (!isValidPassword(this.password)) {
            errors.rejectValue("password", "password.invalid");
        }
    }
}
