package org.foodie.utility;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.foodie.pojo.OrderPojo;
import org.foodie.pojo.StaffPojo;

/**
 *
 * @author AFROZ
 */
public class Gmail {

    private Session newSession;
    private MimeMessage mimeMessage;

    public Gmail() {
        setupServerProperties();
    }

    public void setupServerProperties()
    {
        Properties pro = System.getProperties();
        pro.put("mail.smtp.port","587");
        pro.put("mail.smtp.auth","true");
        pro.put("mail.smtp.starttls.enable","true");
        newSession=Session.getDefaultInstance(pro,null);
        
    }
    
    public void sendEmailFrom(String fromUser,String fromUserPwd) throws MessagingException
    {
        String emailHost="smtp.gmail.com";
        Transport transport=newSession.getTransport("smtp");
        transport.connect(emailHost,fromUser,fromUserPwd);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        
        System.out.println("Email sent successfully");
        
    }

    public void sendEmailTo(String to) throws AddressException, MessagingException {

        String emailSubject = "Testing";
        String emailBody = "Hello this is demo message to test Java's mail api";

        mimeMessage = new MimeMessage(newSession);

        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

        mimeMessage.setSubject(emailSubject);

        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setText(emailBody);

        MimeMultipart multiPart = new MimeMultipart();
        multiPart.addBodyPart(bodyPart);

        mimeMessage.setContent(multiPart);
    }
    public void sendWelcomeEmailTo(String to, String username) throws AddressException, MessagingException {

        String emailSubject = "Welcome to Foodie App - Your Ultimate Food Ordering Companion!";
        String emailBody = "Dear "+ username +",\n"
                + "\n"
                + "Welcome to Foodie App! We're excited to have you as a new member of our food-loving community. "
                + "Whether you're craving a delicious meal from your favorite restaurant or exploring new culinary delights, "
                + "Foodie App is here to make your food ordering experience delightful and convenient."
                + "\n" + "\n"
                + "Happy ordering!\n"
                + "\n"
                + "Best Regards,\n"
                + "The Foodie App Team";

        mimeMessage = new MimeMessage(newSession);

        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

        mimeMessage.setSubject(emailSubject);

        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setText(emailBody);

        MimeMultipart multiPart = new MimeMultipart();
        multiPart.addBodyPart(bodyPart);

        mimeMessage.setContent(multiPart);
    }

    public void sendCancelOrderOTPTo(OrderPojo order, int otp, String to) throws AddressException, MessagingException {

        String emailSubject = "Order Cancellation Confirmation and OTP";
        String emailBody = "Dear " + order.getCustomerName() + ",\n"
                + "\n"
                + "We hope this email finds you well. We regret to inform you that we have received your request to cancel your order, and we are sorry for any inconvenience this may cause. We are here to assist you with the cancellation process and ensure a smooth resolution.\n"
                + "\n"
                + "Order Details:\n"
                + "Order Number: " + order.getOrderId() + "\n"
                + "Product(s) to be Cancelled: " + order.getProductName() + "\n"
                + "Order Total: " + order.getProductPrice() + "\n"
                + "\n"
                + "To complete the cancellation process, Please find below the One-Time Password (OTP) associated with your cancellation request:\n"
                + "\n"
                + "OTP: " + otp + " \n"
                + "Thank you for your understanding and cooperation.\n"
                + "\n"
                + "Sincerely,\n"
                + "\n"
                + order.getCompanyName();

        mimeMessage = new MimeMessage(newSession);

        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

        mimeMessage.setSubject(emailSubject);

        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setText(emailBody);

        MimeMultipart multiPart = new MimeMultipart();
        multiPart.addBodyPart(bodyPart);

        mimeMessage.setContent(multiPart);
    }

    public void sendStaffDetailsTo(StaffPojo staff, String companyName, String ownerName) throws AddressException, MessagingException {

        String emailSubject = "Staff Details";

        String emailBody = "Dear " + staff.getStaffName() + ",\n"
                + "\n"
                + "I hope this email finds you well. I am writing to provide you with the necessary staff details as requested. Please find the information below:\n"
                + "\n"
                + "Staff ID: " + staff.getStaffId() + "\n"
                + "Company Name: " + companyName + "\n"
                + "Email ID: " + staff.getEmailId() + "\n"
                + "Staff password: " + PasswordEncryption.getDecryptedPassword(staff.getPassword()) + "\n"
                + "\n"
                + "These details are crucial for internal record-keeping and ensuring efficient communication within the organization.\n"
                + "Best regards,\n"
                + "\n"
                + ownerName;
        mimeMessage = new MimeMessage(newSession);

        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(staff.getEmailId()));

        mimeMessage.setSubject(emailSubject);

        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setText(emailBody);

        MimeMultipart multiPart = new MimeMultipart();
        multiPart.addBodyPart(bodyPart);

        mimeMessage.setContent(multiPart);
    }


    public void sendOrderReceiptTo(String to, String companyName) throws AddressException, MessagingException, URISyntaxException {

        String emailSubject = "Order Details and Receipt";
        String emailBody = "Dear Customer,\n\nPlease find attached the order details and receipt.\n\nThank you for your order!";

        mimeMessage = new MimeMessage(newSession);

        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

        mimeMessage.setSubject(emailSubject);

        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setText(emailBody);

        File file = new File("src/resources/FoodOrderReceipt" + companyName + ".pdf");
        boolean status = true;

        do {
            if (file.exists()) {
                status = false;
            }
        } while (status);

        DataSource source = new FileDataSource(file);
        bodyPart.setDataHandler(new DataHandler(source));
        bodyPart.setFileName("FoodOrderReceipt.pdf");

        MimeMultipart multiPart = new MimeMultipart();
        multiPart.addBodyPart(bodyPart);

        mimeMessage.setContent(multiPart);
    }
}
