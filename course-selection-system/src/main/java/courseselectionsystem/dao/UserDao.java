package courseselectionsystem.dao;

import courseselectionsystem.entity.*;
import courseselectionsystem.entity.vo.MockSubjectsVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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

    @Update("update tb_user set mock_subjects = #{mockSubjects} where number = number")
    void mockSubjectsSelect(User request);

    /**
     * 统计相同选课的学生人数
     */
    @Select("select count(*) from tb_user where first_subject = #{firstSubject} and secondary_subjects = #{secondarySubjects}")
    int countStudentAmountOfSameSubjects(User user);

    @Update("update tb_user set clas = #{clas}, first_subject = #{firstSubject}, secondary_subjects = #{secondarySubjects} where number = number")
    void reallySubjectsSelect(User request);

    @Insert("insert into tb_knowledge(file_url, file_name, author, subject) values (#{fileUrl}, #{fileName}, #{author}, #{subject})")
    void knowledgeShare(String fileUrl, String fileName, String author, String subject);

    @Select("select * from tb_knowledge where subject = #{subject} limit #{startIndex}, #{size}")
    List<Knowledge> knowledgeListBySubject(String subject, int startIndex, int size);

    @Select("select count(*) from tb_knowledge where subject = #{subject}")
    int getTotalKnowledgeBySubject(String subject);

    @Select("select * from tb_knowledge limit #{startIndex}, #{size}")
    List<Knowledge> knowledgeListAll(int startIndex, int size);

    @Select("select count(*) from tb_knowledge")
    int getTotalKnowledgeAll();

    @Select("update tb_knowledge set is_delete = 1 where id = #{id} and number = #{number}")
    void knowledgeDelete(String number, int id);
}
