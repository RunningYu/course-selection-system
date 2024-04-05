package courseselectionsystem.service;

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
}
