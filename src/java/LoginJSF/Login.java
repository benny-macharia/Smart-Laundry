package LoginJSF;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;

@ManagedBean
@RequestScoped
public class Login {
    
    DBConnection db = new DBConnection();
    Connection myConn = db.myConnect();
    HasherSha1 myEncrypt = new HasherSha1();  
    
    private String email; 
    private String username;
    private String password;

    @Resource(name = "jdbc/YourDataSourceName")
    private DataSource dataSource;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
     
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String register() throws SQLException {
        
        Statement s = null;
        
        if(myConn != null){
            System.out.println(myConn);
            s = myConn.createStatement();
            String sql = "INSERT INTO user (user_id, email, username, password) VALUES (NULL, '" + getEmail() + "', '" + getUsername() + "', '" + myEncrypt.encryptPassword(getPassword()) + "')";
            s.execute(sql);
        }
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedInUser", getUsername());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedInEmail", getEmail());
        return "login";
    }
}
