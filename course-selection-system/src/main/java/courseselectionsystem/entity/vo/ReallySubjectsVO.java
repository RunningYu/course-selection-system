package courseselectionsystem.entity.vo;

import lombok.Data;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/6
 * 真正选课
 */
@Data
public class ReallySubjectsVO {

    /**
     * 学号
     */
    private String number;

    /**
     * 物理、历史
     */
    private String firstSubject;

    /**
     * 次要科目，如：化学+生物、化学+地理...
     */
    private String secondarySubjects;
}
