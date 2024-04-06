package courseselectionsystem.controller;

import courseselectionsystem.entity.User;
import courseselectionsystem.service.ChooseService;
import courseselectionsystem.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
     * 模拟选课
     * @param request
     * @return
     */
    @PostMapping("/subjects/mock/select")
    public JsonResult mockSubjectsSelect(@RequestBody User request) {
        log.info("UserController mockSubjectSelect request:[{}]", request);
        JsonResult response = chooseService.mockSubjectsSelect(request);

        return response;
    }

    /**
     * 真实选科
     * @param request
     * @return
     */
    @PostMapping("/subjects/really/select")
    public JsonResult reallySubjectsSelect(@RequestBody User request) {
        log.info("UserController reallySubjectsSelect request:[{}]", request);
        JsonResult response = chooseService.reallySubjectsSelect(request);

        return response;
    }

    /**
     * 选定专业/院校来选科
     * @param term
     * @param degree
     * @return
     */
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

    /**
     * 院校学科情况
     * @param college 院校名称
     * @param place 地区
     * @param page 第几页
     * @param size 一页的大小
     * @return
     */
    @GetMapping("/college/major/situation")
    public JsonResult CollegeMajorSituation(@Param("college") String college, @Param("place") String place,
                                            @Param("page") int page, @Param("size") int size) {
        log.info("UserController mySubjectsReport college:[{}], place:[{}], page:[{}], size:[{}]", college, place, page, size);
        JsonResult response = chooseService.collegeMajorSituation(college, place, page, size);

        return response;
    }

    /**
     * 专业解析
     */
    @GetMapping("/major/list")
    public JsonResult majorInfoList(@Param("kind") String kind, @Param("page") int page, @Param("size") int size) {
        log.info("UserController majorInfoList kind:[{}], page:[{}], size:[{}]", kind, page, size);
        JsonResult response = chooseService.majorInfoList(kind, page, size);

        return response;
    }

    /**
     * 专业解析详情
     * @return
     */
    @GetMapping("/major/info")
    public JsonResult majorInfo(@Param("id") int id) {
        log.info("UserController majorInfo id:[{}]", id);
        JsonResult response = chooseService.majorInfo(id);

        return response;
    }


    /**
     * todo:排课表
     */



}
