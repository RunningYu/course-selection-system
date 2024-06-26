package courseselectionsystem.utils;

import courseselectionsystem.dao.UserDao;
import courseselectionsystem.entity.*;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/4
 */

@Component
public class FileUtils {

    @Autowired
    private UserDao userDao;

    public void ReadExcel() {
        String url = "C://Users//其然乐衣Letitbe//Desktop//报告表数据.xlsx";

        try {
            //创建工作簿对象
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(url));
            //获取工作簿下sheet的个数
            int sheetNum = xssfWorkbook.getNumberOfSheets();
            System.out.println("该excel文件中总共有："+sheetNum+"个sheet");
            //遍历工作簿中的所有数据
            for(int i = 0;i<sheetNum;i++) {
                //读取第i个工作表
                System.out.println("读取第"+(i+1)+"个sheet");
                XSSFSheet sheet = xssfWorkbook.getSheetAt(i);
                //获取最后一行的num，即总行数。此处从0开始
                int maxRow = sheet.getLastRowNum();
                int n = 0;
                for (int row = 0; row <= maxRow; row++) {
                    //获取最后单元格num，即总单元格数 ***注意：此处从1开始计数***
                    int maxRol = sheet.getRow(row).getLastCellNum();
                    System.out.println("--------第" + row + "行的数据如下--------");
                    for (int rol = 0; rol < maxRol; rol++){
                        System.out.print(sheet.getRow(row).getCell(rol) + "  ");
                    }
                    System.out.println();
                    String s = String.valueOf(sheet.getRow(row).getCell(0));
                    if (s.contains("本科情况-各省（市、区）招生情况统计")) {
                        n = 1;
                        row ++;
                        continue;
                    } else if (s.contains("专科情况-各省（市、区）招生情况统计")) {
                        n = 2;
                        row ++;
                        continue;
                    } else if (s.contains("本、专科招生情况统计")) {
                        n = 3;
                        row ++;
                        continue;
                    } else if (s.contains("相关院校招生情况统计")) {
                        n = 4;
                        row ++;
                        continue;
                    }
                    ReportForm form = new ReportForm();
                    form.setReportId(12);
                    form.setPlace(String.valueOf(sheet.getRow(row).getCell(0)));
                    form.setDegree(String.valueOf(sheet.getRow(row).getCell(1)));
                    double d = Double.parseDouble(sheet.getRow(row).getCell(2) + "");
                    form.setCollegeAmount((int) d);
                    form.setCollegeAbleAmount(String.valueOf(sheet.getRow(row).getCell(3)));
                    d = Double.parseDouble(sheet.getRow(row).getCell(4) + "");
                    form.setMajorAmount((int) d);
                    form.setMajorAbleAmount(String.valueOf(sheet.getRow(row).getCell(5)));
                    if (n == 1) {
                        form.setDataKind(1);
                    } else if (n == 2) {
                        form.setDataKind(2);
                    } else if (n == 3) {
                        form.setDataKind(3);
                    } else if (n == 4) {
                        form.setDataKind(4);
                    }
                    userDao.insetReportForm(form);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取院校条件数据导入数据库
     */
    public void readTxt() {
//        String url = "C:\\Users\\其然乐衣Letitbe\\Desktop\\教育系统数据.txt";
        // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
//        String pathname = "C:\\Users\\其然乐衣Letitbe\\Desktop\\教育系统数据.txt";
        String pathname = "C:\\Users\\其然乐衣Letitbe\\Desktop\\院校条件数据.txt";
        //防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw;
        //不关闭文件会导致资源的泄露，读写文件都同理
        //Java7的try-with-resources可以优雅关闭文件，异常时自动关闭文件；详细解读https://stackoverflow.com/a/12665271
        try (FileReader reader = new FileReader(pathname);
             // 建立一个对象，它把文件内容转成计算机能读懂的语言
             BufferedReader br = new BufferedReader(reader)
        ) {
            String line;
            //网友推荐更加简洁的写法
            boolean flag = false;
            int n = 0;
            int conditionId = 0;
            RequestInfo requestInfo = null;
            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
                System.out.println(line);
                if (line.equals("--")) {
                    requestInfo = null;
                    flag = true;
                    n = 0;
                    continue;
                }
                n ++;
                if (n == 1) {
                    String[] datas = line.split(" ");
                    Condition condition = new Condition();
                    condition.setTerms(datas[0]);
                    condition.setDegree(datas[1]);
                    line = br.readLine();
                    if (line != null) {
                        condition.setOverviewResult(line);
                    }
                    condition.setKind(2);
                    System.out.println("------------------");
                    System.out.println(condition);
                    System.out.println("------------------");
                    userDao.insertMajor(condition);
                    Integer id = userDao.getMaxId();
                    conditionId = id;
                    n ++;

                    continue;
                }
                requestInfo = new RequestInfo();
                requestInfo.setTermsId(conditionId);
                requestInfo.setSubjectRequest(line);
                line = br.readLine();
                if (line != null) {
                    requestInfo.setRate(line);
                }
                userDao.insertRequestInfo(requestInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取 院校学科情况 数据导入数据库
     */
    public void readCollegeMajorSituationExcel() {
        String url = "C://Users//其然乐衣Letitbe//Desktop//院校学科情况.xlsx";

        try {
            //创建工作簿对象
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(url));
            //获取工作簿下sheet的个数
            int sheetNum = xssfWorkbook.getNumberOfSheets();
            System.out.println("该excel文件中总共有："+sheetNum+"个sheet");
            //遍历工作簿中的所有数据
            for(int i = 0;i<sheetNum;i++) {
                //读取第i个工作表
                System.out.println("读取第"+(i+1)+"个sheet");
                XSSFSheet sheet = xssfWorkbook.getSheetAt(i);
                //获取最后一行的num，即总行数。此处从0开始
                int maxRow = sheet.getLastRowNum();
                int n = 0;
                for (int row = 0; row <= maxRow; row++) {
                    //获取最后单元格num，即总单元格数 ***注意：此处从1开始计数***
                    int maxRol = sheet.getRow(row).getLastCellNum();
                    System.out.println("---------------------" + row + "---------------------------");
                    for (int rol = 0; rol < maxRol; rol++){
                        System.out.print(sheet.getRow(row).getCell(rol) + "  ");
                    }
                    System.out.println();
                    if (row == 0) {
                        continue;
                    }
                    CollegeMajorSituation majorSituation = new CollegeMajorSituation();
                    majorSituation.setProvince(String.valueOf(sheet.getRow(row).getCell(0)));
                    majorSituation.setCity(String.valueOf(sheet.getRow(row).getCell(1)));
                    majorSituation.setCollege(String.valueOf(sheet.getRow(row).getCell(2)));
                    majorSituation.setMajor(String.valueOf(sheet.getRow(row).getCell(3)));
                    majorSituation.setMajorLevel(String.valueOf(sheet.getRow(row).getCell(4)));
                    majorSituation.setIsFirstMajor(String.valueOf(sheet.getRow(row).getCell(5)));
                    userDao.insertCollegeMajorSituation(majorSituation);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readChooseCollegeMajorTxt() {
        String pathname = "C:\\Users\\其然乐衣Letitbe\\Desktop\\定好科目挑学校或专业.txt";
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader)
        ) {
            String line;
            String place = "";
            String degree = "";
            String result = "";
            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
                System.out.println(line);
                if (line.equals("--")) {
                    line = br.readLine();
                    String[] datas = line.split(" ");
                    System.out.println(line);
                    place = datas[0];
                    degree = datas[1];
                    result = datas[2];

                    continue;
                }
                ChooseCollegeMajor chooseCollegeMajor = new ChooseCollegeMajor();
                chooseCollegeMajor.setPlace(place);
                chooseCollegeMajor.setDegree(degree);
                chooseCollegeMajor.setResult(result);

                chooseCollegeMajor.setSubjects(line);
                line = br.readLine();
                System.out.println(line);
                chooseCollegeMajor.setCollegeAmount(Integer.valueOf(line));
                line = br.readLine();
                System.out.println(line);
                chooseCollegeMajor.setMajorAmount(Integer.valueOf(line));
                line = br.readLine();
                System.out.println(line);
                chooseCollegeMajor.setMajorRate(line);

                userDao.insertChooseCollegeMajor(chooseCollegeMajor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readChooseCollegeExcel() {
        String url = "C://Users//其然乐衣Letitbe//Desktop//定好科目挑学校.xlsx";

        try {
            //创建工作簿对象
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(url));
            //获取工作簿下sheet的个数
            int sheetNum = xssfWorkbook.getNumberOfSheets();
            System.out.println("该excel文件中总共有："+sheetNum+"个sheet");
            //遍历工作簿中的所有数据
            for(int i = 0;i<sheetNum;i++) {
                //读取第i个工作表
                System.out.println("读取第"+(i+1)+"个sheet");
                XSSFSheet sheet = xssfWorkbook.getSheetAt(i);
                //获取最后一行的num，即总行数。此处从0开始
                int maxRow = sheet.getLastRowNum();
                int n = 0;
                for (int row = 0; row <= maxRow; row++) {
                    //获取最后单元格num，即总单元格数 ***注意：此处从1开始计数***
                    int maxRol = sheet.getRow(row).getLastCellNum();
                    System.out.println("---------------------" + row + "---------------------------");
                    for (int rol = 0; rol < maxRol; rol++){
                        System.out.print(sheet.getRow(row).getCell(rol) + "  ");
                    }
                    System.out.println();
                    if (row == 0) {
                        continue;
                    }

                    ChooseCollege chooseCollege = new ChooseCollege();
                    chooseCollege.setPlace("广州市");
                    chooseCollege.setSubjects("史政地");
                    chooseCollege.setProvince(String.valueOf(sheet.getRow(row).getCell(0)));
                    chooseCollege.setCity(String.valueOf(sheet.getRow(row).getCell(1)));
                    chooseCollege.setDegree(String.valueOf(sheet.getRow(row).getCell(2)));
                    chooseCollege.setCollege(String.valueOf(sheet.getRow(row).getCell(3)));
                    double d = Double.parseDouble(sheet.getRow(row).getCell(4) + "");
                    chooseCollege.setMajorAmount((int) d);
                    userDao.insertChooseCollege(chooseCollege);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readChooseMajorExcel() {
        String url = "C://Users//其然乐衣Letitbe//Desktop//定好科目选专业.xlsx";

        try {
            //创建工作簿对象
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(url));
            //获取工作簿下sheet的个数
            int sheetNum = xssfWorkbook.getNumberOfSheets();
            System.out.println("该excel文件中总共有："+sheetNum+"个sheet");
            //遍历工作簿中的所有数据
            for(int i = 0;i<sheetNum;i++) {
                //读取第i个工作表
                System.out.println("读取第"+(i+1)+"个sheet");
                XSSFSheet sheet = xssfWorkbook.getSheetAt(i);
                //获取最后一行的num，即总行数。此处从0开始
                int maxRow = sheet.getLastRowNum();
                int n = 0;
                for (int row = 0; row <= maxRow; row++) {
                    //获取最后单元格num，即总单元格数 ***注意：此处从1开始计数***
                    int maxRol = sheet.getRow(row).getLastCellNum();
                    System.out.println("---------------------" + row + "---------------------------");
                    for (int rol = 0; rol < maxRol; rol++){
                        System.out.print(sheet.getRow(row).getCell(rol) + "  ");
                    }
                    System.out.println();
                    if (row == 0) {
                        continue;
                    }
                    ChooseMajor chooseMajor = new ChooseMajor();
                    chooseMajor.setPlace("茂名市");
                    chooseMajor.setCollege("广东石油化工学院");
                    chooseMajor.setSubjects("史政地");
                    chooseMajor.setMajor(sheet.getRow(row).getCell(1) + "");
                    chooseMajor.setContainsMajor(sheet.getRow(row).getCell(2) + "");
                    chooseMajor.setDirection(sheet.getRow(row).getCell(3) + "");
                    chooseMajor.setSubjectsRequest(sheet.getRow(row).getCell(4) + "");
                    userDao.insertChooseMajor(chooseMajor);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
