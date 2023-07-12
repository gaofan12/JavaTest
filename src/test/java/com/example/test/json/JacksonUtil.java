package com.example.test.json;


import cn.hutool.core.util.StrUtil;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * 中间部分方法没意义.用不到.
 * TODO. 全局使用一个默认的ObjectMapper,可能导致无法修改其中配置.
 *
 * @author gaofan
 * @author xiaoxiong
 */
@Slf4j
public class JacksonUtil {

    /**
     * 实际上共用一个objectMapper,没有性能问题，并且是线程安全的
     */
    private static ObjectMapper objectMapper = createObjectMapper();

    /**
     * 创建ObjectMapper，当需要修改 默认序列化配置 时，使用本方法.
     *
     * @return ObjectMapper
     */
    public static ObjectMapper createObjectMapper(Module... modules) {
        //大小写脱敏 默认为false 需要改为true
        ObjectMapper mapper =
                JsonMapper.builder()
                        .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true)
                        .build();


        //设置解析protobuf文件
        //ProtobufModule protobufModule = new ProtobufModule();
        //设置特殊解析mongodb
        SimpleModule simpleModule = new SimpleModule();
        //objectId要序列化成字符串
        simpleModule.addSerializer(ObjectId.class, ToStringSerializer.instance);
       // simpleModule.addDeserializer(ObjectId.class, new ObjectIdDeserializer());
       // simpleModule.addDeserializer(Date.class, new DateDeserializer());


        List<Module> moduleList = new ArrayList<>(List.of(modules));
        moduleList.add(simpleModule);
        //moduleList.add(protobufModule);
        moduleList.add(new JavaTimeModule());
        //注册入mongo中
        mapper.registerModules(moduleList);
        // 日期格式
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        //GMT+8
        //map.put("CTT", "Asia/Shanghai");
        mapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        // Include.NON_NULL 属性为NULL 不序列化
        //ALWAYS // 默认策略，任何情况都执行序列化
        //NON_EMPTY // null、集合数组等没有内容、空字符串等，都不会被序列化
        //NON_DEFAULT // 如果字段是默认值，就不会被序列化
        //NON_ABSENT // null的不会序列化，但如果类型是AtomicReference，依然会被序列化
        //mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        //允许字段名没有引号（可以进一步减小json体积）：
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        //允许单引号：
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

        // 允许出现特殊字符和转义符
        //mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);这个已经过时。
        mapper.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);

        //允许C和C++样式注释：
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);

        //序列化结果格式化，美化输出
        //mapper.enable(SerializationFeature.INDENT_OUTPUT);

        //枚举输出成字符串
        //WRITE_ENUMS_USING_INDEX：输出索引
        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_INDEX);

        //空对象不要抛出异常：
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        //Date、Calendar等序列化为时间格式的字符串(如果不执行以下设置，就会序列化成时间戳格式)：
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        //反序列化时，遇到未知属性不要抛出异常：
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        //反序列化时，遇到忽略属性不要抛出异常：
        mapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);

        //反序列化时，空字符串对于的实例属性为null：
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        //mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

        return mapper;
    }


    /**
     * 获取默认配置的ObjectMapper，便于直接调用其方法.
     *
     * @return ObjectMapper
     */
    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }


    /**
     * Object转json字符串
     *
     * @param obj Object
     * @param <T> 泛型
     * @return json字符串
     */
    public static <T> String toJsonStr(T obj) {
        return toJsonStr(obj, objectMapper);
    }

    /**
     * Object转json字符串
     *
     * @param obj Object
     * @param <T> 泛型
     * @return json字符串
     */
    public static <T> String toJsonStr(T obj, ObjectMapper objectMapper) {
        try {
            if (obj == null) {
                return null;

            } else if (obj instanceof String) {
                return (String) obj;

            } else {
                return objectMapper.writeValueAsString(obj);
            }

        } catch (Exception e) {
            log.error("Parse object to String error", e);
            return null;
        }
    }

    /**
     * Object转json字符串
     *
     * @param obj Object
     * @param <T> 泛型
     * @return json字符串
     */
    public static <T> byte[] toJsonBytes(T obj) {
        return toJsonBytes(obj, objectMapper);
    }

    /**
     * Object转json字符串
     *
     * @param obj Object
     * @param <T> 泛型
     * @return json字符串
     */
    public static <T> byte[] toJsonBytes(T obj, ObjectMapper objectMapper) {
        try {
            if (obj == null) {
                return null;

            } else if (obj instanceof byte[]) {
                return (byte[]) obj;

            } else {
                return objectMapper.writeValueAsBytes(obj);
            }

        } catch (Exception e) {
            log.error("Parse object to String error", e);
            return null;
        }
    }

    public static <T> String toJsonStrPretty(T obj) {
        return toJsonStrPretty(obj, objectMapper);
    }

    /**
     * Object转json字符串并格式化美化
     *
     * @param obj Object
     * @param <T> 泛型参数
     * @return json字符串
     */
    public static <T> String toJsonStrPretty(T obj, ObjectMapper objectMapper) {
        try {
            if (obj == null) {
                return null;
            } else if (obj instanceof String) {
                return (String) obj;
            } else {
                return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            }
        } catch (Exception e) {
            log.error("Parse object to String Pretty error: {}", e.getMessage());
            return null;
        }
    }

    public static <T> T toBean(String json, Class<T> clazz) {
        return toBean(json, clazz, objectMapper);
    }

    /**
     * json转object
     *
     * @param json  json字符串
     * @param clazz 转换实体
     * @param <T>   泛型
     * @return 返回
     */
    @SuppressWarnings("unchecked")
    public static <T> T toBean(String json, Class<T> clazz, ObjectMapper objectMapper) {
        try {
            if (StrUtil.isEmpty(json) || clazz == null) {
                return null;
            } else if (clazz.equals(String.class)) {
                return (T) json;
            } else {
                return objectMapper.readValue(json, clazz);
            }
        } catch (IOException e) {
            log.error("Parse String to bean error", e);
            return null;
        }
    }

    public static <T> T toAnyTypeBean(String json, TypeReference<T> typeReference) {
        return toAnyTypeBean(json, typeReference, objectMapper);
    }

    /**
     * json转object，支持任意类型. 比如 new TypeReference<List<com.apps.entity.User>>() {}, new TypeReference<Map<String,User>>()
     * {} 等.
     *
     * @param <T>           泛型
     * @param json          字符串
     * @param typeReference 例如:<li>new TypeReference<List<com.apps.entity.User>>() {}</li>
     * @return 返回
     */
    @SuppressWarnings("unchecked")
    public static <T> T toAnyTypeBean(String json, TypeReference<T> typeReference, ObjectMapper objectMapper) {
        try {
            if (StrUtil.isEmpty(json) || typeReference == null) {
                return null;

            } else if (typeReference.getType().equals(String.class)) {
                return (T) json;

            } else {
                return objectMapper.readValue(json, typeReference);
            }

        } catch (IOException e) {
            log.error("Parse JSON Error", e);
            return null;
        }
    }


    /**
     * 将Array字符串转换为Bean的List，默认为ArrayList.
     * 如果需要返回其他类型的List, 请使用toList(String, Class, Class)
     *
     * @param <T>          Bean类型
     * @param jsonArray    Array字符串
     * @param elementClass List中元素类型
     * @return List
     */
    public static <T> ArrayList<T> toList(String jsonArray, Class<T> elementClass) {
        return toList(jsonArray, elementClass, objectMapper);
    }

    public static <T> ArrayList<T> toList(String jsonArray, Class<T> elementClass, ObjectMapper objectMapper) {
        return toList(jsonArray, ArrayList.class, elementClass, objectMapper);
    }

    /**
     * 将JSONArray转换为Bean的List，默认为ArrayList
     *
     * @param <T>          Bean类型, 简单类型
     * @param jsonArray    {@link ArrayNode}
     * @param elementClass List中元素类型
     */
    public static <T> List<T> toList(ArrayNode jsonArray, Class<T> elementClass, ObjectMapper objectMapper) {
        if (jsonArray == null) {
            return null;
        }
        final List<T> list = new ArrayList<>(jsonArray.size());
        for (JsonNode node : jsonArray) {
            list.add(toBean(node.toString(), elementClass));
        }
        return list;
    }

    public static <T> List<T> toList(JsonNode jsonArray, Class<T> elementClass) {
        return toList(jsonArray, elementClass, objectMapper);
    }

    public static <T> List<T> toList(JsonNode jsonArray, Class<T> elementClass, ObjectMapper objectMapper) {
        if (jsonArray instanceof ArrayNode) {
            return toList((ArrayNode) jsonArray, elementClass, objectMapper);
        }
        throw new IllegalArgumentException("Parse JSON Error, 传入 JsonNode is not a json array! toString: " + jsonArray.toString());
    }


    /**
     * string转object 用于转为集合对象
     *
     * @param jsonArray       Json字符串
     * @param <T>             泛型
     * @param collectionClass 被转集合的类,比如
     *                        <p>ArrayList.class</p>
     * @param elementClass    被转集合中对象类型的类
     *                        <p>User.class</p>
     * @return 返回
     */
    public static <T> T toList(String jsonArray, Class<? extends Collection> collectionClass,
                               Class<?> elementClass, ObjectMapper objectMapper) {
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
            return objectMapper.readValue(jsonArray, javaType);
        } catch (IOException e) {
            log.error("Parse JSON Error, " + jsonArray, e);
            return null;
        }
    }


    /**
     * 将Json字符串转换为HashMap.
     *
     * @param json json字符串
     * @return 实际是LinkedHashMap
     */
    @SuppressWarnings("unchecked")
    public static HashMap<String, Object> toHashMap(String json) {
        //忽略警告-程序员知道的确是这个类型
        return (HashMap<String, Object>) toMap(json, HashMap.class, String.class, Object.class, objectMapper);
    }

    @SuppressWarnings("unchecked")
    public static <Value> HashMap<String, Value> toHashMap(String json, Class<? extends Value> valueClass) {
        //忽略警告-程序员知道的确是这个类型
        return (HashMap<String, Value>) toMap(json, HashMap.class, String.class, valueClass, objectMapper);
    }

    @SuppressWarnings("unchecked")
    public static <Value> HashMap<String, Value> toHashMap(String json, Class<? extends Value> valueClass,
                                                           ObjectMapper objectMapper) {
        //忽略警告-程序员知道的确是这个类型
        return (HashMap<String, Value>) toMap(json, HashMap.class, String.class, valueClass, objectMapper);
    }

    @SuppressWarnings("unchecked")
    public static <Key, Value> HashMap<Key, Value> toHashMap(String json, Class<? extends Key> keyClass, Class<?
            extends Value> valueClass, ObjectMapper objectMapper) {
        //忽略警告-程序员知道的确是这个类型
        return (HashMap<Key, Value>) toMap(json, HashMap.class, keyClass, valueClass, objectMapper);
    }


    @SuppressWarnings("unchecked")
    public static LinkedHashMap<String, Object> toLinkedHashMap(String json) {
        return (LinkedHashMap<String, Object>) toMap(json, LinkedHashMap.class, String.class, Object.class,
                objectMapper);
    }

    @SuppressWarnings("unchecked")
    public static <Value> LinkedHashMap<String, Object> toLinkedHashMap(String json,
                                                                        Class<? extends Value> valueClass) {
        return (LinkedHashMap<String, Object>) toMap(json, LinkedHashMap.class, String.class, valueClass, objectMapper);
    }

    @SuppressWarnings("unchecked")
    public static <Value> LinkedHashMap<String, Value> toLinkedHashMap(String json, Class<? extends Value> valueClass
            , ObjectMapper objectMapper) {
        //忽略警告-程序员知道的确是这个类型
        return (LinkedHashMap<String, Value>) toMap(json, LinkedHashMap.class, String.class, valueClass, objectMapper);
    }

    public static <Key, Value> Map<Key, Value> toMap(String json, Class<? extends Map> mapClass,
                                                     Class<? extends Key> keyClass,
                                                     Class<? extends Value> valueClass, ObjectMapper objectMapper) {
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
            return objectMapper.readValue(json, javaType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    // 定义一个包含日期的POJO类
    static class MyDate {
        private Date date;

        // getter和setter方法
        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }

    public static void main(String[] args) throws JsonProcessingException {
        User user = new User();
        user.setAge(10);
        user.setNameStr("gao");
        log.info(toJsonStr(user));
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_CAMEL_CASE);
        System.out.println("大驼峰: " + mapper.writeValueAsString(user));
        ObjectMapper mapper2 = new ObjectMapper();
        Map<String, String> map = Map.of("K1K1", "V1");
        mapper2.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
        System.out.println("小驼峰:" + mapper2.writeValueAsString(user));
        System.out.println("小驼峰:" + mapper2.writeValueAsString(map));
        ObjectMapper mapper3 = new ObjectMapper();
        mapper3.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        System.out.println("下划线: " + mapper3.writeValueAsString(user));
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CASE);
        System.out.println("小写 :" + mapper.writeValueAsString(user));
        //String date01 = "2022-11-02T11:10:05.345949";
        //// 定义一个JSON字符串
        //String json = "{\"date\":\"2022-11-02T11:10:05.345949\"}";
        //
        //Date date = JacksonUtil.toBean(date01, Date.class);
        ////System.out.println(date);
        //System.out.println(JacksonUtil.toBean(json, MyDate.class));
        //System.out.println(JacksonUtil.toBean("11111111111", ObjectId.class));
        //HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        ////objectObjectHashMap.put("objectId", new ObjectId());
        //objectObjectHashMap.put("date", new User("张三", 18));
        //String s = JacksonUtil.toJsonStr(objectObjectHashMap);
        //
        //String userJson = JacksonUtil.toJsonStr(List.of(new User("张三", 18), new User("李四", 19)));
        //
        //
        //ArrayList<User> x = JacksonUtil.toList(userJson, User.class);
        //System.out.println(x);

        // System.out.println(JacksonUtil.toAnyTypeBean(userJson, new TypeReference<List<User>>() {
        // }));

//        System.out.println(s);
//        HashMap<String,User> stringObjectMap = JacksonUtil.toLinkedHashMap(s,User.class);
//        System.out.println(JacksonUtil.toJsonStr(stringObjectMap));
//        System.out.println(stringObjectMap.getClass());

    }

    public static class User {
        private String NameStr;
        private Integer Age;

        public User() {
        }

        public User(String name, Integer age) {
            this.NameStr = name;
            this.Age = age;
        }

        public String getNameStr() {
            return NameStr;
        }

        public void setNameStr(String nameStr) {
            this.NameStr = nameStr;
        }

        public Integer getAge() {
            return Age;
        }

        public void setAge(Integer age) {
            this.Age = age;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("User{");
            sb.append("name='").append(NameStr).append('\'');
            sb.append(", age=").append(Age);
            sb.append('}');
            return sb.toString();
        }
    }


}
