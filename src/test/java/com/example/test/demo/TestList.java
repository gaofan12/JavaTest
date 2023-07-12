package com.example.test.demo;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @author gaofan
 * @version 1.0
 * @date 2023/1/18 12:20
 **/
@Slf4j
public class TestList {

    public static void main(String[] args) {
        //ListObj listObj = new ListObj();
        //listObj.getList().add("1");
        //listObj.getList().add("2");
        //UserDao userDao = new UserDao();
        //Class<String> actualType = userDao.getActualType();
        //System.out.println(actualType.getName());
        //
        //ParameterizedType parameterizedType = (ParameterizedType) userDao.getClass().getGenericSuperclass();
        //System.out.println(parameterizedType.toString());
        //System.out.println( parameterizedType.getActualTypeArguments()[0].getTypeName());

        System.out.println(String.format("测试%1$s %2$s", "gao", "测试"));
        System.out.println(IdUtil.simpleUUID());
        System.out.println(isGuidUnsafe(IdUtil.simpleUUID()));
    }

    @Test
    public void test01() {
        ListObj listObj = new ListObj();
        listObj.getList().add("1");
        listObj.getList().add("2");
        log.info("{}", listObj);
    }

    @Test
    public void test02() {
        Sort.Order sort = Sort.Order.desc("_id");
        Criteria criteria = new Criteria();
        Query query = new Query().with(Sort.by(Sort.Order.desc("_id")));
        System.out.println(query.toString());
    }

    public static boolean isGuidUnsafe(String uuid) {
        // UUID校验
        if (uuid == null) {
            System.out.println("uuid is null");
            return false;
        }
        String regex = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";
        if (uuid.matches(regex)) {
            //byte[] guidString36 = uuid.getBytes();
            //if (guidString36[8] != '-' || guidString36[13] != '-' || guidString36[18] != '-'
            //        || guidString36[23] != '-') {
            //    return false;
            //}
            return true;
        }
        return false;
    }

}

