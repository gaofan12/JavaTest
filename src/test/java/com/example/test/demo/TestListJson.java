package com.example.test.demo;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author gaofan
 * @version 1.0
 * @date 2022/6/10 15:33
 **/
@Slf4j
public class TestListJson {

    @Test
    public void test01() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(str);
        ArrayList<HashMap<String, String>> objects = new ArrayList<>();
        for (JsonNode node : jsonNode) {
            if (StrUtil.isBlank(node.get("parent_cid").asText())) {
                HashMap<String, String> objectObjectHashMap = new HashMap<>();
                objectObjectHashMap.put("cid", node.get("cid").asText());
                objectObjectHashMap.put("name", node.get("name").asText());
                objects.add(objectObjectHashMap);
                //log.info(node.get("name").asText());
            }
        }
        log.info("-----------------------");
        ArrayList<String> list2 = new ArrayList<>();
        for (HashMap object : objects) {
            log.info(object.get("name").toString());
            for (JsonNode node : jsonNode) {
                if (object.get("cid").equals(node.get("parent_cid").asText())) {
                    list2.add(node.get("cid").asText());
                    log.info(node.get("name").asText());
                }
            }
            log.info("------------------------");
        }
        log.info("-----------------------");

    }

    public String str = "[\n" +
            "    {\n" +
            "        \"cid\": \"sl3laiaqi9zwqc7\",\n" +
            "        \"mtime\": 1653975255.27,\n" +
            "        \"name\": \"设备档案信息\",\n" +
            "        \"ctime\": null,\n" +
            "        \"parent_cid\": \"sl3lavgic5alt2h\",\n" +
            "        \"position\": 1,\n" +
            "        \"expanded\": false,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 2000,\n" +
            "        \"height\": 1078,\n" +
            "        \"bgcolor\": \"#fff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3laiaqi2j4032\",\n" +
            "        \"mtime\": 1654049277.42,\n" +
            "        \"name\": \"设备巡检单\",\n" +
            "        \"ctime\": null,\n" +
            "        \"parent_cid\": \"sl3tixf9k02yok9\",\n" +
            "        \"position\": 1,\n" +
            "        \"expanded\": false,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 2000,\n" +
            "        \"height\": 2414,\n" +
            "        \"bgcolor\": \"#fff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3laii5jl9cjeo\",\n" +
            "        \"mtime\": 1653465242.22,\n" +
            "        \"name\": \"状态 1\",\n" +
            "        \"ctime\": 1653465242.22,\n" +
            "        \"parent_cid\": \"\",\n" +
            "        \"position\": 1,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 2000,\n" +
            "        \"bgcolor\": \"transparent\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laii5jmjt87d\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3lavgic5alt2h\",\n" +
            "        \"mtime\": 1654053695.19,\n" +
            "        \"name\": \"设备基础管理\",\n" +
            "        \"ctime\": 1653465846.61,\n" +
            "        \"parent_cid\": \"\",\n" +
            "        \"position\": 2,\n" +
            "        \"expanded\": true,\n" +
            "        \"orientation\": \"folder\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3tixf9k02yok9\",\n" +
            "        \"mtime\": 1654053695.19,\n" +
            "        \"name\": \"巡检&保养&报修\",\n" +
            "        \"ctime\": 1653963106.83,\n" +
            "        \"parent_cid\": \"\",\n" +
            "        \"position\": 3,\n" +
            "        \"expanded\": true,\n" +
            "        \"orientation\": \"folder\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3tjgiewrxqhic\",\n" +
            "        \"mtime\": 1653983392.65,\n" +
            "        \"name\": \"设备巡检方案\",\n" +
            "        \"ctime\": 1653964000.7,\n" +
            "        \"parent_cid\": \"sl3lavgic5alt2h\",\n" +
            "        \"position\": 2,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3tjgy1nz9sotn\",\n" +
            "        \"mtime\": 1653983392.65,\n" +
            "        \"name\": \"设备保养方案\",\n" +
            "        \"ctime\": 1653964019.67,\n" +
            "        \"parent_cid\": \"sl3lavgic5alt2h\",\n" +
            "        \"position\": 3,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3tjiku0l3ct3g\",\n" +
            "        \"mtime\": 1654755055.46,\n" +
            "        \"name\": \"设备保养单\",\n" +
            "        \"ctime\": 1653964097.07,\n" +
            "        \"parent_cid\": \"sl3tixf9k02yok9\",\n" +
            "        \"position\": 2,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 2000,\n" +
            "        \"height\": 2118,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3tjip31ja43ga\",\n" +
            "        \"mtime\": 1654755270.14,\n" +
            "        \"name\": \"设备报修单\",\n" +
            "        \"ctime\": 1653964097.15,\n" +
            "        \"parent_cid\": \"sl3tixf9k02yok9\",\n" +
            "        \"position\": 3,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 2092,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3tjjrowy434yc\",\n" +
            "        \"mtime\": 1654053695.19,\n" +
            "        \"name\": \"备品备件管理\",\n" +
            "        \"ctime\": 1653964163.53,\n" +
            "        \"parent_cid\": \"\",\n" +
            "        \"position\": 4,\n" +
            "        \"expanded\": true,\n" +
            "        \"orientation\": \"folder\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3tjk673nme5au\",\n" +
            "        \"mtime\": 1654069139.69,\n" +
            "        \"name\": \"备品备件\",\n" +
            "        \"ctime\": 1653964169.06,\n" +
            "        \"parent_cid\": \"sl3tjjrowy434yc\",\n" +
            "        \"position\": 1,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1663,\n" +
            "        \"height\": 1042,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3tjnhr7vx26a6\",\n" +
            "        \"mtime\": 1654068940.86,\n" +
            "        \"name\": \"入库明细\",\n" +
            "        \"ctime\": 1653964324.86,\n" +
            "        \"parent_cid\": \"sl3tjjrowy434yc\",\n" +
            "        \"position\": 4,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3tjnmkmx86d7l\",\n" +
            "        \"mtime\": 1654068936.54,\n" +
            "        \"name\": \"备件入库单\",\n" +
            "        \"ctime\": 1653964332.91,\n" +
            "        \"parent_cid\": \"sl3tjjrowy434yc\",\n" +
            "        \"position\": 2,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3tjnskxvwgpnp\",\n" +
            "        \"mtime\": 1654068936.54,\n" +
            "        \"name\": \"备件领用单\",\n" +
            "        \"ctime\": 1653964339.55,\n" +
            "        \"parent_cid\": \"sl3tjjrowy434yc\",\n" +
            "        \"position\": 3,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3tjo2fl9uc3wf\",\n" +
            "        \"mtime\": 1654755389.1,\n" +
            "        \"name\": \"基础信息\",\n" +
            "        \"ctime\": 1653964351.12,\n" +
            "        \"parent_cid\": \"\",\n" +
            "        \"position\": 6,\n" +
            "        \"expanded\": true,\n" +
            "        \"orientation\": \"folder\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3tjo7zmhgdq59\",\n" +
            "        \"mtime\": 1654068934.35,\n" +
            "        \"name\": \"供应商信息\",\n" +
            "        \"ctime\": 1653964360.87,\n" +
            "        \"parent_cid\": \"sl3tjo2fl9uc3wf\",\n" +
            "        \"position\": 2,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3tjor8mfxo0q4\",\n" +
            "        \"mtime\": 1654068934.35,\n" +
            "        \"name\": \"仓库信息\",\n" +
            "        \"ctime\": 1653964383.68,\n" +
            "        \"parent_cid\": \"sl3tjo2fl9uc3wf\",\n" +
            "        \"position\": 3,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3tjou92liu5a4\",\n" +
            "        \"mtime\": 1654068934.35,\n" +
            "        \"name\": \"设备类型\",\n" +
            "        \"ctime\": 1653964383.78,\n" +
            "        \"parent_cid\": \"sl3tjo2fl9uc3wf\",\n" +
            "        \"position\": 5,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3tjp0oc53jhd1\",\n" +
            "        \"mtime\": 1654068934.35,\n" +
            "        \"name\": \"设备状态\",\n" +
            "        \"ctime\": 1653964394.59,\n" +
            "        \"parent_cid\": \"sl3tjo2fl9uc3wf\",\n" +
            "        \"position\": 6,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3tjp9e01murcz\",\n" +
            "        \"mtime\": 1654068934.35,\n" +
            "        \"name\": \"故障等级\",\n" +
            "        \"ctime\": 1653964403.4,\n" +
            "        \"parent_cid\": \"sl3tjo2fl9uc3wf\",\n" +
            "        \"position\": 7,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3tjpq3eb3dln9\",\n" +
            "        \"mtime\": 1654068934.35,\n" +
            "        \"name\": \"安装地点\",\n" +
            "        \"ctime\": 1653964425.05,\n" +
            "        \"parent_cid\": \"sl3tjo2fl9uc3wf\",\n" +
            "        \"position\": 4,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3tjpzs0djerqy\",\n" +
            "        \"mtime\": 1654068934.35,\n" +
            "        \"name\": \"频次计划\",\n" +
            "        \"ctime\": 1653964443.68,\n" +
            "        \"parent_cid\": \"sl3tjo2fl9uc3wf\",\n" +
            "        \"position\": 8,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3tq6znv9gj1dd\",\n" +
            "        \"mtime\": 1654147456.32,\n" +
            "        \"name\": \"新建、查看\",\n" +
            "        \"ctime\": 1653975308.3,\n" +
            "        \"parent_cid\": \"sl3laiaqi9zwqc7\",\n" +
            "        \"position\": 1,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 3000,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3ttgtn870o958\",\n" +
            "        \"mtime\": 1654147460.57,\n" +
            "        \"name\": \"新建、查看\",\n" +
            "        \"ctime\": 1653980805.91,\n" +
            "        \"parent_cid\": \"sl3tjgiewrxqhic\",\n" +
            "        \"position\": 1,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3tth4k439xpel\",\n" +
            "        \"mtime\": 1653980822.44,\n" +
            "        \"name\": \"页面 4\",\n" +
            "        \"ctime\": 1653980822.44,\n" +
            "        \"parent_cid\": \"\",\n" +
            "        \"position\": 2,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": \"刘桔子丶\",\n" +
            "        \"deleted_at\": 1653980822,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqithbuh7\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3tukgeo8y03uy\",\n" +
            "        \"mtime\": 1653982658.88,\n" +
            "        \"name\": \"页面 1\",\n" +
            "        \"ctime\": 1653982658.88,\n" +
            "        \"parent_cid\": \"\",\n" +
            "        \"position\": 1,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": \"刘桔子丶\",\n" +
            "        \"deleted_at\": 1653982658,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqithbuh7\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3tv09l8wtpvuf\",\n" +
            "        \"mtime\": 1653983397.44,\n" +
            "        \"name\": \"页面 4\",\n" +
            "        \"ctime\": 1653983397.44,\n" +
            "        \"parent_cid\": \"\",\n" +
            "        \"position\": 4,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": \"刘桔子丶\",\n" +
            "        \"deleted_at\": 1653983397,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqithbuh7\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3tv3jol95r509\",\n" +
            "        \"mtime\": 1654147463.22,\n" +
            "        \"name\": \"新建、查看\",\n" +
            "        \"ctime\": 1653983553.32,\n" +
            "        \"parent_cid\": \"sl3tjgy1nz9sotn\",\n" +
            "        \"position\": 1,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3tvenusa2e3jr\",\n" +
            "        \"mtime\": 1653984117.37,\n" +
            "        \"name\": \"创建巡检单\",\n" +
            "        \"ctime\": 1653984068.98,\n" +
            "        \"parent_cid\": \"sl3laiaqi2j4032\",\n" +
            "        \"position\": 1,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 3000,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3v0i71g6dp002\",\n" +
            "        \"mtime\": 1654053695.19,\n" +
            "        \"name\": \"设备数据看板\",\n" +
            "        \"ctime\": 1654053093.41,\n" +
            "        \"parent_cid\": \"\",\n" +
            "        \"position\": 1,\n" +
            "        \"expanded\": true,\n" +
            "        \"orientation\": \"folder\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3v0iim6axr5mw\",\n" +
            "        \"mtime\": 1654053206.41,\n" +
            "        \"name\": \"设备信息概览\",\n" +
            "        \"ctime\": 1654053108.41,\n" +
            "        \"parent_cid\": \"sl3v0i71g6dp002\",\n" +
            "        \"position\": 1,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 4000,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3v0kdj21v2obp\",\n" +
            "        \"mtime\": 1654053211.49,\n" +
            "        \"name\": \"巡检信息概览\",\n" +
            "        \"ctime\": 1654053211.49,\n" +
            "        \"parent_cid\": \"sl3v0i71g6dp002\",\n" +
            "        \"position\": 2,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 4000,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3v0m20pr3k8b6\",\n" +
            "        \"mtime\": 1654053288.36,\n" +
            "        \"name\": \"保养信息概览\",\n" +
            "        \"ctime\": 1654053273.53,\n" +
            "        \"parent_cid\": \"sl3v0i71g6dp002\",\n" +
            "        \"position\": 3,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 4000,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3v0n97j3ba9wj\",\n" +
            "        \"mtime\": 1654053349.15,\n" +
            "        \"name\": \"报修信息概览\",\n" +
            "        \"ctime\": 1654053333.62,\n" +
            "        \"parent_cid\": \"sl3v0i71g6dp002\",\n" +
            "        \"position\": 4,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 4000,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3v0rrjmhhlvbe\",\n" +
            "        \"mtime\": 1654053564.37,\n" +
            "        \"name\": \"备件信息概览\",\n" +
            "        \"ctime\": 1654053554.02,\n" +
            "        \"parent_cid\": \"sl3v0i71g6dp002\",\n" +
            "        \"position\": 5,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 4000,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3v0t8lj1cw7ms\",\n" +
            "        \"mtime\": 1654053619.51,\n" +
            "        \"name\": \"个人数据看板\",\n" +
            "        \"ctime\": 1654053608.65,\n" +
            "        \"parent_cid\": \"sl3v0i71g6dp002\",\n" +
            "        \"position\": 6,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 4000,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3v7q50nwpw8i4\",\n" +
            "        \"mtime\": 1654065229,\n" +
            "        \"name\": \"创建保养单\",\n" +
            "        \"ctime\": 1654065226.95,\n" +
            "        \"parent_cid\": \"sl3tjiku0l3ct3g\",\n" +
            "        \"position\": 1,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 3000,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3v9xyj7v6pkrz\",\n" +
            "        \"mtime\": 1654068949.07,\n" +
            "        \"name\": \"领用明细\",\n" +
            "        \"ctime\": 1654068949.07,\n" +
            "        \"parent_cid\": \"sl3tjjrowy434yc\",\n" +
            "        \"position\": 5,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3vakqa8mg4oos\",\n" +
            "        \"mtime\": 1654070019.33,\n" +
            "        \"name\": \"备品备件明细\",\n" +
            "        \"ctime\": 1654070011.22,\n" +
            "        \"parent_cid\": \"sl3tjk673nme5au\",\n" +
            "        \"position\": 1,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 3000,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3vblep37z90wc\",\n" +
            "        \"mtime\": 1654071739.41,\n" +
            "        \"name\": \"新增入库单\",\n" +
            "        \"ctime\": 1654071726.05,\n" +
            "        \"parent_cid\": \"sl3tjnmkmx86d7l\",\n" +
            "        \"position\": 1,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 3000,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl3wcwyw3up8gqu\",\n" +
            "        \"mtime\": 1654134414.93,\n" +
            "        \"name\": \"新增领用单\",\n" +
            "        \"ctime\": 1654134404.26,\n" +
            "        \"parent_cid\": \"sl3tjnskxvwgpnp\",\n" +
            "        \"position\": 1,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 3000,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl46hf50gqeelgn\",\n" +
            "        \"mtime\": 1654746637.88,\n" +
            "        \"name\": \"页面 1\",\n" +
            "        \"ctime\": 1654746637.88,\n" +
            "        \"parent_cid\": \"\",\n" +
            "        \"position\": 1,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": \"刘桔子丶\",\n" +
            "        \"deleted_at\": 1654746637,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqithbuh7\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl46i9n4jbrivgm\",\n" +
            "        \"mtime\": 1654748075.02,\n" +
            "        \"name\": \"新建\",\n" +
            "        \"ctime\": 1654748064.46,\n" +
            "        \"parent_cid\": \"sl3tjo7zmhgdq59\",\n" +
            "        \"position\": 1,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl46ia2w20trkxe\",\n" +
            "        \"mtime\": 1654748078.24,\n" +
            "        \"name\": \"页面 1\",\n" +
            "        \"ctime\": 1654748078.24,\n" +
            "        \"parent_cid\": \"\",\n" +
            "        \"position\": 1,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": \"刘桔子丶\",\n" +
            "        \"deleted_at\": 1654748078,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqithbuh7\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl46kyslbchtbn1\",\n" +
            "        \"mtime\": 1654752593.51,\n" +
            "        \"name\": \"新建\",\n" +
            "        \"ctime\": 1654752592,\n" +
            "        \"parent_cid\": \"sl3tjor8mfxo0q4\",\n" +
            "        \"position\": 1,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl46l2g4acbp2ft\",\n" +
            "        \"mtime\": 1654752765.3,\n" +
            "        \"name\": \"新建\",\n" +
            "        \"ctime\": 1654752765.3,\n" +
            "        \"parent_cid\": \"sl3tjpq3eb3dln9\",\n" +
            "        \"position\": 1,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl46l3rd0z13qxt\",\n" +
            "        \"mtime\": 1654752828.54,\n" +
            "        \"name\": \"新建\",\n" +
            "        \"ctime\": 1654752828.54,\n" +
            "        \"parent_cid\": \"sl3tjou92liu5a4\",\n" +
            "        \"position\": 1,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl46l5aw258wf62\",\n" +
            "        \"mtime\": 1654752899.25,\n" +
            "        \"name\": \"新建\",\n" +
            "        \"ctime\": 1654752891.75,\n" +
            "        \"parent_cid\": \"sl3tjp0oc53jhd1\",\n" +
            "        \"position\": 1,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl46lta7m6rh2ko\",\n" +
            "        \"mtime\": 1654754015.97,\n" +
            "        \"name\": \"新建\",\n" +
            "        \"ctime\": 1654754015.97,\n" +
            "        \"parent_cid\": \"sl3tjp9e01murcz\",\n" +
            "        \"position\": 1,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl46lxnqkytd5if\",\n" +
            "        \"mtime\": 1654754219.98,\n" +
            "        \"name\": \"新建\",\n" +
            "        \"ctime\": 1654754219.98,\n" +
            "        \"parent_cid\": \"sl3tjpzs0djerqy\",\n" +
            "        \"position\": 1,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl46mmtuyd9als3\",\n" +
            "        \"mtime\": 1654755393.23,\n" +
            "        \"name\": \"页面 1\",\n" +
            "        \"ctime\": 1654755393.23,\n" +
            "        \"parent_cid\": \"\",\n" +
            "        \"position\": 5,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 1024,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": \"刘桔子丶\",\n" +
            "        \"deleted_at\": 1654755393,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqithbuh7\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cid\": \"sl46mnmtkg08vrv\",\n" +
            "        \"mtime\": 1654755433.5,\n" +
            "        \"name\": \"创建报修单\",\n" +
            "        \"ctime\": 1654755426.66,\n" +
            "        \"parent_cid\": \"sl3tjip31ja43ga\",\n" +
            "        \"position\": 1,\n" +
            "        \"expanded\": null,\n" +
            "        \"orientation\": \"portrait\",\n" +
            "        \"width\": 1440,\n" +
            "        \"height\": 3000,\n" +
            "        \"bgcolor\": \"#ffffff\",\n" +
            "        \"bgimage\": null,\n" +
            "        \"artboard_id\": null,\n" +
            "        \"artboard_image_url\": \"\",\n" +
            "        \"deleter_name\": null,\n" +
            "        \"deleted_at\": null,\n" +
            "        \"project_meta_cid\": \"pl3laiapnzgg4k2\",\n" +
            "        \"screen_glue_cid\": \"sgl3laiaqinurfw3\"\n" +
            "    }\n" +
            "]";
}
