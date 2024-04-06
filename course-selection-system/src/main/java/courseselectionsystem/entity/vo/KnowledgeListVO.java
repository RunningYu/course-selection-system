package courseselectionsystem.entity.vo;

import courseselectionsystem.entity.Knowledge;
import lombok.Data;

import java.util.List;
import java.util.PrimitiveIterator;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/6
 */
@Data
public class KnowledgeListVO {

    private List<Knowledge> list;

    private Integer total;
}
