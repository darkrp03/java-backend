package com.backend.services;

import com.backend.models.OlxItem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelService {
    public void writeOlxItemsToExcel(List<OlxItem> olxItems, String filePath) {
        Workbook workbook = new XSSFWorkbook(); // For .xlsx files
        Sheet sheet = workbook.createSheet("Olx Items");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Name");
        headerRow.createCell(1).setCellValue("Price");
        headerRow.createCell(2).setCellValue("URL");

        // Populate data rows
        int rowNum = 1;
        for (OlxItem item : olxItems) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.getName());
            row.createCell(1).setCellValue(item.getPrice());
            row.createCell(2).setCellValue(item.getUrl());
        }

        // Auto-size columns
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);

        // Write to file
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
            System.out.println("Excel file created successfully: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
