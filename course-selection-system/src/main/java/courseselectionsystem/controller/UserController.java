package courseselectionsystem.controller;

import courseselectionsystem.entity.UploadResponse;
import courseselectionsystem.entity.UserRequest;
import courseselectionsystem.service.UserService;
import courseselectionsystem.utils.JsonResult;
import courseselectionsystem.utils.JwtUtils;
import courseselectionsystem.utils.MinioUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/4
 */
@Slf4j
@RestController
public class UserController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private MinioUtil minioUtil;

    /**
     * 图片上传，返回图片链接
     */
    @PostMapping("/upload/file")
    public JsonResult minioUpload(@RequestParam(value = "file") MultipartFile file) {
        UploadResponse response = minioUtil.uploadFile(file, "file");

        return JsonResult.success(response);
    }

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
    public JsonResult userInfoModify(HttpServletRequest httpServletRequest, @RequestBody UserRequest user) {
        log.info("UserController userInfoModify user:[{}]", user);
        String token = httpServletRequest.getHeader("Authorization");
        if (token == null || "".equals(token)) {
            return JsonResult.error("请先登录");
        }
        String number = jwtUtils.getNumberByToken(token);
        if (number == null) {
            return JsonResult.error("Authorization 过期或失效！");
        }
        user.setNumber(number);
        log.info("UserController userInfoModify number:[{}]", number);
        userService.userInfoModify(user);

        return JsonResult.success();
    }

    /**
     * 知识文件分享
     */
    @PostMapping("/knowledge/share")
    public JsonResult knowledgeShare(@RequestParam("file") MultipartFile file,
                                     @RequestParam("fileName") String fileName,
                                     @RequestParam("author") String author,
                                     @RequestParam("subject") String subject) {
        log.info("UserController knowledgeShare file:[{}], fileName:[{}], author:[{}], subject:[{}]", file, fileName, author, subject);
        JsonResult response = userService.knowledgeShare(file, fileName, author, subject);

        return response;
    }

    /**
     * 知识文件删除
     */
    @GetMapping("/knowledge/delete")
    public JsonResult knowledgeDelete(HttpServletRequest httpServletRequest, @RequestParam("id") int id) {
        String token = httpServletRequest.getHeader("Authorization");
        log.info("UserController knowledgeDelete id:[{}], token:[{}]", id, token);
        String number = jwtUtils.getNumberByToken(token);
        JsonResult response = userService.knowledgeDelete(number, id);

        return response;
    }

    /**
     * 知识文件列表（分类）
     */
    @GetMapping("/knowledge/list")
    public JsonResult knowledgeList(@Param("subject") String subject, @Param("page") int page, @Param("size") int size) {
        log.info("UserController knowledgeShare subject:[{}], page:[{}], page:[{}]", subject, page, size);
        JsonResult response = userService.knowledgeList(subject, page, size);

        return response;
    }


}
