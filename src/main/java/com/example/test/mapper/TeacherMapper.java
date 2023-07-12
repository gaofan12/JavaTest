package com.example.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.test.model.entity.TeacherDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author gaofan
 * @version 1.0
 * @date 2022/4/22 15:48
 **/
@Mapper
public interface TeacherMapper extends BaseMapper<TeacherDO> {

}
