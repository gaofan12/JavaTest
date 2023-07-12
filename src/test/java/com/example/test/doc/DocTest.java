package com.example.test.doc;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.example.test.json.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

/**
 * @author gaofan
 * @version 1.0
 * @date 2023/6/14 16:53
 **/
@Slf4j
public class DocTest {

    @Test
    public void test01() throws IOException, InvalidFormatException {
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        InputStream imageStream =
                FileUtil.getInputStream("E:\\work\\share-file\\be-java\\wwwroot\\docx\\2.png");
        InputStream inputStream = new ByteArrayInputStream(imageStream.readAllBytes());
        XWPFRun run = paragraph.createRun();
        BufferedImage image = ImageIO.read(inputStream);
        int width = image.getWidth();
        int height = image.getHeight();

        // 将图片插入到段落的运行中
        int imageType = XWPFDocument.PICTURE_TYPE_PNG; // 根据图片类型自行调整
        String fileName = "image.png";
        inputStream.reset();
        run.addPicture(inputStream,
                imageType, fileName, Units.toEMU(width), Units.toEMU(height));
        //创建一个word图片，并插入到文档中-->像素可改
        document.write(new FileOutputStream("E:\\work\\share-file\\be-java\\wwwroot\\docx\\2.docx"));
        document.close();
    }

    @Test
    public void test02() {
        String dateStr = "6/15/2023 2:08:41 PM";
        if (StrUtil.endWithAnyIgnoreCase(dateStr, "AM", "PM")) {
            DateTime parse = DateUtil.parse(dateStr,
                    DateTimeFormatter.ofPattern("M/dd/yyyy h:mm:ss a", Locale.ENGLISH));
            System.out.println(parse);
        }

    }


    @Test
    public void test03() {
        String dateString = "10/15/2023 2:08:41 PM";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy h:mm:ss a", Locale.ENGLISH);
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);

        System.out.println(dateTime);
    }

    @Test
    public void test04() throws IOException {
        XWPFDocument xwpfDocument = new XWPFDocument(FileUtil.getInputStream("C:\\Users\\gaofan\\Desktop\\测试复制行.docx"));
        List<XWPFParagraph> paragraphs = xwpfDocument.getParagraphs();
        XWPFTable xwpfTable = xwpfDocument.getTables().get(0);
        DocUtil.copyRows2(xwpfTable, xwpfTable.getRow(2), 2, 2);
        xwpfDocument.write(new FileOutputStream("C:\\Users\\gaofan\\Desktop\\测试复制行2.docx"));
        xwpfDocument.close();
    }


    @Test
    public void test05() {
        List<Integer> integers = List.of(1, 2, 3, 4, 5);
        for (Integer integer : integers) {
            if (integer == 3) {
               continue;
            }
            System.out.println("参数：   " + integer);
        }
        System.out.println("参数：   " + "结束");
    }
    @Test
    public void test06() {
        List<Integer> integers = List.of(1, 2, 3, 4, 5);
        for (Integer integer : integers) {
            if (integer == 3) {
               break;
            }
            System.out.println("参数：   " + integer);
        }
        System.out.println("参数：   " + "结束");
    }
    @Test
    public void test07() {
        List<Integer> integers = List.of(1, 2, 3, 4, 5);
        for (Integer integer : integers) {
            if (integer == 3) {
                return;
            }
            System.out.println("参数：   " + integer);
        }
        System.out.println("参数：   " + "结束");
    }
    @Test
    public void test08() {
        String[] array = "1:2：3:4：5,6".split("[:：]");
        System.out.println("参数：   " + "结束" + JacksonUtil.toJsonStr(array));
    }
}
