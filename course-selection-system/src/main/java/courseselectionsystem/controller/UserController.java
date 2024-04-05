package courseselectionsystem.controller;

import courseselectionsystem.entity.User;
import courseselectionsystem.entity.UserRequest;
import courseselectionsystem.service.UserService;
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
 * @date : 2024/4/4
 */
@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册 or 登录
     * @return
     */
    @PostMapping("/user/login")
    public JsonResult login(@RequestBody UserRequest user) {
        log.info("UserController login user:[{}]", user);
        JsonResult response = userService.login(user);

        return response;
    }

    @GetMapping("/user/info")
    public JsonResult userInfo(@Param("number") String number) {
        log.info("UserController userInfo number:[{}]", number);
        JsonResult response = userService.userInfo(number);

        return response;
    }

    @PostMapping("/user/info/modify")
    public JsonResult userInfoModify(@RequestBody UserRequest user) {
        log.info("UserController userInfoModify user:[{}]", user);
        userService.userInfoModify(user);

        return JsonResult.success();
    }





}
