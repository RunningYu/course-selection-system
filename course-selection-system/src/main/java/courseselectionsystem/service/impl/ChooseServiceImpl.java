package courseselectionsystem.service.impl;

import courseselectionsystem.dao.ChooseDao;
import courseselectionsystem.dao.UserDao;
import courseselectionsystem.entity.*;
import courseselectionsystem.entity.vo.*;
import courseselectionsystem.service.ChooseService;
import courseselectionsystem.service.UserService;
import courseselectionsystem.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/5
 */
@Slf4j
@Service
public class ChooseServiceImpl implements ChooseService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ChooseDao chooseDao;

    @Override
    public JsonResult chooseSubjectByMajor(String term, String degree) {
        Condition condition = chooseDao.chooseCondition(term, degree);
        // 如果为空，怎表示暂时还没录入该专业的数据
        if (condition == null) {
            return JsonResult.error("暂无该数据结果");
        }
        List<RequestInfo> list = chooseDao.chooseRequestInfolist(condition.getId());
        ChooseSubjectVO response = new ChooseSubjectVO();
        response.setCondition(condition);
        response.setList(list);
        log.info("ChooseServiceImpl chooseSubjectByMajor response:[{}]", response);

        return JsonResult.success(response);
    }

    @Override
    public JsonResult chooseCollegeMajorBySubjects(String place, String degree) {
        List<ChooseCollegeMajor> list = chooseDao.chooseCollegeMajorBySubjects(place, degree);
        if (list == null || list.size() == 0) {
            return JsonResult.error("暂无该数据结果");
        }
        List<ChooseCollegeMajor> wuLiList = new ArrayList<>();
        List<ChooseCollegeMajor> liShiList = new ArrayList<>();
        for (ChooseCollegeMajor collegeMajor : list) {
            if (collegeMajor.getSubjects().contains("物")) {
                wuLiList.add(collegeMajor);
            } else {
                liShiList.add(collegeMajor);
            }
        }
        ChooseCollegeMajorVO response = new ChooseCollegeMajorVO();
        response.setPlace(place);
        response.setResult(list.get(0).getResult());
        response.setWuLi(wuLiList);
        response.setLiShiKind(liShiList);
        log.info("ChooseServiceImpl chooseCollegeMajorBySubjects response:[{}]", response);

        return JsonResult.success(response);
    }

    @Override
    public JsonResult chooseCollegeBySubjects(String place, String subjects, int page, int size) {
        page = (page > 0) ? page : 1;
        // 计算数据库表中查询的初始位置
        int startIndex = (page - 1) * size;
        List<ChooseCollege> list = chooseDao.chooseCollegeBySubjects(place, subjects, startIndex, size);
        if (list == null || list.size() == 0) {
            return JsonResult.error("暂无该数据结果");
        }
        int total = chooseDao.getTotalOfChooseCollegeBySubjects(place, subjects);
        ChooseCollegeVO response = new ChooseCollegeVO();
        response.setList(list);
        response.setTotal(total);
        log.info("ChooseServiceImpl chooseCollegeBySubjects response:[{}]", response);

        return JsonResult.success(response);
    }

    @Override
    public JsonResult chooseMajorBySubjects(String place, String subjects, String college, int page, int size) {
        page = (page > 0) ? page : 1;
        // 计算数据库表中查询的初始位置
        int startIndex = (page - 1) * size;
        List<ChooseMajor> list = chooseDao.chooseMajorBySubjects(place, subjects, college, startIndex, size);
        if (list == null || list.size() == 0) {
            return JsonResult.error("暂无该数据结果");
        }
        int total = chooseDao.getTotalOfChooseMajorBySubjects(place, subjects, college);
        ChooseMajorVO response = new ChooseMajorVO();
        response.setList(list);
        response.setTotal(total);
        log.info("ChooseServiceImpl chooseMajorBySubjects response:[{}]", response);

        return JsonResult.success(response);
    }

    @Override
    public JsonResult mySubjectsReport(String subjects) {
        String[] s = subjects.split(" ");
        subjects = s[0] + "+" + s[1] + "+" + s[2];
        Report report = chooseDao.mySubjectsReport(subjects);
        if (report == null) {
            return JsonResult.error("暂无该数据结果");
        }
        /*
         * dataKind说明：
         * 1-本科情况-各省（市、区）招生情况统计
         * 2-专科情况-各省（市、区）招生情况统计
         * 3-本、专科招生情况统计
         * 4-相关院校招生情况统计
         */
        List<ReportForm> list1 = chooseDao.getMySubjectsReportFrom(report.getId(), 1);
        List<ReportForm> list2 = chooseDao.getMySubjectsReportFrom(report.getId(), 2);
        List<ReportForm> list3 = chooseDao.getMySubjectsReportFrom(report.getId(), 3);
        List<ReportForm> list4 = chooseDao.getMySubjectsReportFrom(report.getId(), 4);
        ReportVO response = new ReportVO();
        response.setReport(report);
        response.setBenKeProvinceInfo(list1);
        response.setZhuanKeProvinceInfo(list2);
        response.setBenZhuanInfo(list3);
        response.setCollegeInfo(list4);
        log.info("ChooseServiceImpl mySubjectsReport response:[{}]", response);

        return JsonResult.success(response);
    }

    @Override
    public JsonResult mockSubjectsSelect(User request) {
        userDao.mockSubjectsSelect(request);

        return JsonResult.success("模拟选课成功");
    }

    @Override
    public JsonResult reallySubjectsSelect(User request) {
        User user = userDao.getUserByNumber(request.getNumber());
        if (user.getFirstSubject() != null && !user.getFirstSubject().equals("")) {
            // 如果该学生已经选好科了的，则不能再次修改
            return JsonResult.error("该学生已经选好了科，不能再次修改!");
        }
        // 统计相同选课组合的人数
        int studentAmount = userDao.countStudentAmountOfSameSubjects(request) + 1;
        // 安排班别，默认50人一班
        int classNumber = 0;
        if (studentAmount % 50 == 0) {
            classNumber = studentAmount / 50;
        } else {
            classNumber = studentAmount / 50 + 1;
        }
        String clas = classNumber + "班";
        request.setClas(clas);
        userDao.reallySubjectsSelect(request);

        return JsonResult.success("选课成功");
    }

    @Override
    public JsonResult collegeMajorSituation(String college, String place, int page, int size) {
        page = (page > 0) ? page : 1;
        // 计算数据库表中查询的初始位置
        int startIndex = (page - 1) * size;
        List<CollegeMajorSituation> list = new ArrayList<>();
        int total = 0;
        // 如果院校名称college不为空，则根据大学名称来搜索
        if (college != null && !college.equals("")) {
            list = chooseDao.collegeMajorSituationListByCollege(college, startIndex, size);
            total = chooseDao.getTotalOfCollegeMajorSituationByCollege(college);
        }
        // 如果地区place不为空，则根据地区来搜索
        else if (place != null && !place.equals("")){
            list = chooseDao.collegeMajorSituationListByPlace(place, startIndex, size);
            total = chooseDao.getTotalOfCollegeMajorSituationByPlace(place);
        }
        // 如果college和place都为空，则全部都查询
        else {
            list = chooseDao.collegeMajorSituationList(startIndex, size);
            total = chooseDao.getTotalOfCollegeMajorSituation();
        }
        CollegeMajorSituationVO response = new CollegeMajorSituationVO();
        response.setList(list);
        response.setTotal(total);

        return JsonResult.success(response);
    }
}
