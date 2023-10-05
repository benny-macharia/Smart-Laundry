
package LoginJSF;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;

@ManagedBean
@RequestScoped


public class basketRegistrationBean {
     DBConnection db = new DBConnection();
    Connection myConn = db.myConnect();
    
    @Resource(name = "jdbc/YourDataSourceName")
    private DataSource dataSource;
    
    String b_name; 
    int b_capacity; 

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    public int getB_capacity() {
        return b_capacity;
    }

    public void setB_capacity(int b_capacity) {
        this.b_capacity = b_capacity;
    }
    
    public String register() throws SQLException {
        
        Statement s = null;
        
        if(myConn != null){
            System.out.println(myConn);
            s = myConn.createStatement();
            String sql = "INSERT INTO basket (basket_id, b_name, b_capacity) VALUES (NULL, '" + getB_name() + "', '" + getB_capacity() + "')";
            s.execute(sql);
        }
        return "collections";
    }
    
    private List<basketRegistrationBean> baskets = new ArrayList<>();

    public List<basketRegistrationBean> getBaskets() {
        try {
            Statement s = myConn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM basket");

            while (rs.next()) {
                basketRegistrationBean basket = new basketRegistrationBean();
                basket.setB_name(rs.getString("b_name"));
                basket.setB_capacity(rs.getInt("b_capacity"));
                baskets.add(basket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return baskets;
    }
    
    public String deleteBasket(String b_name, int b_capacity) {
    try {
        Statement statement = myConn.createStatement();
        String sql = "DELETE FROM basket WHERE b_name = '" + b_name + "' AND b_capacity = " + b_capacity;
        statement.executeUpdate(sql);
    } catch (SQLException e) {
        e.printStackTrace(); // Handle the exception appropriately
    }
    return "collections";
}


    
    
}
