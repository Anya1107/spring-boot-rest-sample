package by.feedblog.api.interceptor;

import by.feedblog.api.entity.Bookmark;
import by.feedblog.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Value("${user.authorization}")
    private String isEnabled;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (Boolean.parseBoolean(isEnabled)){
            String header = request.getHeader("X-Blog-Token");
//            if (userService.validToken(header)) {
//                return true;
//            }
            response.sendError(401);
            return false;
        } else {
            return true;
        }
    }
}
