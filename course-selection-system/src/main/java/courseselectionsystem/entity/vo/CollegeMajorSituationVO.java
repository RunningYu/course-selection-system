package courseselectionsystem.entity.vo;

import courseselectionsystem.entity.CollegeMajorSituation;
import lombok.Data;

import java.util.List;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/6
 * 学科情况响应体
 */
@Data
public class CollegeMajorSituationVO {

    private List<CollegeMajorSituation> list;

    private Integer total;
}
