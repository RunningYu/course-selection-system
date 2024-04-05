package courseselectionsystem.entity.vo;

import courseselectionsystem.entity.Condition;
import courseselectionsystem.entity.RequestInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/5
 */
@Data
public class ChooseSubjectVO {

    private Condition condition;

    private List<RequestInfo> list;

}
