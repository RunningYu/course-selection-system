package courseselectionsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/5
 * 定好科目选学校/专业
 */
@Data
public class ChooseMajor {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 学校所在地区
     */
    private String place;

    /**
     * 院校名称
     */
    private String college;

    /**
     * 科目组合
     */
    private String subjects;

    /**
     * 专业名称
     */
    private String major;

    /**
     * 包含专业
     */
    private String containsMajor;

    /**
     * 报考方向
     */
    private String direction;

    /**
     * 科目要求
     */
    private String subjectsRequest;

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
