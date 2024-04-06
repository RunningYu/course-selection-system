package courseselectionsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/4
 */

/**
 * @Data 注解可以自动生成 get和 set方法
 */
@Data
public class User {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 学号或职工号
     */
    private String number;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户名
     */
    private String username;

    /**
     * 头像url
     */
    private String headshot;

    /**
     * 学校
     */
    private String school;

    /**
     * 班级
     */
    private String clas;

    /**
     * 首选科目（历史 or 物理）
     */
    private String firstSubject;

    /**
     * 次选科目（化学、生物、政治、地理）
     */
    private String secondarySubjects;

    /**
     * 模拟选课组合
     */
    private String mockSubjects;


    /**
     * 所在地区
     */
    private String place;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
