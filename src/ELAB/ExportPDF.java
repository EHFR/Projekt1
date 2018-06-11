package ELAB;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExportPDF {
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    private String file;


    public ExportPDF(Rechnung rechnung) {
        Date date = new Date();
        String dateString = new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss").format(date);
        file = System.getProperty("user.home") + "/Desktop/ElabRechnungen/Rechnung" + dateString + "Uhr_" + rechnung.getName() + ".pdf";
        boolean bool = new File(System.getProperty("user.home") + "/Desktop/ElabRechnungen").mkdirs();

        this.start(rechnung);
    }

    public void start(Rechnung rechnung) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(file));
        } catch (FileNotFoundException | DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        document.open();
        addMetaData(document, rechnung);
        try {
            addTitlePage(document, rechnung);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        document.close();

    }

    public void addMetaData(Document document, Rechnung rechnung) {
        document.addTitle("Rechnung");
        document.addSubject("ELAB");
        document.addKeywords("");
        document.addAuthor(rechnung.getAnsprechpartner().getName());
        document.addCreator("ElabVerwaltungssystem");
    }

    public void addTitlePage(Document document, Rechnung rechnung)
            throws DocumentException {
        Paragraph preface = new Paragraph();

        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Rechnungsausdruck", catFont));

        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Generiert von: " + "\n"
                + System.getProperty("user.name") + ", " + new Date(), smallBold));

        addEmptyLine(preface, 4);
        preface.add(new Paragraph("ID: " + rechnung.getId(), smallBold));

        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Rechnungsdatum: " + rechnung.getZeitstempelString(), smallBold));

        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Rechnungsname: " + rechnung.getName(), smallBold));

        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Auftraggeber: " + rechnung.getAuftraggeber().getName(), smallBold));

        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Ansprechpartner: " + rechnung.getAnsprechpartner().getName(), smallBold));

        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Topf-ID: " + rechnung.getTopf().getId(), smallBold));

        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Rechnungsbetrag: " + rechnung.getBetrag() + " €", smallBold));

        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Bezahlart: " + rechnung.getBezahlart(), smallBold));

        document.add(preface);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

}