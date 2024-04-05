package courseselectionsystem.service.impl;

import courseselectionsystem.dao.ChooseDao;
import courseselectionsystem.entity.*;
import courseselectionsystem.entity.vo.*;
import courseselectionsystem.service.ChooseService;
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
}
