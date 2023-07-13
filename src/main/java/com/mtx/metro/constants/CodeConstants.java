package com.mtx.metro.constants;

public interface CodeConstants {
    //定义状态码
    String CODE_SUCCESS = "200";//成功OK
    String CODE_PERMISSION_ERROR = "300";//权限不足Unauthorized
    String CODE_PARAMETER_ERROR = "400";//参数错误BAD_REQUEST
    String CODE_FORBIDDEN_ERROR = "403";//拒绝请求FORBIDDEN
    String CODE_SYSTEM_ERROR = "500";//系统错误INTERNAL_SERVER_ERROR
    String CODE_SERVICE_ERROR = "600";//业务异常
}
