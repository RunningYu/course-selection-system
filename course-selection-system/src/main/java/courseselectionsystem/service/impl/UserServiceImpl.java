package courseselectionsystem.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import courseselectionsystem.Mapping.ConvertMapping;
import courseselectionsystem.dao.UserDao;
import courseselectionsystem.entity.*;
import courseselectionsystem.entity.vo.KnowledgeListVO;
import courseselectionsystem.service.UserService;
import courseselectionsystem.utils.JsonResult;
import courseselectionsystem.utils.JwtUtils;
import courseselectionsystem.utils.MD5Util;
import courseselectionsystem.utils.MinioUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/4
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MinioUtil minioUtil;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private Cache<String ,Object> caffeineCache;

    @Autowired
    private UserDao userDao;

    @Override
    public JsonResult login(UserRequest user) {
        log.info("UserServiceImpl login user:[{}]", user);
        // 查询数据库是否存在该用户（有则直接登录，并生成token）
        User havaUser = userDao.getUserByNumber(user.getNumber());
        // 数据库中没有，则表示首次登录，默认密码是学号
        if (havaUser == null) {
            // 判断密码
            if (user.getPassword().equals(user.getNumber())) {
                // 密码正确，则讲用户信息添加入数据库
                String md5Password = MD5Util.encrypt(user.getNumber());
                user.setPassword(md5Password);
                userDao.addUser(user);
            } else {
                return JsonResult.error("用户密码错误！");
            }
        }
        // 数据库中已经有这个用户了，表示不是首次登录，密码不一定是number,可能改过了，所以要用数据库存的密码进行校验
        else {
            String md5Password = MD5Util.encrypt(user.getNumber());
            if (!md5Password.equals(havaUser.getPassword())) {
                return JsonResult.error("用户密码错误！");
            }
        }
        User user1 = new User();
        user1.setNumber(user.getNumber());
        user1.setPassword(user.getPassword());
        user1.setUsername(user.getUsername());
        String token = jwtUtils.createToken(user1);
        caffeineCache.put(token, user);
        UserResponse response = ConvertMapping.userRequest2UserResponse(user);
        response.setToken(token);

        return JsonResult.success("登录成功！", response);
    }

    @Override
    public void userInfoModify(UserRequest user) {
        userDao.userInfoModify(user);
    }

    @Override
    public JsonResult userInfo(String number) {
        User user = userDao.getUserByNumber(number);
        log.info("UserServiceImpl number user:[{}]", user);
        if (user == null) {
            return JsonResult.error("没有改用户");
        }

        return JsonResult.success(user);
    }

    @Override
    public JsonResult knowledgeShare(String file, String fileName, String author, String subject) {
//        UploadResponse fileResponse = minioUtil.uploadFile(file, "file");
//        String fileUrl = fileResponse.getMinIoUrl();
//        log.info("UserServiceImpl knowledgeShare fileUrl:[{}]", fileUrl);
        userDao.knowledgeShare(file, fileName, author, subject);

        return JsonResult.success("知识分享成共");
    }

    @Override
    public JsonResult knowledgeDelete(String number, int id) {
        log.info("UserServiceImpl knowledgeDelete id:[{}], number:[{}]", id, number);
        if (number == null || "".equals(number)) {
            return JsonResult.error(500, "请先登录");
        }
        userDao.knowledgeDelete(number, id);

        return JsonResult.success("删除成功");
    }

    @Override
    public JsonResult knowledgeList(String subject, int page, int size) {
        page = (page > 0 ? page : 1);
        int startIndex = (page - 1) * size;
        List<Knowledge> list = null;
        int total = 0;
        // 如果传了全部，则全部知识查找，否则根据科目来查找
        if (subject.equals("全部")) {
            list = userDao.knowledgeListAll(startIndex, size);
            total = userDao.getTotalKnowledgeAll();
        } else {
            list = userDao.knowledgeListBySubject(subject, startIndex, size);
            total = userDao.getTotalKnowledgeBySubject(subject);
        }
        KnowledgeListVO response = new KnowledgeListVO();
        response.setList(list);
        response.setTotal(total);

        return JsonResult.success(response);
    }
}
