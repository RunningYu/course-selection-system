package courseselectionsystem.entity;

import lombok.Data;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/5
 * 用户注册登录时的请求体
 */
@Data
public class UserRequest {

    /**
     * 学号或职工号
     */
    private String number;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户名
     */
    private String username;

    /**
     * 头像url
     */
    private String headshot;

    /**
     * 学校
     */
    private String school;

}
