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
@Data
public class Condition {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 输入的条件（专业名或院校名）
     */
    private String terms;

    /**
     * 条件类型 1-专业 2-院校
     */
    private Integer kind;

    /**
     * 本科 or 专科
     */
    private String degree;

    /**
     * 概述结果
     */
    private String overviewResult;

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
