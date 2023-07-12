package com.example.test.office;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.junit.jupiter.api.Test;
import org.springframework.http.ContentDisposition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author gaofan
 * @version 1.0
 * @date 2023/4/13 15:10
 **/
@Slf4j
public class WordTest {

    private String inputUrl = "E:\\work\\share-file\\test\\src\\main\\resources\\001.docx";
    private String outputUrl = "E:\\work\\share-file\\test\\src\\main\\resources\\004.docx";


    @Test
    public void testWord3() {
        boolean b = true;
        int i = 2;
        float f = 1.0F;
        Long l = 100000000333L;
        String s = "str";
        String format = String.format("b=%s,i=%s,f=%s,l=%s,s=%s", b, i, f, l, s);
        System.out.println(format);
        String format2 = String.format("b=%2$s,i=%1$s,f=%3$s,l=%4$s,s=%5$s", b, i, f, l, s);
        System.out.println(format2);
    }

    @Test
    public void testWord2() throws IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream(inputUrl);
            XWPFDocument document = new XWPFDocument(fileInputStream);
            //example.write(new FileOutputStream(outputUrl));
            //try (XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(outputUrl))) {
            XWPFTableCell xwpfTableCell = document.getTables().get(0).getRow(1).getTableCells().get(0);
            XWPFParagraph xwpfParagraph = xwpfTableCell.getParagraphs().get(1);
            log.info("text: {}", xwpfParagraph.getText());
            xwpfParagraph.removeRun(0);
            xwpfParagraph.createRun().setText("replace first");
            xwpfParagraph.createRun().setText("replace second222");
            document.write(new FileOutputStream(outputUrl));
            fileInputStream.close();
            document.close();
            //} catch (IOException ignored) {
            //
            //}
        } catch (IOException ignored) {

        }
        //XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(inputUrl));
        //XWPFDocument document2 = new XWPFDocument(POIXMLDocument.openPackage(inputUrl));
        //XWPFDocument document3 = new XWPFDocument(POIXMLDocument.openPackage(inputUrl));
        //XWPFDocument document4 = new XWPFDocument(POIXMLDocument.openPackage(inputUrl));
        //for (int i = 0; i < 100; i++) {
        //    XWPFDocument documenti = new XWPFDocument(POIXMLDocument.openPackage(inputUrl));
        //}
        //document.close();
        //System.out.println(1111);
    }

    @Test
    public void testWord() {
        try {
            //解析docx模板并获取document对象
            XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(inputUrl));
            List<IBodyElement> bodyElements = document.getBodyElements();
            for (int i = 0; i < bodyElements.size(); i++) {
                IBodyElement bodyElement = bodyElements.get(i);
                log.info(document.getBodyElements().size() + "");
                if (bodyElement instanceof XWPFTable table) {
                    int posOfTable = document.getPosOfTable(table);
                    log.info("位置：{}", posOfTable);
                    document.removeBodyElement(posOfTable);
                }
                log.info(document.getBodyElements().size() + "");
            }
            //获取整个文本对象
            List<XWPFParagraph> allParagraph = document.getParagraphs();

            //获取XWPFRun对象输出整个文本内容
            StringBuffer tempText = new StringBuffer();
            for (XWPFParagraph xwpfParagraph : allParagraph) {
                // System.out.println(xwpfParagraph.getDocument().getDocument().getBody().toString());
                List<XWPFRun> runList = xwpfParagraph.getRuns();
                for (XWPFRun xwpfRun : runList) {
                    tempText.append(xwpfRun.toString());
                }
            }
            System.out.println(tempText.toString());
            document.write(new FileOutputStream(outputUrl));
            document.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //WordUtil.readWord();
    }


    /**
     * 根据模板生成新word文档
     * 判断表格是需要替换还是需要插入，判断逻辑有$为替换，表格无$为插入
     *
     * @param inputUrl  模板存放地址
     * @param textMap   需要替换的信息集合
     * @param tableList 需要插入的表格信息集合
     * @return 成功返回true, 失败返回false
     */
    public static boolean changWord(String inputUrl, String outputUrl,
                                    Map<String, String> textMap, List<String[]> tableList) {

        //模板转换默认成功
        boolean changeFlag = true;
        try {
            //获取docx解析对象
            XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(inputUrl));
            //解析替换文本段落对象
            WordTest.changeText(document, textMap);
            //解析替换表格对象
            WordTest.changeTable(document, textMap, tableList);

            //生成新的word
            File file = new File(outputUrl);
            FileOutputStream stream = new FileOutputStream(file);
            document.write(stream);
            stream.close();

        } catch (IOException e) {
            e.printStackTrace();
            changeFlag = false;
        }

        return changeFlag;

    }

    /**
     * 替换段落文本
     *
     * @param document docx解析对象
     * @param textMap  需要替换的信息集合
     */
    public static void changeText(XWPFDocument document, Map<String, String> textMap) {
        //获取段落集合
        List<XWPFParagraph> paragraphs = document.getParagraphs();

        for (XWPFParagraph paragraph : paragraphs) {
            //判断此段落时候需要进行替换
            String text = paragraph.getText();
            if (checkText(text)) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    //替换模板原来位置
                    run.setText(changeValue(run.toString(), textMap), 0);
                }
            }
        }

    }

    /**
     * 替换表格对象方法
     *
     * @param document  docx解析对象
     * @param textMap   需要替换的信息集合
     * @param tableList 需要插入的表格信息集合
     */
    public static void changeTable(XWPFDocument document, Map<String, String> textMap,
                                   List<String[]> tableList) {
        //获取表格对象集合
        List<XWPFTable> tables = document.getTables();
        for (int i = 0; i < tables.size(); i++) {
            //只处理行数大于等于2的表格，且不循环表头
            XWPFTable table = tables.get(i);
            if (table.getRows().size() > 1) {
                //判断表格是需要替换还是需要插入，判断逻辑有$为替换，表格无$为插入
                if (checkText(table.getText())) {
                    List<XWPFTableRow> rows = table.getRows();
                    //遍历表格,并替换模板
                    eachTable(rows, textMap);
                } else {
//                  System.out.println("插入"+table.getText());
                    insertTable(table, tableList);
                }
            }
        }
    }


    /**
     * 遍历表格
     *
     * @param rows    表格行对象
     * @param textMap 需要替换的信息集合
     */
    public static void eachTable(List<XWPFTableRow> rows, Map<String, String> textMap) {
        for (XWPFTableRow row : rows) {
            List<XWPFTableCell> cells = row.getTableCells();
            for (XWPFTableCell cell : cells) {
                //判断单元格是否需要替换
                if (checkText(cell.getText())) {
                    List<XWPFParagraph> paragraphs = cell.getParagraphs();
                    for (XWPFParagraph paragraph : paragraphs) {
                        List<XWPFRun> runs = paragraph.getRuns();
                        for (XWPFRun run : runs) {
                            run.setText(changeValue(run.toString(), textMap), 0);
                        }
                    }
                }
            }
        }
    }

    /**
     * 为表格插入数据，行数不够添加新行
     *
     * @param table     需要插入数据的表格
     * @param tableList 插入数据集合
     */
    public static void insertTable(XWPFTable table, List<String[]> tableList) {
        //创建行,根据需要插入的数据添加新行，不处理表头
        for (int i = 1; i < tableList.size(); i++) {
            XWPFTableRow row = table.createRow();
        }
        //遍历表格插入数据
        List<XWPFTableRow> rows = table.getRows();
        for (int i = 1; i < rows.size(); i++) {
            XWPFTableRow newRow = table.getRow(i);
            List<XWPFTableCell> cells = newRow.getTableCells();
            for (int j = 0; j < cells.size(); j++) {
                XWPFTableCell cell = cells.get(j);
                cell.setText(tableList.get(i - 1)[j]);
            }
        }

    }


    /**
     * 判断文本中时候包含$
     *
     * @param text 文本
     * @return 包含返回true, 不包含返回false
     */
    public static boolean checkText(String text) {
        boolean check = false;
        if (text.indexOf("$") != -1) {
            check = true;
        }
        return check;

    }

    /**
     * 匹配传入信息集合与模板
     *
     * @param value   模板需要替换的区域
     * @param textMap 传入信息集合
     * @return 模板需要替换区域信息集合对应值
     */
    public static String changeValue(String value, Map<String, String> textMap) {
        Set<Map.Entry<String, String>> textSets = textMap.entrySet();
        for (Map.Entry<String, String> textSet : textSets) {
            //匹配模板与替换值 格式${key}
            String key = "${" + textSet.getKey() + "}";
            if (value.indexOf(key) != -1) {
                value = textSet.getValue();
            }
        }
        //模板未匹配到区域替换为空
        if (checkText(value)) {
            value = "";
        }
        return value;
    }


    public static void main(String[] args) {
        //模板文件地址
        String inputUrl = "E:\\work\\share-file\\test\\src\\main\\resources\\002.docx";
        //新生产的模板文件
        String outputUrl = "E:\\work\\share-file\\test\\src\\main\\resources\\003.docx";

        Map<String, String> testMap = new HashMap<String, String>();
        testMap.put("name", "小明");
        testMap.put("sex", "男");
        testMap.put("address", "软件园");
        testMap.put("phone", "88888888");

        List<String[]> testList = new ArrayList<String[]>();
        testList.add(new String[]{"1", "1AA", "1BB", "1CC"});
        testList.add(new String[]{"2", "2AA", "2BB", "2CC"});
        testList.add(new String[]{"3", "3AA", "3BB", "3CC"});
        testList.add(new String[]{"4", "4AA", "4BB", "4CC"});

        WordTest.changWord(inputUrl, outputUrl, testMap, testList);
    }
}
