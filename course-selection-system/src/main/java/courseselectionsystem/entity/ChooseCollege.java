package courseselectionsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/5
 * 定好科目选学校
 */
@Data
public class ChooseCollege {
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
     * 科目组合
     */
    private String subjects;

    /**
     * 地域（省）
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 本科、专科
     */
    private String degree;

    /**
     * 院校名称
     */
    private String college;

    /**
     * 可报专业数
     */
    private Integer majorAmount;

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
