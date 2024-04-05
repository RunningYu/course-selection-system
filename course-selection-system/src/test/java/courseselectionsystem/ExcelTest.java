package courseselectionsystem;

import courseselectionsystem.utils.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/4
 */
@SpringBootTest
public class ExcelTest {

    @Autowired
    private FileUtils fileUtils;

//    @Transactional
    @Test
    public void test1() throws Exception {
//        FileUtils.ReadExcel();
//        fileUtils.readTxt();
//        fileUtils.ReadExcel();
//        fileUtils.readCollegeMajorSituationExcel();
//        fileUtils.readChooseCollegeMajorTxt();
//        fileUtils.readChooseCollegeExcel();
        fileUtils.readChooseMajorExcel();
    }


}
