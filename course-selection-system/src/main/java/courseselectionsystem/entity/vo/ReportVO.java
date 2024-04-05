package courseselectionsystem.entity.vo;

import courseselectionsystem.entity.Report;
import courseselectionsystem.entity.ReportForm;
import lombok.Data;

import java.util.List;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/5
 * 我的选课报告响应体
 */
@Data
public class ReportVO {

    /**
     * 文字分析报告
     */
    private Report report;

    /**
     * 1-本科情况-各省（市、区）招生情况统计
     */
    private List<ReportForm> benKeProvinceInfo;

    /**
     * 2-专科情况-各省（市、区）招生情况统计
     */
    private List<ReportForm> zhuanKeProvinceInfo;

    /**
     * 3-本、专科招生情况统计
     */
    private List<ReportForm> benZhuanInfo;

    /**
     * 4-相关院校招生情况统计
     */
    private List<ReportForm> collegeInfo;

}
