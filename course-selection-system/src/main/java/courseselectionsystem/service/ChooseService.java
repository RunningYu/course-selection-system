package courseselectionsystem.service;

import courseselectionsystem.entity.User;
import courseselectionsystem.utils.JsonResult;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/5
 */
public interface ChooseService {
    JsonResult chooseSubjectByMajor(String term, String degree);

    JsonResult chooseCollegeMajorBySubjects(String place, String degree);

    JsonResult chooseCollegeBySubjects(String place, String subjects, int page, int size);

    JsonResult chooseMajorBySubjects(String place, String subjects, String college, int page, int size);

    JsonResult mySubjectsReport(String subjects);

    JsonResult mockSubjectsSelect(User request);

    JsonResult reallySubjectsSelect(User request);

    JsonResult collegeMajorSituation(String college, String place, int page, int size);

    JsonResult majorInfoList(String kind, int page, int size);

    JsonResult majorInfo(int id);
}
