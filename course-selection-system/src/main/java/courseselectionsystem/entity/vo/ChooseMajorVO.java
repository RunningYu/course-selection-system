package courseselectionsystem.entity.vo;

 import courseselectionsystem.entity.ChooseMajor;
import lombok.Data;

import java.util.List;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/5
 * 选专业
 */
@Data
public class ChooseMajorVO {

    /**
     * 选专业的列表
     */
    private List<ChooseMajor> list;

    /**
     * 选专业的总数
     */
    private Integer total;

}
