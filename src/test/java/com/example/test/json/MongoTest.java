package com.example.test.json;

import com.example.test.model.entity.TeacherDO;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonObjectId;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author gaofan
 * @version 1.0
 * @date 2023/5/10 21:19
 **/
@Slf4j
public class MongoTest {




    @Test
    //@Rollback(value = false)
    public void TestPattern2() {
        String input = "o)";
        String regex = "([1-9]\\d*\\.?\\d*)|(0\\.?\\d*)|(o)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String match = matcher.group();
            System.out.println(match);
        }
    }


    @Test
    //@Rollback(value = false)
    public void TestPattern() {
        String input = "cSUM($646c7966edc69c193e7e68e9$,$646c7966edc69c193e7e68e9$)";
        String input2 = "cSUM(2121,111)";
        String regex = "\\$(.*?)\\$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input2);

        while (matcher.find()) {
            String match = matcher.group(1);
            System.out.println(match);
        }
    }


    @Test
    //@Rollback(value = false)
    public void TestCurd() {
        Bson bson = Updates.pull("users", Filters.in("accountId", List.of("1", "22")));
        Update update = Update.fromDocument(Document.parse(bson.toBsonDocument().toJson()));
        log.info("update:{}", update);
        log.info("update:{}", update.toString());
        Update update1 = new Update();
        Update pull = update1.pull("users", Filters.in("accountId", List.of("1", "22")).toBsonDocument());
        log.info("update1:{}", update1);
        Update update3 = new Update().pull("filters", Filters.in("controlId", List.of("1", "22")).toBsonDocument())
                .pullAll("controlIds", List.of("1", "22").toArray());
        log.info("update3:{}", update3);

    }


    public static void main(String[] args) {
        Document document = new Document();
        document.put("date", new Date());
        document.put("id", new ObjectId());
        BsonDocument bsonDocument = BsonDocument.parse(document.toJson());
        String str = JacksonUtil.toJsonStr(bsonDocument.toJson(JsonWriterSettings.builder().outputMode(JsonMode.STRICT).build()));
        System.out.println("bsonDocument" + str);
        Document document1 = Document.parse(bsonDocument.toJson());
        String user = JacksonUtil.toJsonStr(document1);
        System.out.println(user);
        System.out.println("user" + JacksonUtil.toJsonStr(JacksonUtil.toBean(user, User.class)));


        BsonArray bsonValues = new BsonArray();
        bsonValues.add(bsonDocument);
        BsonDocument bsonDocument1 = new BsonDocument("key2", new BsonObjectId(new ObjectId()));
        bsonValues.add(bsonDocument1);
        bsonValues.add(new BsonObjectId(new ObjectId()));
        //Document parse = Document.parse(bsonValues.asDocument().toJson());
        //System.out.println("BsonArray" + JacksonUtil.toJsonStr(parse));
        //Document document2 = new Document("list", bsonValues);
        //System.out.println("document2" + JacksonUtil.toJsonStr(document2));

        List<Object> documents =
                Collections.singletonList(bsonValues.stream().map(bsonValue -> {
                    if (bsonValue instanceof BsonDocument) {
                        return Document.parse(bsonValue.asDocument().toJson());
                    } else {
                        return bsonValue;
                    }
                }).toList());
        System.out.println("BsonArray" + JacksonUtil.toJsonStr(documents));

    }


}

class User {
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    private ObjectId id;
}
