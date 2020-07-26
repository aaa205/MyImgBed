package com.a205.mybed.pictureservice.aspect;

import org.apache.http.HttpStatus;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import util.RestAPIResult;

/**
 * 使用切面来用户鉴权
 */
@Aspect
@Component
public class AuthorizationAspect {
    private final Logger logger = LoggerFactory.getLogger(AuthorizationAspect.class);

    @Autowired
    private RestTemplate template;

    @Pointcut(value = "@annotation(com.a205.mybed.pictureservice.aspect.Authorization)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object check(ProceedingJoinPoint point) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String token = attributes.getRequest().getHeader("Authorization");
        // 进行验证
        if (token != null) {
            // todo
            logger.info("调用{}: 验证成功", point.getSignature().getName());
            return point.proceed();
        }
        // 没有或验证失败
        logger.info("调用{}: 验证失败", point.getSignature().getName());
        attributes.getResponse().setStatus(HttpStatus.SC_UNAUTHORIZED);
        return new RestAPIResult<>().error(401, "未登录或登录凭证错误");
    }
}
