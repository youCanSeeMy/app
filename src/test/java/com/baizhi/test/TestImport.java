package com.baizhi.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;

public class TestImport {
    @Test
    public void test1(){

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("凌小猪");


        Row row = sheet.createRow(0);

        Cell cell = row.createCell(0);

        CellStyle cellStyle = workbook.createCellStyle();

        cellStyle.setAlignment(HorizontalAlignment.CENTER);


        cell.setCellValue("凌小猪");

    }
}
