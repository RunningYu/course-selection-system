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
public class Knowledge {

    /**
     * 创建时间
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 知识文件名
     */
    private String fileName;

    /**
     * 知识文件url
     */
    private String fileUrl;

    /**
     * 作者
     */
    private String author;

    /**
     * 科目
     */
    private String subject;

    /**
     * 点赞量
     */
    private Integer likeAmount;

    /**
     * 0-未删除 1-已删除
     */
    private Integer isDelete;

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
