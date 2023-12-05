package com.example.doggoApp.doggoApp.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PDFCreator implements FileCreator {
    private Document document = new Document();
    private ByteArrayOutputStream out = new ByteArrayOutputStream();
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);

    public void addTitlePage(String title) throws DocumentException {
        Paragraph preface = new Paragraph();
        Paragraph paragraphTitle = new Paragraph(title, catFont);
        paragraphTitle.setAlignment(Element.ALIGN_CENTER);
        preface.add(paragraphTitle);
        addEmptyLine(1);
        document.add(preface);
    }

    public void addTextLine(String text) throws DocumentException {
        Paragraph preface = new Paragraph();
        preface.add(new Paragraph(text, smallBold));
        document.add(preface);
    }

    public void addHeader(String header) throws DocumentException {
        Paragraph preface = new Paragraph();
        preface.add(new Paragraph(header, subFont));
        document.add(preface);
    }

    public void addImage(byte[] imageBytes) throws DocumentException, IOException {
        Image image = Image.getInstance(imageBytes);
        image.scaleToFit(140, 140);
        document.add(image);
    }

    public void addEmptyLine(int number) throws DocumentException {
        Paragraph preface = new Paragraph();
        for (int i = 0; i < number; i++) {
            preface.add(new Paragraph(" "));
        }
        document.add(preface);
    }

    @Override
    public void createFile() throws FileNotFoundException, DocumentException {
        PdfWriter.getInstance(document, out);
        document.open();
    }

    @Override
    public ByteArrayInputStream getCreatedFile() throws DocumentException {
        return new ByteArrayInputStream(out.toByteArray());
    }

    public void closeFile() {
        document.close();
    }
}
