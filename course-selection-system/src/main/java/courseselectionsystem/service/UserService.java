package courseselectionsystem.service;

import courseselectionsystem.entity.UserRequest;
import courseselectionsystem.utils.JsonResult;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/4
 */
public interface UserService {
    JsonResult login(UserRequest user);

    void userInfoModify(UserRequest user);

    JsonResult userInfo(String number);
}
