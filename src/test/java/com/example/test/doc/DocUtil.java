package com.example.test.doc;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;

import java.util.Iterator;
import java.util.List;

/**
 * @author gaofan
 * @version 1.0
 * @date 2023/6/16 17:05
 **/
@Slf4j
public class DocUtil {

    /**
     * des:表末尾添加行(表，要复制样式的行，添加行数，插入的行下标索引)
     * @param table
     * @param sourceRow
     * @param num
     */
    public static void copyRows2(XWPFTable table, XWPFTableRow sourceRow, int num, int insertRowIndex) {
        //循环添加行和和单元格
        for (int k = 1; k <= num; k++) {
            //添加新行
            XWPFTableRow newRow = table.insertNewTableRow(insertRowIndex);
            newRow.getCtRow().setTrPr(sourceRow.getCtRow().getTrPr());

            // 复制单元格内容和格式
            for (int i = 0; i < sourceRow.getTableCells().size(); i++) {
                XWPFTableCell sourceCell = sourceRow.getCell(i);
                XWPFTableCell newCell = newRow.createCell();

                // 复制单元格格式
                newCell.getCTTc().setTcPr(sourceCell.getCTTc().getTcPr());
                if (newCell.getParagraphs() != null) {
                    // 删除单元格中的所有段落
                    while (newCell.getParagraphs().size() > 0) {
                        newCell.removeParagraph(0);
                    }
                }
                // 复制文本内容的格式
                for (XWPFParagraph sourceParagraph : sourceCell.getParagraphs()) {
                    XWPFParagraph newParagraph = newCell.addParagraph();
                    newParagraph.getCTP().setPPr(sourceParagraph.getCTP().getPPr());
                    for (XWPFRun sourceRun : sourceParagraph.getRuns()) {
                        XWPFRun newRun = newParagraph.createRun();
                        newRun.getCTR().setRPr(sourceRun.getCTR().getRPr());
                        String text = sourceRun.getText(0);
                        if (text != null) {
                            newRun.setText(text.trim());
                        }
                    }
                }
            }
        }
    }

}
