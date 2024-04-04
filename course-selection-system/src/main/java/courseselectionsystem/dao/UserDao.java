package courseselectionsystem.dao;

import courseselectionsystem.entity.Condition;
import courseselectionsystem.entity.ReportForm;
import courseselectionsystem.entity.RequestInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/4
 */
@Mapper
public interface UserDao {

    @Insert("insert into tb_choose_subject(terms, kind, degree, overview_result) values (#{terms}, #{kind}, #{degree}, #{overviewResult})")
    void insertMajor(Condition condition);

    @Insert("insert into tb_choose_subject_info(id, terms_id, subject_request, rate ) values (#{id}, #{termsId}, #{subjectRequest}, #{rate})")
    void insertRequestInfo(RequestInfo requestInfo);

    @Select("select max(id) from tb_choose_subject")
    Integer getMaxId();

    @Insert("insert into tb_course_selection_report_form(report_id, data_kind, place, degree, college_amount, college_able_amount, major_amount, major_able_amount) " +
            "values (#{reportId}, #{dataKind}, #{place}, #{degree}, #{collegeAmount}, #{collegeAbleAmount}, #{majorAmount}, #{majorAbleAmount})")
    void insetReportForm(ReportForm form);
}
