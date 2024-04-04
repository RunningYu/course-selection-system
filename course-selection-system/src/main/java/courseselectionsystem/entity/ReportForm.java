package courseselectionsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 选课报告的表格数据
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/4
 */
@Data
public class ReportForm {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 报告id
     */
    private Integer reportId;

    /**
     * 地区
     */
    private String place;

    /**
     * 本科、专科
     */
    private String degree;

    /**
     * 招生院校
     */
    private String collegeAmount;

    /**
     * 可选报院校数
     */
    private String college_able_amount;


    /**
     * 招生专业总数
     */
    private Integer majorAmount;

    /**
     * 可选报专业数
     */
    private String majorAbleAmount;

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

