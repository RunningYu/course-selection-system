package courseselectionsystem.service;

import courseselectionsystem.entity.UserRequest;
import courseselectionsystem.entity.vo.MockSubjectsVO;
import courseselectionsystem.utils.JsonResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/4
 */
public interface UserService {
    JsonResult login(UserRequest user);

    void userInfoModify(UserRequest user);

    JsonResult userInfo(String number);

    JsonResult knowledgeShare(String file, String fileName, String author, String subject);

    JsonResult knowledgeList(String subject, int page, int page1);

    JsonResult knowledgeDelete(String number, int id);
}
