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
        fileUtils.ReadExcel();
    }

//    public static void main(String[] args) {
//        // Excel文件路径
//        String excelFilePath = "path/to/your/excel/file.xlsx";
//
//        try {
//            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
//
//            // 创建工作簿对象
//            Workbook workbook = new XSSFWorkbook(inputStream);
//
//            // 获取第一个工作表
//            Sheet sheet = workbook.getSheetAt(0);
//
//            // 迭代工作表的每一行
//            for (Row row : sheet) {
//                // 迭代行的每一个单元格
//                for (Cell cell : row) {
//                    // 根据单元格类型读取数据
//                    switch (cell.getCellTypeEnum()) {
//                        case STRING:
//                            System.out.print(cell.getStringCellValue() + "\t");
//                            break;
//                        case NUMERIC:
//                            System.out.print(cell.getNumericCellValue() + "\t");
//                            break;
//                        case BOOLEAN:
//                            System.out.print(cell.getBooleanCellValue() + "\t");
//                            break;
//                        case BLANK:
//                            System.out.print("[BLANK]\t");
//                            break;
//                        default:
//                            System.out.print("[UNKNOWN]\t");
//                            break;
//                    }
//                }
//                System.out.println();
//            }
//
//            // 关闭输入流
//            inputStream.close();
//            // 关闭工作簿
//            workbook.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
