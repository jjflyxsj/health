package com.itcast.test;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo2 {
    @Test
    public void test1() throws Exception {
        XSSFWorkbook excel=new XSSFWorkbook(new File("E:\\uploads\\poop.xlsx"));
        XSSFSheet sheetAt = excel.getSheetAt(0);
        for (Row row : sheetAt) {
            for (Cell cell : row) {
                System.out.println(cell.getStringCellValue());
            }
        }
        excel.close();
    }
    @Test
    public void test2() throws  Exception{
        XSSFWorkbook excel=new XSSFWorkbook();
        XSSFSheet sheet = excel.createSheet("创智");
        XSSFRow row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue("姓名");
        row1.createCell(1).setCellValue("地址");
        row1.createCell(2).setCellValue("爱好");
        XSSFRow row2 = sheet.createRow(1);
        row2.createCell(0).setCellValue("张三丰");
        row2.createCell(1).setCellValue("道家");
        row2.createCell(2).setCellValue("太极");
        XSSFRow row3 = sheet.createRow(2);
        row3.createCell(0).setCellValue("色色色");
        row3.createCell(1).setCellValue("52av");
        row3.createCell(2).setCellValue("霸王硬上弓");

        FileOutputStream outputStream = new FileOutputStream(new File("E:\\uploads\\hello.xlsx"));
        excel.write(outputStream);
    }
}
