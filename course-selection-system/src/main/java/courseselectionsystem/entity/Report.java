package courseselectionsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 选课报告
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/4
 */
@Data
public class Report {

    /**
     * 创建时间
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 所选科目组
     */
    private String subjects;

    /**
     * 报告
     */
    private String report;

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
