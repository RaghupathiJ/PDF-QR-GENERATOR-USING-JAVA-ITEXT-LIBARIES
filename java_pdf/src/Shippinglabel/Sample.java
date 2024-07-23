package Shippinglabel;

import java.io.IOException;
import java.util.Scanner;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;


public class Sample {
	private static BaseFont bs;
	private static BaseFont bsbold;

	public static void main(String[] args) throws DocumentException, IOException {
		Document doc = new Document(PageSize.A6);
		PdfWriter pw = null;

		String FilePath = "E:\\eclipse\\java_pdf\\Lib";
		String filename = "Dolphin.pdf";
		fontinializer();

		try {
			FilePath = FilePath + filename;
			pw = PdfWriter.getInstance((com.itextpdf.text.Document) doc, new FileOutputStream(FilePath));
			doc.open();
			PdfContentByte cb = pw.getDirectContent();
			generateLayout(doc, cb);
			generateBarcode(cb, doc, "");

			//printFile(FilePath);
			System.out.println(FilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(doc != null) {
				doc.close();
			}
			if(pw != null) {
				pw.close();
			}
		}

	}

	private static void fontinializer() throws DocumentException, IOException {

		bs = BaseFont.createFont(BaseFont.TIMES_BOLD, BaseFont.CP1250, BaseFont.EMBEDDED);
		bsbold = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1250, BaseFont.EMBEDDED);

	}
	
    private static void generateLayout(Document doc, PdfContentByte cb) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the Origin:");
        String origin = sc.next();
        System.out.println("Enter the Destination:");
        String destination = sc.next();
        System.out.println("Enter the Consignee Name:");
        String consigneename = sc.next();
        
        System.out.println("Enter the Packs:");
        int totalpacks = 0;
        if (sc.hasNextInt()) {
            totalpacks = sc.nextInt();
        } else {
            System.out.println("Please enter a valid integer for total packs.");
            sc.next(); // Consume invalid input
            return;
        }

        System.out.println("Enter the weight:");
        double totalweight = 0.0;
        if (sc.hasNextDouble()) {
            totalweight = sc.nextDouble();
        } else {
            System.out.println("Please enter a valid number for total weight.");
            sc.next(); // Consume invalid input
            return;
        }

        System.out.println("Enter the remarks (yes/no):");
        String remarks = sc.next();

        System.out.println("Thank you for your response!");

        cb.rectangle(5, 5, 288, 411);
        cb.setLineWidth(3);
        cb.stroke();

        cb.setLineWidth(1);

        cb.moveTo(5, 365);
        cb.lineTo(292, 365);

        cb.moveTo(5, 290);
        cb.lineTo(292, 290);

        cb.moveTo(5, 245);
        cb.lineTo(292, 245);

        cb.moveTo(5, 195);
        cb.lineTo(292, 195);

        cb.moveTo(5, 145);
        cb.lineTo(292, 145);

        cb.moveTo(5, 100);
        cb.lineTo(292, 100);

        cb.stroke();

        content2(cb, 40, 390, "BLUEWINGS CARGO AND LOGISTICS");
        content0(cb, 70, 375, "Contact No : 7299726766 / 9500434896");

        content1(cb, 80, 350, "AWB Number testrf8452");

        content1(cb, 25, 275, "Origin");
        content0(cb, 25, 260, origin);

        content1(cb, 200, 275, "Destination");
        content0(cb, 200, 260, destination);

        content1(cb, 25, 225, "Consignor Name");
        content0(cb, 25, 208, "BLUEWINGS CARGO AND LOGISTICS");

        content1(cb, 25, 178, "Consignee Name");
        content0(cb, 25, 162, consigneename);

        content1(cb, 25, 130, "Total packs: " + totalpacks);

        content0(cb, 200, 130, "Total Wgt (kg): " + totalweight);

        content1(cb, 25, 80, "REMARKS");

        if (remarks.equalsIgnoreCase("yes")) {
            content0(cb, 25, 50, "Good, Thanks for visiting");
        } else {
            content0(cb, 25, 50, "Bad, Sorry for your Trouble");
        }
    }

		   public static void content0(PdfContentByte cb, float x, float y, String text) {
			cb.beginText();
			cb.setFontAndSize(bs, 10);
			cb.setTextMatrix(x, y);
			cb.showText(text.trim());
			cb.endText();
		}	
		   public static void content1(PdfContentByte cb, float x, float y, String text) {
			cb.beginText();
			cb.setFontAndSize(bsbold, 12);
			cb.setTextMatrix(x, y);
			cb.showText(text.trim());
			cb.endText();
		}
		    public static void content2(PdfContentByte cb, float x, float y, String text) {
			cb.beginText();
			cb.setFontAndSize(bsbold, 13);
			cb.setTextMatrix(x, y);
			cb.showText(text.trim());
			cb.endText();
		}
	    	
			public static void generateBarcode(PdfContentByte cb,Document doc,String barcodeValue) throws DocumentException {
				barcodeValue ="testrf8452";
				Barcode128 barcode128 = new Barcode128();
				barcode128.setCode(barcodeValue);
				barcode128.setFont(bsbold);
				barcode128.setSize(10);
				barcode128.setBaseline(10);
				
				Image code128 = barcode128.createImageWithBarcode(cb, BaseColor.BLACK, BaseColor.BLACK);
				code128.setAbsolutePosition(92, 300);
				
				doc.add(code128);
								       				                   
			}
	    }