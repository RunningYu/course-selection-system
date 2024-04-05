package courseselectionsystem.Mapping;

import courseselectionsystem.entity.UserRequest;
import courseselectionsystem.entity.UserResponse;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/5
 */
public class ConvertMapping {

    public static UserResponse userRequest2UserResponse(UserRequest user) {
        if (user == null) {
            return null;
        }
        UserResponse response = new UserResponse();
        response.setNumber(user.getNumber());
        response.setPassword(user.getPassword());
        response.setHeadshot(user.getHeadshot());
        response.setUsername(user.getUsername());
        response.setSchool(user.getSchool());

        return response;
    }
}
