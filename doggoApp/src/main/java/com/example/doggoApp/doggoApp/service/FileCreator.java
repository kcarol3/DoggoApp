package com.example.doggoApp.doggoApp.service;

import com.itextpdf.text.DocumentException;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

public interface FileCreator {
    void createFile() throws FileNotFoundException, DocumentException;
    ByteArrayInputStream getCreatedFile() throws DocumentException;
}
