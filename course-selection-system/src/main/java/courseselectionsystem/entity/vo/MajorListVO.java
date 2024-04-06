package courseselectionsystem.entity.vo;

import courseselectionsystem.entity.Major;
import lombok.Data;

import java.util.List;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/6
 */
@Data
public class MajorListVO {

    private List<Major> list;

    private Integer total;
}
