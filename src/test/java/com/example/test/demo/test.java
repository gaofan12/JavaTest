package com.example.test.demo;

import APPS.WorksheetService.WorksheetModel;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.net.NetUtil;
import cn.hutool.crypto.SecureUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * @author gaofan
 * @version 1.0
 * @date 2023/3/30 16:01
 **/
public class test {

    @Test
    public void testMD5() {
        //String s = SecureUtil.sha256().digestHex("str").replace("-", "").toLowerCase();
        //String s2 = SecureUtil.sha256().digestHex("str").toLowerCase();
        //System.out.println(s);
        //System.out.println(s2);
        //System.out.println(NetUtil.getLocalHostName());
        //GetMd5Str48("123");
        BigDecimal bigDecimal =
                new BigDecimal("2.7E+5");
        System.out.println(bigDecimal.toPlainString());
        System.out.println(bigDecimal.toString());
//        File file = FileUtil.file("/opt/test.txt");
//// "test"
//        String name = FileNameUtil.mainName(file);
//// "txt"
//        String name2 = FileNameUtil.extName(file);
    }

    public static String GetMd5Str48(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        byte[] array = SecureUtil.md5().digest(str);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
            System.out.printf(String.format("%03x", array[i] & 0xff));
            System.out.println("----");
        }
        return stringBuilder.toString();
    }

    @Test
    public void testJson() throws JsonProcessingException {
        String str = "{\"Type\":1,\"Data\":{\"TopicServer\":\"MD_WSService5\"," +
                "\"WorksheetId\":\"642104e4aa3bba040ceca1ad\",\"Items\":[{\"cid\":\"642104e4aa3bba040ceca2a2\"," +
                "\"RowId\":\"1df031a8-539a-43a5-abcd-dd54b9f93f1f\",\"IsAdd\":true," +
                "\"wsid\":\"642104e4aa3bba040ceca1ae\",\"rows\":[\"87dd511b-8c71-4923-a5c7-2a6f1adf0261\"]," +
                "\"SourceControlId\":\"642104e4aa3bba040ceca298\",\"AllowSummary\":true}],\"RequestId\":\"workflow\"," +
                "\"WorksheetRows\":[],\"WorksheetControls\":{},\"AccountId\":\"user-workflow\"," +
                "\"CreateTime\":\"2023-04-01T09:58:01.3660598+08:00\"}}";
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode map = mapper.readValue(str, ObjectNode.class);
        System.out.println(map.toString());
        System.out.println(map.get("Data").toString());
        System.out.println(mapper.readValue(map.get("Data").toString(), HashMap.class));

    }

    @Test
    public void testGrpc() {
        WorksheetModel.GetRowsDataByIdsResponse.Builder getRowsDataByIdsResponse =
                WorksheetModel.GetRowsDataByIdsResponse.newBuilder();
        WorksheetModel.RowsCount rowsCount = WorksheetModel.RowsCount.newBuilder()
                .setCount(2).addRelations("1").setWorksheet(WorksheetModel.Worksheet.newBuilder()
                        .setAllowAdd(true).setWorksheetId("work").build()).build();
        //准备参数
        getRowsDataByIdsResponse.setData(rowsCount);//设置data
        System.out.println("先设置date结果");
        System.out.println(getRowsDataByIdsResponse);
        //getRowsDataByIdsResponse.setData(getRowsDataByIdsResponse.getDataBuilder().clearRelations().
        //        addAllRelations(List.of("1", "2", "3")));
        //getRowsDataByIdsResponse.getDataBuilder().
        //        addAllRelations(List.of("1", "2", "3"));
        System.out.println("清除原有data结果，重新设置");
        getRowsDataByIdsResponse.getDataBuilder().clearRelations().addAllRelations(List.of("11", "12",
                "13")).getWorksheetBuilder().setWorksheetId("id");
        getRowsDataByIdsResponse.setCode(1);
        WorksheetModel.GetRowsDataByIdsResponse response = getRowsDataByIdsResponse.build();
        System.out.println(System.identityHashCode(response));
        System.out.println(response);
        System.out.println("清除原有response结果  2222，重新设置");
        WorksheetModel.RowsCount.Builder builder =
                response.toBuilder().getDataBuilder().clearRelations().addAllRelations(List.of("21", "22", "23"));
        System.out.println(System.identityHashCode(response));
        System.out.println(response);
        System.out.println("toBuild会重新生成一个对象");
        System.out.println(System.identityHashCode(builder.build()));
        System.out.println(builder.build());
    }
}
