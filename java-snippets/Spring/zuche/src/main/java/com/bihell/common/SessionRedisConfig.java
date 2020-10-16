package com.bihell.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)
/**
 * maxInactiveIntervalInSeconds: 设置 Session 失效时间，使用 Redis Session 之后，原 Boot 的 server.session.timeout 属性不再生效。
 */
public class SessionRedisConfig {
}
