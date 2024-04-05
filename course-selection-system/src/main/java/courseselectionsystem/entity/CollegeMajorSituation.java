package courseselectionsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/5
 * 院校学科情况
 */

@Data
public class CollegeMajorSituation {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 地域
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 院校名称
     */
    private String college;

    /**
     * 学科名称
     */
    private String major;

    /**
     * 学科评估结果
     */
    private String majorLevel;

    /**
     *
     */
    private String isFirstMajor;

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
