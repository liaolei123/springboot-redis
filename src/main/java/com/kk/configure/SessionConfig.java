package com.kk.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author Mr.kk
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400*30) //设置session的失效时间
public class SessionConfig {

}
