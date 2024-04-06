package courseselectionsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/6
 */
@Data
public class Major {

    /**
     * 创建时间
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 专业分类
     */
    private String kind;

    /**
     * 专业名称
     */
    private String title;

    /**
     * 封面图片url
     */
    private String image;

    /**
     * 专业解析
     */
    private String analysis;

    /**
     * 专业与就业
     */
    private String professionEmployment;

    /**
     * 全国普通高校毕业生规模
     */
    private String graduatesAmount;

    /**
     * 男生比例
     */
    private String maleRate;

    /**
     * 女生比例
     */
    private String femaleRate;

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
