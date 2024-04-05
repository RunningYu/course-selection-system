package courseselectionsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.imageio.stream.IIOByteBuffer;
import java.util.Date;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/5
 * 定好科目选学校/专业
 */
@Data
public class ChooseCollegeMajor {
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
     * 本科、专科
     */
    private String degree;

    /**
     * 结果概述
     */
    private String result;

    /**
     * 科目组合
     */
    private String subjects;

    /**
     * 可报院校数
     */
    private Integer collegeAmount;

    /**
     * 可报专业数
     */
    private Integer majorAmount;

    /**
     * 专业覆盖率
     */
    private String majorRate;

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
