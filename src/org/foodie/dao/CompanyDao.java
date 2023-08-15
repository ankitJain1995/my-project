
package org.foodie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import org.foodie.dbuitl.DBConnection;
import org.foodie.pojo.CompanyPojo;

/**
 *
 * @author AFROZ
 */

public class CompanyDao {

    public static Map<String, String> validateSeller(String companyName, String password) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry = "Select company_id,owner_name from companies where company_name=? and password=? AND status = 'ACTIVE'";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, companyName);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        Map<String, String> seller = null;
        if (rs.next()) {
            seller = new HashMap<>();
            seller.put("companyId", rs.getString("company_id"));
            seller.put("ownerName", rs.getString("owner_name"));
            seller.put("companyName", companyName);
        }
        return seller;
    }

    public static boolean addSeller(CompanyPojo seller) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry = "INSERT INTO companies VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, getNewId());
        ps.setString(2, seller.getCompanyName());
        ps.setString(3, seller.getOwnerName());
        ps.setString(4, seller.getPassword());
        ps.setString(5, "ACTIVE");
        ps.setString(6, seller.getEmailId());
        ps.setString(7, seller.getSecurityKey());

        int result = ps.executeUpdate();
        return result > 0;
    }

    public static String getNewId() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("Select count(*) from companies");
        rs.next();
        return "CMP" + (100 + rs.getInt(1));
    }

    public static Map<String, String> getAllComanyNamesIdsMap() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement query = conn.createStatement();
//        String qry = "SELECT c.company_id, c.company_name FROM products  p, companies c "
//                + "WHERE p.company_id = c.company_id AND c.status = 'ACTIVE' "
//                + "GROUP BY c.company_id, c.company_name";

        String qry = "SELECT c.company_id, c.company_name "
                + "FROM companies c "
                + "JOIN products p ON p.company_id = c.company_id "
                + "WHERE c.status = 'ACTIVE' "
                + "GROUP BY c.company_id, c.company_name";
        ResultSet result = query.executeQuery(qry);
        Map<String, String> companyNamesIdsMap = new HashMap<>();

        while (result.next()) {
            companyNamesIdsMap.put(result.getString("company_name"), result.getString("company_id"));
        }

        return companyNamesIdsMap;
    }

    public static boolean removeCompanyBy(String companyId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement query = conn.prepareStatement("UPDATE companies SET status = 'INACTIVE' WHERE company_id = ?");
        query.setString(1, companyId);
        int result = query.executeUpdate();
        System.out.println("setting the company to INACTIVE status " + result);
        return result > 0;
    }

    public static Map<String, String> getSellerEmailCredentialByCompanyId(String companyId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement query = conn.prepareStatement("SELECT email_id,security_key FROM companies WHERE company_id = ?");
        query.setString(1, companyId);
        ResultSet result = query.executeQuery();
        Map<String, String> emailCredentialMap = new HashMap<>();
        if (result.next()) {
            emailCredentialMap.put("emailId", result.getString("email_id"));
            emailCredentialMap.put("securityKey", result.getString("security_key"));
        }
        return emailCredentialMap;
    }
    
    public static Map<String, String> getSellerEmailCredentialByCompanyEmail(String emailId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement query = conn.prepareStatement("SELECT security_key FROM companies WHERE email_id = ?");
        query.setString(1, emailId);
        ResultSet result = query.executeQuery();
        Map<String, String> emailCredentialMap = new HashMap<>();
        if (result.next()) {
            emailCredentialMap.put("emailId", emailId);
            emailCredentialMap.put("securityKey", result.getString("security_key"));
        }
        return emailCredentialMap;
    }

}
