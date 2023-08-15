package org.foodie.dao;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import org.foodie.dbuitl.DBConnection;
import org.foodie.pojo.ProductPojo;

/**
 *
 * @author AFROZ
 */
public class ProductDao {
    
    public static boolean addProduct(ProductPojo product) throws SQLException, IOException {
        //converting the image to insert into the database;
        BufferedImage bufferedImage
                = new BufferedImage(product.getProductImage().getWidth(null), product.getProductImage().getHeight(null),
                        BufferedImage.TYPE_INT_RGB);
        bufferedImage.getGraphics().drawImage(product.getProductImage(), 0, 0, null);

        // Convert BufferedImage to byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, product.getProductImageType(), baos);
        byte[] imageData = baos.toByteArray();

        // sending the data to the database;
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Insert into products values(?,?,?,?,?)");
        ps.setString(1, getNewProductId());
        ps.setString(2, product.getCompanyId());
        ps.setString(3, product.getProductName());
        ps.setDouble(4, product.getProductPrice());
        ps.setBinaryStream(5, new ByteArrayInputStream(imageData), imageData.length);
        int x = ps.executeUpdate();
        return x > 0;
    }
    
    public static HashMap<String, ProductPojo> getProductDetails(String companyId) throws SQLException, IOException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select * from products where company_id=?");
        ps.setString(1, companyId);
        ResultSet rs = ps.executeQuery();
        HashMap<String, ProductPojo> productList = new HashMap<>();
        while (rs.next()) {
            
            ProductPojo product = new ProductPojo();
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
            
            productList.put(product.getProductName(), product);
        }
        return productList;
    }
    
    public static String getNewProductId() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("Select count(*) from products");
        rs.next();
        return "PD" + (100 + rs.getInt(1));
    }
    
    public static ArrayList<ProductPojo> getAllProducts() throws SQLException, IOException {
        Connection conn = DBConnection.getConnection();
        Statement ps = conn.createStatement();
        ResultSet rs = ps.executeQuery("SELECT company_id, product_id, product_name, product_price, product_image FROM products");
        ArrayList<ProductPojo> productList = new ArrayList<>();
        ProductPojo product;
        while (rs.next()) {
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
            productList.add(product);
        }
        return productList;
    }
    
    public static ArrayList<ProductPojo> getAllProductsFilterBy(String filter) throws SQLException, IOException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT company_id, product_id, product_name, product_price, product_image FROM products where company_id=?");
        ps.setString(1, filter);
        ResultSet rs = ps.executeQuery();
        ArrayList<ProductPojo> productList = new ArrayList<>();
        ProductPojo product;
        while (rs.next()) {
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
            productList.add(product);
        }
        return productList;
    }
    
}
