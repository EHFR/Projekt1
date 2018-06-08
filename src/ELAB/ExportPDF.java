package ELAB;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

public class ExportPDF {
    private static String FILE = "c:/temp/Rechnung.pdf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    public static void main(String[] args) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            addTitlePage(document);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void addMetaData(Document document) {
        document.addTitle("Rechnungen");
        document.addSubject("ELAB");
        document.addKeywords("");
        document.addAuthor("Max Mustermann");
        document.addCreator("Max Mustermann");
    }
    
    private static void addTitlePage(Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();

        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Rechnungsausdruck", catFont));

        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Generiert von: " + "\n" 
        						  + System.getProperty("user.name") + ", " + new Date(), smallBold));
        
        addEmptyLine(preface, 4);
        preface.add(new Paragraph("ID: "  , smallBold));
        
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Datum: ", smallBold));
        
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Rechnungsname: ", smallBold));
        
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Auftraggeber: ", smallBold));
        
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Ansprechpartner: ", smallBold));
        
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Topf-ID: ", smallBold));
        
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Rechnungsbetrag: ", smallBold));
        
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Bezahlart: ", smallBold));
        
        document.add(preface);
    }
    
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }        
    }
    

}