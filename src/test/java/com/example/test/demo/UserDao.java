package com.example.test.demo;

import cn.hutool.core.date.DateUtil;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.client.model.Filters;
import lombok.Data;
import org.apache.commons.lang3.RegExUtils;
import org.bson.BsonArray;
import org.bson.BsonElement;
import org.bson.BsonInt32;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.graalvm.home.Version;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Field;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class UserDao extends AbstractDao<String> {

    public static void main(String[] args) {
        //Pattern.compile("\\d+").matcher("11a22b33c44")
        //        .results().forEach(System.out::println);
        //Iterator<MatchResult> iterator = Pattern.compile("\\d+",Pattern.CASE_INSENSITIVE).matcher("11a22b33c44")
        //        .results().iterator();
        //while (iterator.hasNext()) {
        //    System.out.println(iterator.next().group());
        //}
        //
        //System.out.println(generate(5));

    }

    @Test
    public void testBasic() {
        Bson bson = Filters.and(Arrays.asList(
                Filters.gte("createdTime", Calendar.getInstance().getTime()),
                Filters.gte("apis.count", 1)));
        System.out.println(bson.toString());
       // new MongoTemplate().getCollection("mongodbtest").find(bson)
    }

    @Test
    public void testDouble() {
        System.out.println(-1);
        int num = Double.valueOf(0.0 - 1.2).intValue();
        System.out.println(num);
        System.out.println(DateUtil.format(DateUtil.offsetDay(new Date(), num), "yyyy-MM-dd"));
        System.out.println(Pattern.compile("aa|Ab|BB", Pattern.CASE_INSENSITIVE).pattern());


        System.out.println(new BasicDBObject("keywords", new BasicDBObject("$regex", Pattern.compile("{.aaa|*", Pattern.CASE_INSENSITIVE))));

        System.out.println(new BasicDBObject("keywords",
                new BasicDBObject("$regex", escape("{.aaa|*")).append("$options", "i")));

    }

    /**
     * 正则表达式有以下特殊字符。需要转义  * . ? + $ ^ [ ] ( ) { } | \ /
     */
    public static String escape(String str) {
        String str1 = "*.?+$^[](){}|\\/";
        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            String ss = String.valueOf(str.charAt(i));
            if (str1.contains(ss)) {
                ss = "\\" + ss;
            }
            sf.append(ss);
        }
        return sf.toString();
    }

    @Test
    public void testPattern() {

        Pattern.compile("[0-9]+", Pattern.CASE_INSENSITIVE)
                .matcher("aaa111bb22cc33").results().forEach(item -> {
                    System.out.println(item.group());
                });
    }

    @Test
    public void testUpdate() {
        List<Integer> integers = List.of(1, 2, 3);
        Update update = Update.update("key1", "1");
        update.addToSet("key2", new Document("$each", integers));
        System.out.println(update.toString());
        System.out.println(update.getUpdateObject());
    }
    @Test
    public void testVersion() {
        Version version = Version.parse("1.1.1.1");
        System.out.println(version.compareTo(Version.parse("1.1.1.1")));
    }

    @Test
    public void testDocument() {
        Document basicDBObject = new Document("id",
                new Document("$in", List.of(1, 2, 3)));//多条件{ "id" : { "$in" : [1, 2, 3]}
        basicDBObject.put("name", new BsonElement("item.getControlId()",
                new BsonInt32(Integer.parseInt("10"))).getValue());
        //增加一个and "name" : "gaofan"
        basicDBObject.append("append", "va");//增加一个and
        System.out.println(basicDBObject.toJson());
        System.out.println(basicDBObject.toString());
        System.out.println(new BasicQuery(basicDBObject.toJson()));// BasicQuery extends Query
        System.out.println("basicDBObject----------");// BasicQuery extends Query

        BasicDBObject parse = BasicDBObject.parse(basicDBObject.toJson());
        System.out.println(parse.toJson());
        BasicQuery basicQuery = new BasicQuery(parse.toJson());
        basicQuery.setSortObject(new Document("text", "aaa"));
        System.out.println("----------");
        System.out.println(basicQuery.getQueryObject());
        System.out.println(basicQuery);
        System.out.println("-------------");
    }
    @Test
    public void testList() {
        List<Integer> integers = List.of(1, 2, 3);
        List<Integer> integers1 = integers.stream().filter(item -> item > 3).toList();
       // boolean add = integers.add(1);
        List<Integer> integers2 = integers.stream().filter(item -> item > 3).collect(Collectors.toList());
        boolean add2 = integers2.add(1);
    }

    @Test
    public void testPoint() {
        Boolean a = true;
        testBoolean(a);
        System.out.println(a);
        System.out.println("//////////////////");
        Integer aInteger = 1256;
        testaInteger(aInteger);
        System.out.println(aInteger);
        System.out.println("//////////////////");
        String str = "test";
        testStr(str);
        System.out.println(str);
        System.out.println("//////////////////");
        Optional optional = new Optional();
        optional.setValue(str);
        System.out.println(System.identityHashCode(optional));
        testOptional(optional);
        System.out.println(System.identityHashCode(optional));
        System.out.println(optional.getValue());
    }

    private void testOptional(Optional str1) {
        System.out.println(str1.getValue());
        System.out.println(System.identityHashCode(str1));
        str1.setValue("test1");
        System.out.println(System.identityHashCode(str1));
        System.out.println(str1.getValue());

    }
    @Data
    public final class Optional {

        /**
         * If non-null, the value; if null, indicates no value is present
         */
        private String value;
    }

    private void testBoolean(Boolean aBoolean) {
        System.out.println(aBoolean);
        aBoolean = false;
        System.out.println(aBoolean);
    }

    private void testaInteger(Integer aInteger){
        System.out.println(aInteger);
        aInteger = 1000;
        System.out.println(aInteger);
    }

    private void testStr(String str){
        System.out.println(str);
        str = "qwe";
        System.out.println(str);
    }

    @Test
    public void testBasicDBObject()  {
        BasicDBObject basicDBObject = new BasicDBObject("id",
                new BasicDBObject("$in", List.of(1, 2, 3)));//多条件{ "id" : { "$in" : [1, 2, 3]}
        basicDBObject.put("name", new BsonElement("item.getControlId()",
                new BsonInt32(Integer.parseInt("10"))).getValue());
        //增加一个and "name" : "gaofan"
        basicDBObject.append("append", "va");//增加一个and
        System.out.println(basicDBObject.toJson());
        System.out.println(basicDBObject.toString());
        System.out.println(new BasicQuery(basicDBObject.toString()));// BasicQuery extends Query
        BasicQuery basicQuery = new BasicQuery(basicDBObject.toString());
        basicQuery.setSortObject(new Document("text", "aaa"));
        System.out.println("----------");
        System.out.println(basicQuery.getQueryObject());
        System.out.println(basicQuery);
        System.out.println("-------------");
        Field fields = new Field();
        fields.include("_id").exclude("status").exclude("sharerange").exclude("uaid")
                .exclude("wsutime").exclude(
                        "keywords").exclude("discussunreads").exclude("unreads");
        basicQuery.setFieldsObject(fields.getFieldsObject());
        System.out.println("----------");
        System.out.println(basicQuery.getFieldsObject());
        System.out.println(basicQuery);
        System.out.println("-------------");
        BasicDBList objects = new BasicDBList();
        objects.add(new BasicDBObject("id", 1));
        objects.add(new BasicDBObject("name", "gao"));
        // objects.put("va", new BasicDBObject("id2", 2));报错 BasicBSONList can only work with numeric keys, not: [va]
        objects.put(0, new BasicDBObject("id2", 2));//key是兼容的占位符
        System.out.println(objects.toString());
        basicDBObject.put("$or", objects);//BasicDBList只是一个数组，可以存入多个basicDBObject查询条件，并最终选择or或者and等连接符
        System.out.println(basicDBObject.toString());
        BasicDBList list = new BasicDBList();
        objects.add(1);
        objects.add(2);
        System.out.println(list.toString());
        basicDBObject.put("$or", list);
        System.out.println(basicDBObject.toString());
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> row = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            row.add(0, 1);
            for (int j = 1; j < row.size() - 1; j++) {
                row.set(j, row.get(j) + row.get(j + 1));
            }
            res.add(new ArrayList<>(row));
        }
        return res;
    }

}
