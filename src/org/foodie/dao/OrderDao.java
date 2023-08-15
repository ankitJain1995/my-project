
package org.foodie.dao;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.imageio.ImageIO;
import org.foodie.dbuitl.DBConnection;
import org.foodie.pojo.OrderPojo;
import org.foodie.pojo.PlaceOrderPojo;
import org.foodie.pojo.ProductPojo;

/**
 *
 * @author AFROZ
 */

public class OrderDao {

    public static ArrayList<OrderPojo> getOrderHistoryForSeller(String companyName) throws SQLException {
        Connection conn = DBConnection.getConnection();
//        String qry = "SELECT p.product_name, p.product_price, cu.customer_name, s.staff_name, cu.address, o.review "
//                + "FROM orders o, products p, customers cu, staffs s "
//                + "WHERE o.product_id = p.product_id AND o.customer_id = cu.customer_id AND "
//                + "o.staff_id = s.staff_id AND o.company_id = ? AND o.status = 'DELIVERED'";

        String qry = "SELECT p.product_name, p.product_price, cu.customer_name, s.staff_name, cu.address, o.review "
                + "FROM orders o "
                + "JOIN products p ON o.product_id = p.product_id "
                + "JOIN customers cu ON o.customer_id = cu.customer_id "
                + "JOIN staffs s ON o.staff_id = s.staff_id "
                + "WHERE o.company_id = ? "
                + "  AND o.status = 'DELIVERED'";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, companyName);
        ResultSet rs = ps.executeQuery();
        ArrayList<OrderPojo> orderList = new ArrayList<>();
        OrderPojo order;
        while (rs.next()) {
            order = new OrderPojo();
            order.setProductName(rs.getString("product_name"));
            order.setProductPrice(rs.getDouble("product_price"));
            order.setCustomerName(rs.getString("customer_name"));
            order.setCustomerAddress(rs.getString("address"));
            order.setDeliveryStaffName(rs.getString("staff_name"));
            order.setReview(rs.getString("review"));
            orderList.add(order);
        }
        System.out.println("orders are " + orderList);
        return orderList;
    }

    public static ArrayList<OrderPojo> getNewOrderForDeliveryStaff(String staffId) throws SQLException {
        Connection conn = DBConnection.getConnection();
//        String qry = "SELECT o.order_id, o.otp p.product_name, p.product_price, c.customer_name, c.address, u.mobile_no "
//                + "FROM orders o, products p, customers c, users u "
//                + "WHERE o.product_id = p.product_id AND o.customer_id = c.customer_id AND "
//                + "c.email_id = u.email_id AND o.staff_id = ? AND o.status = 'ORDERED'";

        String qry = "SELECT o.order_id, o.otp, p.product_name, p.product_price, c.customer_name, c.address, u.mobile_no "
                + "FROM orders o "
                + "JOIN products p ON o.product_id = p.product_id "
                + "JOIN customers c ON o.customer_id = c.customer_id "
                + "JOIN users u ON c.email_id = u.email_id "
                + "WHERE o.staff_id = ? "
                + "  AND o.status = 'ORDERED' "
                + "ORDER BY o.order_id DESC";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, staffId);
        ResultSet rs = ps.executeQuery();
        ArrayList<OrderPojo> orderList = new ArrayList<>();
        OrderPojo order;
        while (rs.next()) {
            order = new OrderPojo();
            order.setOrderId(rs.getString("order_id"));
            order.setProductName(rs.getString("product_name"));
            order.setProductPrice(rs.getDouble("product_price"));
            order.setCustomerName(rs.getString("customer_name"));
            order.setCustomerAddress(rs.getString("address"));
            order.setCustomerPhoneNo(rs.getString("mobile_no"));
            order.setOtp(rs.getInt("otp"));
            orderList.add(order);
        }
        return orderList;
    }

    public static ArrayList<OrderPojo> getOrderHistoryForDeliveryStaff(String staffId) throws SQLException {
        Connection conn = DBConnection.getConnection();
//        String qry = "SELECT p.product_name, p.product_price, c.customer_name, c.address, o.review "
//                + "FROM orders o, products p, customers c "
//                + "WHERE o.product_id = p.product_id AND o.customer_id = c.customer_id AND "
//                + "o.staff_id = ? AND o.status = 'DELIVERED'";

        String qry = "SELECT p.product_name, p.product_price, c.customer_name, c.address, o.review "
                + "FROM orders o "
                + "JOIN products p ON o.product_id = p.product_id "
                + "JOIN customers c ON o.customer_id = c.customer_id "
                + "WHERE o.staff_id = ? "
                + "  AND o.status = 'DELIVERED'";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, staffId);
        ResultSet rs = ps.executeQuery();
        ArrayList<OrderPojo> orderList = new ArrayList<>();
        OrderPojo order;
        while (rs.next()) {
            order = new OrderPojo();
            order.setProductName(rs.getString("product_name"));
            order.setProductPrice(rs.getDouble("product_price"));
            order.setCustomerName(rs.getString("customer_name"));
            order.setCustomerAddress(rs.getString("address"));
            order.setReview(rs.getString("review"));
            orderList.add(order);
        }
        return orderList;
    }

    // this is for testing purpose.
    public static ArrayList<OrderPojo> getOrderHistoryFor(String companyName) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry = "select o.order_id, o.company_name, p.product_name, p.product_price, u.name, o.address, u.mobile_no, o.staff_name, o.status, o.review "
                + "from orders o,products p, users u  "
                + "where  "
                + "o.product_id = p.product_id "
                + "and "
                + "o.email_id = u.email_id "
                + "and "
                + "o.company_name = ?";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, companyName);
        ResultSet rs = ps.executeQuery();
        ArrayList<OrderPojo> orderList = new ArrayList<>();
        OrderPojo order;
        while (rs.next()) {
            order = new OrderPojo();
            order.setOrderId(rs.getString("order_id"));
            order.setCompanyName(rs.getString("company_name"));
            order.setProductName(rs.getString("product_name"));
            order.setProductPrice(rs.getDouble("product_price"));
            order.setCustomerName(rs.getString("name"));
            order.setCustomerAddress(rs.getString("address"));
            order.setCustomerPhoneNo(rs.getString("mobile_no"));
            order.setDeliveryStaffName(rs.getString("staff_name"));
            order.setStatus(rs.getString("status"));
            order.setReview(rs.getString("review"));
            orderList.add(order);
        }
        return orderList;
    }

    public static String placeOrder(PlaceOrderPojo placeOrder) throws SQLException {
        Connection conn = DBConnection.getConnection();
        //        (order_id, product_id, customer_id, staff_id, review, status, company_id)
        PreparedStatement ps = conn.prepareStatement("INSERT INTO orders values(?,?,?,?,?,?,?,?)");
        placeOrder.setOrderId(getNewOrderId());
        ps.setString(1, placeOrder.getOrderId());
        ps.setString(2, placeOrder.getProductId());
        ps.setString(3, placeOrder.getCustomerId());
        ps.setString(4, placeOrder.getDeliveryStaffId());
        ps.setString(5, "");
        ps.setString(6, "ORDERED");
        ps.setString(7, placeOrder.getCompanyId());
        ps.setInt(8, (100000 + new Random().nextInt(900000)));
        if (ps.executeUpdate() > 0) {
            return placeOrder.getOrderId();
        }
        return null;
    }

    public static boolean addToCart(PlaceOrderPojo placeOrder) throws SQLException {
        Connection conn = DBConnection.getConnection();
        //        (order_id, product_id, customer_id, staff_id, review, status, company_id)
        PreparedStatement ps = conn.prepareStatement("INSERT INTO orders values(?,?,?,?,?,?,?,?)");
        placeOrder.setOrderId(getNewOrderId());
        ps.setString(1, placeOrder.getOrderId());
        ps.setString(2, placeOrder.getProductId());
        ps.setString(3, placeOrder.getCustomerId());
        ps.setString(4, placeOrder.getDeliveryStaffId());
        ps.setString(5, "");
        ps.setString(6, "CART");
        ps.setString(7, placeOrder.getCompanyId());
        ps.setInt(8, 0);
        return ps.executeUpdate() > 0;
    }

    public static boolean cancelOrder(String orderId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE orders SET status = ? WHERE order_id = ?");
        ps.setString(1, "CANCELLED");
        ps.setString(2, orderId);
        return ps.executeUpdate() > 0;
    }

    public static boolean confirmOrder(String orderId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE orders SET status = ? WHERE order_id = ?");
        ps.setString(1, "DELIVERED");
        ps.setString(2, orderId);
        return ps.executeUpdate() > 0;
    }

    public static boolean deleteOrderCartByOrderId(String orderId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM orders WHERE order_id = ?");
        ps.setString(1, orderId);
        return ps.executeUpdate() > 0;
    }

    public static String getNewOrderId() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("Select max(order_id) as order_id from orders");
        rs.next();
        String orderId = rs.getString("order_id");
        if (orderId == null) {
            return "ORD101";
        }
        int number = Integer.parseInt(orderId.substring(3));
        number++;
        return "ORD" + number;
    }

    public static ArrayList<OrderPojo> getOrderedProductsByCustomerId(String customerId) throws SQLException {
        Connection conn = DBConnection.getConnection();
//        String qry = "SELECT o.order_id, p.product_name, p.product_price, s.staff_name, c.address "
//                + "FROM orders o, products p, customers c, staffs s "
//                + "WHERE o.product_id = p.product_id AND o.customer_id = c.customer_id AND "
//                + "o.staff_id = s.staff_id AND o.customer_id = ? AND o.status = 'ORDERED'";

//        String qry = "SELECT o.order_id, p.product_name, p.product_price, s.staff_name, c.address, f.email_id "
//                + "FROM orders o, products p, customers c, staffs s, companies f "
//                + "WHERE o.product_id = p.product_id "
//                + "AND o.customer_id = c.customer_id "
//                + "AND o.staff_id = s.staff_id "
//                + "AND o.company_id = f.company_id "
//                + "AND o.customer_id = ? "
//                + "AND o.status = 'ORDERED'";
        String qry = "SELECT o.order_id, p.product_name, p.product_price, s.staff_name, c.address, f.email_id "
                + "FROM orders o "
                + "JOIN products p ON o.product_id = p.product_id "
                + "JOIN customers c ON o.customer_id = c.customer_id "
                + "JOIN staffs s ON o.staff_id = s.staff_id "
                + "JOIN companies f ON o.company_id = f.company_id "
                + "WHERE o.customer_id = ? "
                + "  AND o.status = 'ORDERED' "
                + "ORDER BY o.order_id DESC";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, customerId);
        ResultSet rs = ps.executeQuery();
        ArrayList<OrderPojo> orderList = new ArrayList<>();
        OrderPojo order;
        while (rs.next()) {
            order = new OrderPojo();
            order.setOrderId(rs.getString("order_id"));
            order.setProductName(rs.getString("product_name"));
            order.setProductPrice(rs.getDouble("product_price"));
            order.setDeliveryStaffName(rs.getString("staff_name"));
            order.setCustomerAddress(rs.getString("address"));
            order.setCompanyEmailId(rs.getString("email_id"));
            orderList.add(order);
        }
        return orderList;
    }

    public static ArrayList<OrderPojo> getDeliveredProductsByCustomerId(String customerId) throws SQLException {
        Connection conn = DBConnection.getConnection();
//        String qry = "SELECT o.order_id, p.product_name, p.product_price, s.staff_name, cm.company_name, c.address, o.review "
//                + "FROM orders o, products p, customers c, staffs s, companies cm "
//                + "WHERE o.product_id = p.product_id AND o.customer_id = c.customer_id AND o.staff_id = s.staff_id "
//                + "AND cm.company_id = o.company_id AND o.customer_id = ? AND o.status = 'DELIVERED'";

        String qry = "SELECT o.order_id, p.product_name, p.product_price, s.staff_name, cm.company_name, c.address, o.review "
                + "FROM orders o "
                + "JOIN products p ON o.product_id = p.product_id "
                + "JOIN customers c ON o.customer_id = c.customer_id "
                + "JOIN staffs s ON o.staff_id = s.staff_id "
                + "JOIN companies cm ON cm.company_id = o.company_id "
                + "WHERE o.customer_id = ? "
                + "  AND o.status = 'DELIVERED' "
                + "ORDER BY o.order_id DESC";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, customerId);
        ResultSet rs = ps.executeQuery();
        ArrayList<OrderPojo> orderList = new ArrayList<>();
        OrderPojo order;
        while (rs.next()) {
            order = new OrderPojo();
            order.setOrderId(rs.getString("order_id"));
            order.setProductName(rs.getString("product_name"));
            order.setProductPrice(rs.getDouble("product_price"));
            order.setDeliveryStaffName(rs.getString("staff_name"));
            order.setCompanyName(rs.getString("company_name"));
            order.setCustomerAddress(rs.getString("address"));
            order.setReview(rs.getString("review"));
            orderList.add(order);
        }
        return orderList;
    }

    public static OrderPojo getOrderDetailsByOrderId(String orderId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry = "SELECT c.customer_name, c.address, s.staff_name, u.mobile_no, co.company_name, p.product_name, p.product_price, o.otp "
                + "FROM orders o "
                + "JOIN products p ON o.product_id = p.product_id "
                + "JOIN companies co ON o.company_id = co.company_id "
                + "JOIN customers c ON o.customer_id = c.customer_id "
                + "JOIN staffs s ON o.staff_id = s.staff_id "
                + "JOIN users u ON s.email_id = u.email_id "
                + "WHERE o.order_id = ?";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, orderId);
        ResultSet rs = ps.executeQuery();
        OrderPojo order = null;
        if (rs.next()) {
            order = new OrderPojo();
            order.setOrderId(orderId);
            order.setCustomerName(rs.getString("customer_name"));
            order.setCustomerAddress(rs.getString("address"));
            order.setDeliveryStaffName(rs.getString("staff_name"));
            order.setDeliveryStaffMobileNo(rs.getString("mobile_no"));
            order.setCompanyName(rs.getString("company_name"));
            order.setProductName(rs.getString("product_name"));
            order.setProductPrice(rs.getDouble("product_price"));
            order.setOtp(rs.getInt("otp"));

        }
        return order;
    }

    public static boolean updateOrderReview(Map<String, String> orderReviewMap) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry = "UPDATE orders SET review=? where order_id=?";
        PreparedStatement ps = conn.prepareStatement(qry);
        // Create a PreparedStatement with the SQL statement
        for (Map.Entry<String, String> orderReview : orderReviewMap.entrySet()) {
            String orderId = orderReview.getKey();
            String review = orderReview.getValue();

            // Set parameter values
            ps.setString(1, review);
            ps.setString(2, orderId);

            // Add the batch
            ps.addBatch();
        }

        // Execute the batch update
        int[] result = ps.executeBatch();

        for (int i = 0; i < result.length; i++) {
            if (result[i] != 1) {
                return false;
            }
        }
        return true;

        // Check the result for successful updates
//        int totalUpdated = 0;
//        for (int updateCount : result) {
//            if (updateCount >= 0) {
//                totalUpdated += updateCount;
//            }
//        }
//        boolean allSame = Arrays.equals(result, Arrays.copyOfRange(result, PreparedStatement.SUCCESS_NO_INFO, result.length));
//
//        System.out.println("Total records inserted: " + allSame);
//        return allSame;
    }

    public static ArrayList<OrderPojo> getOrderCartByCustomerId(String customerId) throws SQLException {
        Connection conn = DBConnection.getConnection();

//        String qry = "SELECT o.order_id, p.product_name, p.product_price, s.staff_name, c.address, f.email_id "
//                + "FROM orders o, products p, customers c, staffs s, companies f "
//                + "WHERE o.product_id = p.product_id "
//                + "AND o.customer_id = c.customer_id "
//                + "AND o.staff_id = s.staff_id "
//                + "AND o.company_id = f.company_id "
//                + "AND o.customer_id = ? "
//                + "AND o.status = 'ORDERED'";
        String qry = "SELECT o.order_id, c.customer_name, c.address, s.staff_name, u.mobile_no, co.company_name, co.email_id, p.product_name, p.product_price, o.otp "
                + "FROM orders o "
                + "INNER JOIN products p ON o.product_id = p.product_id "
                + "INNER JOIN customers c ON o.customer_id = c.customer_id "
                + "INNER JOIN staffs s ON o.staff_id = s.staff_id "
                + "INNER JOIN companies co ON o.company_id = co.company_id "
                + "INNER JOIN users u ON s.email_id = u.email_id "
                + "WHERE o.status = 'CART'"
                + "AND o.customer_id = ? ";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, customerId);
        ResultSet rs = ps.executeQuery();
        ArrayList<OrderPojo> orderList = new ArrayList<>();
        OrderPojo order;
        while (rs.next()) {
            order = new OrderPojo();
            order.setOrderId(rs.getString("order_id"));
            order.setCustomerName(rs.getString("customer_name"));
            order.setCustomerAddress(rs.getString("address"));
            order.setDeliveryStaffName(rs.getString("staff_name"));
            order.setDeliveryStaffMobileNo(rs.getString("mobile_no"));
            order.setCompanyName(rs.getString("company_name"));
            order.setCompanyEmailId(rs.getString("email_id"));
            order.setProductName(rs.getString("product_name"));
            order.setProductPrice(rs.getDouble("product_price"));
            order.setOtp(rs.getInt("otp"));
            orderList.add(order);
        }
        return orderList;
    }

    public static ProductPojo getProductDetailsByOrderId(String orderId) throws SQLException, IOException {
        Connection conn = DBConnection.getConnection();

//        String qry = "SELECT o.order_id, p.product_name, p.product_price, s.staff_name, c.address, f.email_id "
//                + "FROM orders o, products p, customers c, staffs s, companies f "
//                + "WHERE o.product_id = p.product_id "
//                + "AND o.customer_id = c.customer_id "
//                + "AND o.staff_id = s.staff_id "
//                + "AND o.company_id = f.company_id "
//                + "AND o.customer_id = ? "
//                + "AND o.status = 'ORDERED'";
        String qry = "SELECT p.product_id, p.company_id, p.product_name, p.product_price, p.product_image "
                + "FROM orders o "
                + "INNER JOIN products p ON o.product_id = p.product_id "
                + "WHERE o.order_id = ?";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, orderId);
        ResultSet rs = ps.executeQuery();

        ProductPojo product = null;
        if (rs.next()) {
            product = new ProductPojo();
            product.setCompanyId(rs.getString("company_id"));
            product.setProductId(rs.getString("product_id"));
            product.setProductName(rs.getString("product_name"));
            product.setProductPrice(rs.getDouble("product_price"));
            //convert the blob to image : 
            // Fetch the image data as InputStream
            InputStream inputStream = rs.getBinaryStream("product_image");

            // Convert InputStream to BufferedImage
            BufferedImage bufferedImage = ImageIO.read(inputStream);

            // Convert BufferedImage to Image
            Image image = bufferedImage;

            product.setProductImage(image);
        }
        return product;
    }

    public static boolean checkOutCart(List<String> orderCartIds) throws SQLException {
        Connection conn = DBConnection.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement ps = conn.prepareStatement("UPDATE orders SET status = ? WHERE order_id = ?");
        for (String cartId : orderCartIds) {

            // Set parameter values
            ps.setString(1, "ORDERED");
            ps.setString(2, cartId);

            // Add the batch
            ps.addBatch();
        }

        // Execute the batch update
        int[] result = ps.executeBatch();

    
        int [] check = new int[result.length];
        Arrays.fill(check, PreparedStatement.SUCCESS_NO_INFO);
        boolean allSame = Arrays.equals(result, check);

        System.out.println("Total records inserted: " + allSame);
        if(allSame == true){
            conn.commit();
            return allSame;
        }
        conn.rollback();
        return allSame;

    }

    public static boolean setPropertyByOrderId(String orderId, String staffId, int otp) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE orders SET staff_id = ?, otp = ? WHERE order_id = ?");
        ps.setString(1, staffId);
        ps.setInt(2, otp);
        ps.setString(3, orderId);
        return ps.executeUpdate() > 0;
    }
}
