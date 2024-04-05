package courseselectionsystem.entity.vo;

import courseselectionsystem.entity.ChooseCollege;
import lombok.Data;

import java.util.List;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/5
 * 定好科目选学校
 */
@Data
public class ChooseCollegeVO {

    /**
     * 定好科目选学校的列表
     */
    private List<ChooseCollege> list;

    /**
     * 定好科目选学校的总数
     */
    private Integer total;

}
