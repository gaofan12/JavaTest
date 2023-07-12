package com.example.test.office;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gaofan
 * @version 1.0
 * @date 2023/4/18 20:16
 **/
@Slf4j
public class ExcelTest {
    private String inputUrl = "E:\\work\\share-file\\test\\src\\main\\resources\\005.xlsx";

    @Test
    public void testExcel01() throws IOException {
       // excelDataTransf(FileUtil.readBytes(inputUrl));
        Pair<String, Integer> objects = new Pair<>(null, 1);
        System.out.println(objects.getValue0());
        System.out.println(objects.getValue1());
    }


    public static List<Object> excelDataTransf(byte[] buffer) throws IOException {
        List<Object> result = new ArrayList<>();
        try (ByteArrayInputStream fileStream = new ByteArrayInputStream(buffer)) {
            try (Workbook excelWorkbook = WorkbookFactory.create(fileStream)) {
                Map<Integer, List<CellRangeAddress>> dictionary = new HashMap<>();
                for (int i = 0; i < excelWorkbook.getNumberOfSheets(); i++) {
                    Sheet sheet = excelWorkbook.getSheetAt(i);
                    //获取所有的合并单元
                    List<CellRangeAddress> mergedRegions = sheet.getMergedRegions();
                    if (mergedRegions == null) {
                        continue;
                    }
                    List<CellRangeAddress> cellRangeAddressList = new ArrayList<>();
                    for (CellRangeAddress cellRange : mergedRegions) {
                        //使用 foreach 循环遍历当前表格中的所有合并单元格，并将符合条件的合并单元格添加到 "list" 列表中，该条件为：左侧列数小于右侧列数，且该合并单元格位于第一行。
                        //如果当前表格中存在表头的合并单元格，则将该表格的编号及其合并单元格列表添加到 "dictionary" 字典中。
                        if (cellRange.getFirstColumn() < cellRange.getLastColumn() && cellRange.getFirstRow() == 0 && cellRange.getLastRow() == 0) {
                            cellRangeAddressList.add(cellRange);
                        }
                    }
                    if (cellRangeAddressList.size() > 0)
                    {
                        dictionary.put(i, cellRangeAddressList);
                    }
                }
                dictionary.forEach((k, v) -> {
                    log.info("sheetId:{}, sheetName: {}" ,k, excelWorkbook.getSheetName(k));
                    v.forEach(cellRangeAddress -> {
                        log.info("firstRow:{}, lastRow:{}, firstColumn:{}, lastColumn:{}" ,cellRangeAddress.getFirstRow(), cellRangeAddress.getLastRow(), cellRangeAddress.getFirstColumn(), cellRangeAddress.getLastColumn());
                    });
                });
                log.info(dictionary.toString());
                log.info("-------第一次读取--------");
                readAllDate(excelWorkbook);
                log.info("-------第二次读取--------");
                readAllDate(excelWorkbook);
                //result = DataTableTransf(excelWorkbook, dictionary, id);
            }
        }
        return result;
    }

    public static void readAllDate(Workbook workbook) {
        DataFormatter dataFormatter = new DataFormatter();
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            String sheetName = sheet.getSheetName();
            List<List<String>> sheetData = new ArrayList<>();
            log.info("Sheet ---  sheetId:{}, sheetName: {}, sheetSize;{}" ,i, sheetName, sheet.getPhysicalNumberOfRows());
            for (Row row : sheet) {
                log.info("Row ---  rowId:{}" ,row.getRowNum());
                List<String> rowData = new ArrayList<>();
                for (Cell cell : row) {
                    String cellValue = dataFormatter.formatCellValue(cell);
                    rowData.add(cellValue);
                    log.info("Cell ---  cellId:{}, cellValue:{}" ,cell.getRowIndex(), cellValue);
                }
                sheetData.add(rowData);
            }
        }
    }
}
