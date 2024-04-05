package courseselectionsystem.entity.vo;

import courseselectionsystem.entity.ChooseCollegeMajor;
import lombok.Data;

import java.util.List;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/5
 * 定好科目选学校/专业
 */
@Data
public class ChooseCollegeMajorVO {

    /**
     * 所在地区
     */
    private String place;

    /**
     * 结果概述
     */
    private String result;

    /**
     * 物理类结果
     */
    private List<ChooseCollegeMajor> wuLi;

    /**
     * 历史类结果
     */
    private List<ChooseCollegeMajor> liShiKind;

}
