package courseselectionsystem.controller;

import courseselectionsystem.entity.UserRequest;
import courseselectionsystem.entity.vo.ChooseSubjectVO;
import courseselectionsystem.service.ChooseService;
import courseselectionsystem.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/5
 */
@Slf4j
@RestController
public class ChooseController {

    @Autowired
    private ChooseService chooseService;

    /**
     * todo: 业务待确定
     * @param user
     * @return
     */
    @PostMapping("/mock/subject/select")
    public JsonResult mockSubjectSelect(@RequestBody UserRequest user) {
        log.info("UserController userInfoModify user:[{}]", user);
        //userService.mockSubjectSelect(user);

        return JsonResult.success();
    }

    @GetMapping("/choose/subject")
    public JsonResult chooseSubject(@Param("term") String term, @Param("degree") String degree) {
        log.info("UserController chooseSubjectByMajor term:[{}], degree:[{}]", term, degree);
        JsonResult response = chooseService.chooseSubjectByMajor(term, degree);

        return response;
    }

    /**
     * 定好 科目 挑学校/选专业
     * @param place 所在地区
     * @param degree 本科 or 专科
     * @return
     */
    @GetMapping("/choose/college/major/by/subjects")
    public JsonResult chooseCollegeMajorBySubjects(@Param("place") String place, @Param("degree") String degree) {
        log.info("UserController chooseCollegeMajorBySubjects place:[{}], degree:[{}]", place, degree);
        JsonResult response = chooseService.chooseCollegeMajorBySubjects(place, degree);

        return response;
    }

    /**
     * 定好 科目 挑学校/选专业：挑学校
     * @param subjects 科目组合
     * @param page 第几页
     * @param size 一页大小
     * @return
     */
    @GetMapping("/choose/college/by/subjects")
    public JsonResult chooseCollegeBySubjects(@Param("place") String place, @Param("subjects") String subjects,
                                              @Param("page") int page, @Param("size") int size) {
        log.info("UserController chooseCollegeBySubjects place:[{}], subjects:[{}], page:[{}], size:[{}]", place, subjects, page, size);
        JsonResult response = chooseService.chooseCollegeBySubjects(place, subjects, page, size);

        return response;
    }

    /**
     * 定好 科目 挑学校/选专业：选专业
     * @param subjects 科目组合
     * @param page 第几页
     * @param size 一页大小
     * @return
     */
    @GetMapping("/choose/major/by/subjects")
    public JsonResult chooseMajorBySubjects(@Param("place") String place, @Param("subjects") String subjects, @Param("college") String college,
                                              @Param("page") int page, @Param("size") int size) {
        log.info("UserController chooseMajorBySubjects place:[{}], subjects:[{}], college:[{}], page:[{}], size:[{}]", place, subjects, college, page, size);
        JsonResult response = chooseService.chooseMajorBySubjects(place, subjects, college, page, size);

        return response;
    }

    /**
     * 我的选课报告
     * @param subjects 科目组合
     * @return
     */
    @GetMapping("/my/subjects/report")
    public JsonResult mySubjectsReport(@Param("subjects") String subjects) {
        log.info("UserController mySubjectsReport subjects:[{}]", subjects);
        JsonResult response = chooseService.mySubjectsReport(subjects);

        return response;
    }

}
