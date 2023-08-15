package org.foodie.utility;

import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author AFROZ
 */
public class Recept {

    private static Font headerFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
    private static Font subHeaderFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
    private static Font contentFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

    public static void main(String[] args) throws DocumentException, FileNotFoundException, IOException {
        // Data for the receipt
        String orderId = "#123456789";
        Date orderDate = new Date();
        String customerName = "John Smith";
        String customerAddress = "123 Main St, Anytown, USA";
        String staffName = "Jane Doe";
        String staffNumber = "989645637";
        String companyName = "Foodie Inc.";
        String[] items = {"Pizza Margherita", "Caesar Salad", "Coke"};
        double[] itemPrices = {10.99, 6.99, 1.99};
        double subtotal = 0.0;
        for (double price : itemPrices) {
            subtotal += price;
        }
        double taxRate = 0.075;
        double taxAmount = subtotal * taxRate;
        double total = 100+taxAmount;
        String otp = "235533";

        // Format numbers to display as currency
        DecimalFormat currencyFormatter = new DecimalFormat("$0.00");

        // Format date to display as yyyy-MM-dd
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        // Create a new PDF document
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("FoodOrderReceipt.pdf"));
        document.open();

        Image img = Image.getInstance("E:/HSEKAR_CODES/FoodieApp/Foodie.png");
        img.scaleAbsolute(100, 80);
        img.setAlignment(Element.ALIGN_CENTER);
        document.add(img);

        // Create and add title paragraph to document
        Paragraph titleParagraph = new Paragraph("FOOD ORDER RECEIPT", headerFont);
        titleParagraph.setAlignment(Element.ALIGN_CENTER);
        document.add(titleParagraph);

        // Add spacing
        document.add(new Chunk("\n"));

        // Add order information to document
        PdfPTable orderTable = new PdfPTable(2);
        orderTable.setWidthPercentage(75);
        orderTable.addCell(createCell("ORDER DETAILS :-", Element.ALIGN_LEFT, headerFont));
        orderTable.addCell(new PdfPCell(new Phrase("\n\n"))).setBorder(0);
        orderTable.addCell(createCell("Order ID :", Element.ALIGN_LEFT, subHeaderFont));
        orderTable.addCell(createCell(orderId, Element.ALIGN_RIGHT, contentFont));
        orderTable.addCell(createCell("Date :", Element.ALIGN_LEFT, subHeaderFont));
        orderTable.addCell(createCell(dateFormatter.format(orderDate), Element.ALIGN_RIGHT, contentFont));
        orderTable.addCell(createCell("Customer Name :", Element.ALIGN_LEFT, subHeaderFont));
        orderTable.addCell(createCell(customerName, Element.ALIGN_RIGHT, contentFont));
        orderTable.addCell(createCell("Customer Address :", Element.ALIGN_LEFT, subHeaderFont));
        orderTable.addCell(createCell(customerAddress, Element.ALIGN_RIGHT, contentFont));
        orderTable.addCell(createCell("Staff Name :", Element.ALIGN_LEFT, subHeaderFont));
        orderTable.addCell(createCell(staffName, Element.ALIGN_RIGHT, contentFont));
        orderTable.addCell(createCell("Staff Number :", Element.ALIGN_LEFT, subHeaderFont));
        orderTable.addCell(createCell(staffNumber, Element.ALIGN_RIGHT, contentFont));
        orderTable.addCell(createCell("Company Name :", Element.ALIGN_LEFT, subHeaderFont));
        orderTable.addCell(createCell(companyName, Element.ALIGN_RIGHT, contentFont));
        document.add(orderTable);

        // Add spacing
        document.add(new Chunk("\n"));

        // Add item information to document
        PdfPTable itemTable = new PdfPTable(2);
        itemTable.setWidthPercentage(75);
        itemTable.addCell(createCell("PAYMENT DETAILS :-", Element.ALIGN_LEFT, headerFont));
        itemTable.addCell(new PdfPCell(new Phrase("\n\n"))).setBorder(0);
        
        itemTable.addCell(createCell("ITEM NAME", Element.ALIGN_LEFT, subHeaderFont));
        itemTable.addCell(createCell("ITEM PRICE", Element.ALIGN_RIGHT, subHeaderFont));
        for (int i = 0; i < items.length; i++) {
            itemTable.addCell(createCell(items[i], Element.ALIGN_LEFT, contentFont));
            itemTable.addCell(createCell(currencyFormatter.format(itemPrices[i]), Element.ALIGN_RIGHT, contentFont));
        }
        itemTable.addCell(new PdfPCell(new Phrase("\n"))).setBorder(0);
        itemTable.addCell(new PdfPCell(new Phrase("\n"))).setBorder(0);
        itemTable.addCell(createCell("TOTAL PRICE ", Element.ALIGN_LEFT, subHeaderFont));
        itemTable.addCell(createCell(String.valueOf(total), Element.ALIGN_RIGHT, subHeaderFont));
        itemTable.addCell(new PdfPCell(new Phrase("\n\n"))).setBorder(0);
        itemTable.addCell(new PdfPCell(new Phrase("\n\n"))).setBorder(0);
        itemTable.addCell(createCell("Share this otp to complete the order : ", Element.ALIGN_LEFT, contentFont));
        itemTable.addCell(createCell(otp, Element.ALIGN_LEFT, subHeaderFont));
        
        document.add(itemTable);
        document.close();

    }

    private static PdfPCell createCell(String content, int horizontalAlignment, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setHorizontalAlignment(horizontalAlignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

}
