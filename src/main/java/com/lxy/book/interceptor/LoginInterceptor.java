package com.lxy.book.interceptor;

import com.lxy.book.constant.Constants;
import com.lxy.book.model.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LoginInterceptor  implements HandlerInterceptor {

    //true, 表示放行,继续访问目标
    //false, 表示拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("校验用户是否登录" );
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo)session.getAttribute(Constants.USER_SESSION_KEY);
        if(userInfo==null){
            log.warn("用户未登录");
            //用户未登录
           /* return Result.nologin();*/
            response.setStatus(401);
            response.getOutputStream().write("nologin".getBytes());
            return false;
        }
        log.info("校验通过");
        return true;
    }
}
