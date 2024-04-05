package courseselectionsystem.dao;

import courseselectionsystem.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/5
 */
@Mapper
public interface ChooseDao {

    @Select("select * from tb_choose_subject where terms = #{term} and degree = #{degree}")
    Condition chooseCondition(String term, String degree);

    @Select("select * from tb_choose_subject_info where terms_id = #{id}")
    List<RequestInfo> chooseRequestInfolist(Integer id);

    @Select("select * from tb_choose_college_major where place = #{place} and degree = #{degree}")
    List<ChooseCollegeMajor> chooseCollegeMajorBySubjects(String place, String degree);

    @Select("select * from tb_choose_college where place = #{place} and subjects = #{subjects} limit #{startIndex}, #{size}")
    List<ChooseCollege> chooseCollegeBySubjects(String place, String subjects, int startIndex, int size);

    @Select("select count(*) from tb_choose_college where place = #{place} and subjects = #{subjects}")
    int getTotalOfChooseCollegeBySubjects(String place, String subjects);

    @Select("select * from tb_choose_major where subjects = #{subjects} and college = #{college} limit #{startIndex}, #{size}")
    List<ChooseMajor> chooseMajorBySubjects(String place, String subjects, String college, int startIndex, int size);

    @Select("select count(*) from tb_choose_major where subjects = #{subjects} and college = #{college}")
    int getTotalOfChooseMajorBySubjects(String place, String subjects, String college);

    @Select("select * from tb_course_selection_report where subjects = #{subjects}")
    Report mySubjectsReport(String subjects);

    @Select("select * from tb_course_selection_report_form where report_id = #{reportId} and data_kind = #{dataKind}")
    List<ReportForm> getMySubjectsReportFrom(int reportId, int dataKind);
}
