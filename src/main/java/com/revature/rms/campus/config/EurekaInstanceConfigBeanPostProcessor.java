package com.revature.rms.campus.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
@Profile("dev")
public class EurekaInstanceConfigBeanPostProcessor implements BeanPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(EurekaInstanceConfigBeanPostProcessor.class);

    @Value("${spring.application.name}")
    private String serviceName;

    @Value("${server.port}")
    private int port;

    private String fargateIp;

    public EurekaInstanceConfigBeanPostProcessor() {
        try {
            fargateIp = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            LOGGER.warn("Could not get the Fargate instance ip address.");
        }
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {

        if (bean instanceof EurekaInstanceConfigBean) {
            LOGGER.info("EurekaInstanceConfigBean detected. Setting IP address to {}", fargateIp);
            EurekaInstanceConfigBean instanceConfigBean = ((EurekaInstanceConfigBean) bean);
            instanceConfigBean.setInstanceId(fargateIp + ":" + serviceName + ":" + port);
            instanceConfigBean.setIpAddress(fargateIp);
            instanceConfigBean.setHostname(fargateIp);
            instanceConfigBean.setStatusPageUrl("http://" + fargateIp + ":" + port + "/actuator/info");
            instanceConfigBean.setHealthCheckUrl("http://" + fargateIp + ":" + port + "/actuator/info");
        }

        return bean;
    }

}
