package courseselectionsystem.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author：chenzhenyu
 * @Date：2024/1/4 10:32
 */
/**
 * 通用响应体
 */
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@Data
public class JsonResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 结果状态
     */
    private Integer code = 200;

    /**
     * 结果数据
     */
    private T data;

    /**
     * 结果描述
     */
    private String message = "ok";

    public JsonResult() {
    }

    public JsonResult(Integer code) {
        this();
        this.code = code;
    }

    public JsonResult(Integer code, T data) {
        this(code);
        this.data = data;
    }

    public JsonResult(Integer code, String message) {
        this(code);
        this.message = message;
    }

    public JsonResult(Integer code, String message, T data) {
        this(code, message);
        this.data = data;
    }

    public static <T> JsonResult<T> success() {
        return new JsonResult(200);
    }

    public static <T> JsonResult<T> success(T data) {
        return new JsonResult(200, data);
    }

    public static <T> JsonResult<T> success(String message, T data) {
        return new JsonResult(200, message, data);
    }

    public static <T> JsonResult<T> success(Integer code, String message, T data) {
        return new JsonResult(code, message, data);
    }

    public static JsonResult<String> error() {
        return new JsonResult(500);
    }

    public static <T> JsonResult<T> error(String message) {
        return new JsonResult(500, message);
    }

    public static <T> JsonResult<T> error(Integer code, String message) {
        return new JsonResult(code, message);
    }

    public static <T> JsonResult<T> error(Integer code, String message, T data) {
        return new JsonResult(code, message, data);
    }


}
