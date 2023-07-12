package com.example.test.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 学生表
 */
@ApiModel(value = "学生表")
@Getter
@Setter
@ToString
@TableName(value = "student")
public class StudentDO {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Long id;

    /**
     * 名字
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value = "名字")
    private String name;

    /**
     * 性别
     */
    @TableField(value = "sex")
    @ApiModelProperty(value = "性别")
    private String sex;

    /**
     * 年龄
     */
    @TableField(value = "age")
    @ApiModelProperty(value = "年龄")
    private Integer age;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    /**
     * 创建人ID
     */
    @TableField(value = "creator_id")
    @ApiModelProperty(value = "创建人ID")
    private String creatorId;

    /**
     * 描述
     */
    @TableField(value = "`desc`")
    @ApiModelProperty(value = "描述")
    private String desc;

    /**
     * 修改人员ID
     */
    @TableField(value = "update_user_id")
    @ApiModelProperty(value = "修改人员ID")
    private String updateUserId;

    /**
     * 删除标志,禁止硬删除数据，默认0是有效，1是删除
     */
    @TableField(value = "del_flag")
    @ApiModelProperty(value = "删除标志,禁止硬删除数据，默认0是有效，1是删除")
    private String delFlag;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_SEX = "sex";

    public static final String COL_AGE = "age";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_CREATOR_ID = "creator_id";

    public static final String COL_DESC = "desc";

    public static final String COL_UPDATE_USER_ID = "update_user_id";

    public static final String COL_DEL_FLAG = "del_flag";
}