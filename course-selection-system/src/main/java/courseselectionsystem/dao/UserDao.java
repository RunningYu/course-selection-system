package courseselectionsystem.dao;

import courseselectionsystem.entity.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Insert("insert into tb_college_major_situation(province, city, college, major, major_level, is_first_major) " +
            "values (#{province}, #{city}, #{college}, #{major}, #{majorLevel}, #{isFirstMajor})")
    void insertCollegeMajorSituation(CollegeMajorSituation majorSituation);

    @Insert("insert into tb_choose_college_major(place, degree, result, subjects, college_amount, major_amount, major_rate) " +
            "values(#{place}, #{degree}, #{result}, #{subjects}, #{collegeAmount}, #{majorAmount}, #{majorRate})")
    void insertChooseCollegeMajor(ChooseCollegeMajor chooseCollegeMajor);

    @Insert("insert into tb_choose_college(place, subjects, province, city, degree, college, major_amount) " +
            "values (#{place}, #{subjects}, #{province}, #{city}, #{degree}, #{college}, #{majorAmount})")
    void insertChooseCollege(ChooseCollege chooseCollege);

    @Insert("insert into tb_choose_major(place, college, subjects, major, contains_major, direction, subjects_request) " +
            "values (#{place}, #{college}, #{subjects}, #{major}, #{containsMajor}, #{direction}, #{subjectsRequest})")
    void insertChooseMajor(ChooseMajor chooseMajor);

    @Select("select * from tb_user where number = #{number}")
    User getUserByNumber(String number);

    @Insert("insert into tb_user(number, password, username, headshot) values(#{number}, #{password}, #{username}, #{headshot})")
    void addUser(UserRequest user);

    @Update("update tb_user set username = #{username}, headshot = #{headshot}, school = #{school} where number = #{number}")
    void userInfoModify(UserRequest user);
}
