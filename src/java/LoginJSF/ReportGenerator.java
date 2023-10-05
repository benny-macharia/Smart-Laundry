/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginJSF;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.sql.DataSource;

/**
 *
 * @author benny
 */
@ManagedBean
@RequestScoped

public class ReportGenerator {
    DBConnection db = new DBConnection();
    Connection myConn = db.myConnect();
    
    @Resource(name = "jdbc/YourDataSourceName")
    private DataSource dataSource;
    
    
    private int id;
    private int basketId;
    private Date date;
    private int lastReading;
    private int currentReading;
    private int amountAdded;
    private String condition;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public int getBasketId() {
        return basketId;
    }

    public void setBasketId(int basketId) {
        this.basketId = basketId;
    }

    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    
    public int getLastReading() {
        return lastReading;
    }

    public void setLastReading(int lastReading) {
        this.lastReading = lastReading;
    }

    
    public int getCurrentReading() {
        return currentReading;
    }

    public void setCurrentReading(int currentReading) {
        this.currentReading = currentReading;
    }

    
    public int getAmountAdded(){
        return amountAdded; 
    }
    

    public void setAmountAdded(int amountAdded) {
        this.amountAdded = amountAdded;
    }

    
    private List<ReportGenerator> reports = new ArrayList<>();

public List<ReportGenerator> getReports() {
    try {
        reports.clear();
        Statement s = myConn.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM reports");

        while (rs.next()) {
            ReportGenerator report = new ReportGenerator();
            report.setId(rs.getInt("reports_id"));
            report.setBasketId(rs.getInt("basket_id"));
            report.setDate(rs.getDate("date"));
            report.setLastReading(rs.getInt("last_reading"));
            report.setCurrentReading(rs.getInt("current_reading"));
            
            
            
            report.setAmountAdded(rs.getInt("amount_added"));
            
            reports.add(report);
            System.out.println("Last Reading: " + rs.getInt("last_reading"));
            System.out.println("Current Reading: " + rs.getInt("current_reading"));
            System.out.println("Amount Added: " + rs.getInt("amount_added"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return reports;
    
}
public static void main(String args[]){
    ReportGenerator test = new ReportGenerator();
        List<ReportGenerator> reports1 = test.getReports();
}
}
