package courseselectionsystem.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import courseselectionsystem.Mapping.ConvertMapping;
import courseselectionsystem.dao.UserDao;
import courseselectionsystem.entity.User;
import courseselectionsystem.entity.UserRequest;
import courseselectionsystem.entity.UserResponse;
import courseselectionsystem.service.UserService;
import courseselectionsystem.utils.JsonResult;
import courseselectionsystem.utils.JwtUtils;
import courseselectionsystem.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/4
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

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
}
