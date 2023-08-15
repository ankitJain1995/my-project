
package org.foodie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.foodie.dbuitl.DBConnection;
import org.foodie.pojo.StaffPojo;

/**
 *
 * @author AFROZ
 */
public class StaffDao {

    public static String addStaff(StaffPojo staff) throws SQLException {

        Connection conn = DBConnection.getConnection();
        conn.setAutoCommit(false);

        PreparedStatement ps = conn.prepareStatement("insert into staffs values (?,?,?,?)");
        staff.setStaffId(getNewStaffId());
        ps.setString(1, staff.getStaffId());
        ps.setString(2, staff.getCompanyId());
        ps.setString(3, staff.getEmailId());
        ps.setString(4, staff.getStaffName());

        boolean isUserAdded = UserDao.addUser(staff);
        if (!isUserAdded) {
            return null;
        }

        int result = ps.executeUpdate();

        if (result == 0) {
            conn.rollback();
            return null;
        }

//        boolean isUserAdded = UserDao.addUser(staff);
//        if (!isUserAdded) {
//            conn.rollback();
//            return false;
//        }
        conn.commit();

        return staff.getStaffId();

    }

    public static StaffPojo getStaffById(String staffId) throws SQLException {
        Connection conn = DBConnection.getConnection();
//        String qry = "SELECT users.mobile_no, staffs.staff_id, staffs.email_id, staffs.staff_name FROM  users, staffs WHERE staffs.email_id = users.email_id AND staffs.staff_id = ?";

        String qry = "SELECT u.mobile_no, s.staff_id, s.email_id, s.staff_name "
                + "FROM users u "
                + "JOIN staffs s ON s.email_id = u.email_id "
                + "WHERE s.staff_id = ?";
        PreparedStatement query = conn.prepareStatement(qry);
        query.setString(1, staffId);
        ResultSet result = query.executeQuery();

        result.next();

        StaffPojo staff = new StaffPojo();
        staff.setStaffId(staffId);
        staff.setEmailId(result.getString("email_id"));
        staff.setStaffName(result.getString("staff_name"));
        staff.setMobileNo(result.getString("mobile_no"));
        return staff;

    }

    public static ArrayList<String> getStaffIds(String companyId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement query = conn.prepareStatement("select staff_id from staffs where company_id=?");
        query.setString(1, companyId);
        ResultSet executeQuery = query.executeQuery();
        ArrayList<String> staffList = new ArrayList<>();

        while (executeQuery.next()) {
            staffList.add(executeQuery.getString("staff_id"));
        }

        return staffList;
    }

    public static ArrayList<String> getStaffIdsByCompanyName(String companyName) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement query = conn.prepareStatement("SELECT s.staff_id "
                + "FROM staffs s "
                + "INNER JOIN companies c ON c.company_id = s.company_id "
                + "WHERE c.company_name = ?");
        query.setString(1, companyName);
        ResultSet executeQuery = query.executeQuery();
        ArrayList<String> staffList = new ArrayList<>();

        while (executeQuery.next()) {
            staffList.add(executeQuery.getString("staff_id"));
        }

        return staffList;
    }

    public static String getNewStaffId() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("Select count(*) from staffs");
        rs.next();
        return "STF" + (100 + rs.getInt(1));
    }

    public static String getRandomStaffIdFromCompany(String companyId) throws SQLException {
        ArrayList<String> staffIds = getStaffIds(companyId);
        int index = new Random().nextInt(staffIds.size());
        return staffIds.get(index);
    }

    public static Map<String, String> getRandomStaffDetailsFromCompany(String companyName) throws SQLException {

        ArrayList<String> staffIds = getStaffIdsByCompanyName(companyName);
        int index = new Random().nextInt(staffIds.size());

        Connection conn = DBConnection.getConnection();
        PreparedStatement query = conn.prepareStatement("SELECT s.staff_name, u.mobile_no "
                + "FROM staffs s, users u "
                + "WHERE s.staff_id = ?");
        query.setString(1, staffIds.get(index));
        ResultSet result = query.executeQuery();
        Map<String, String> staffDetailsMap = new HashMap<>();
        if (result.next()) {
            staffDetailsMap.put("staffId", staffIds.get(index));
            staffDetailsMap.put("staffName", result.getString("staff_name"));
            staffDetailsMap.put("mobileNo", result.getString("mobile_no"));
            return staffDetailsMap;
        }

        return staffDetailsMap;
    }
}
