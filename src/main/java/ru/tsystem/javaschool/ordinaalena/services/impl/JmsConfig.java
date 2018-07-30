package ru.tsystem.javaschool.ordinaalena.services.impl;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

/**
 * This class set some JMS settings
 * In our case, we use ActiveMQ as JMS implementation
 */
@Configuration
@EnableJms
@ComponentScan("ru.tsystem.javaschool.ordinaalena")
public class JmsConfig {
    /**
     * URL where our JMS server is available
     */
    private static final String BROKER_URL = "tcp://localhost:61616";
    /**
     * Username for our JMS server
     */
    private static final String BROKER_USERNAME = "admin";
    /**
     * Password for our JMS server
     */
    private static final String BROKER_PAROLE = "admin";
    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(BROKER_URL);
        connectionFactory.setPassword(BROKER_PAROLE);
        connectionFactory.setUserName(BROKER_USERNAME);
        return connectionFactory;
    }
    /**
     * Method register jmsTemplate in spring context.
     * It uses connectionFactory we defined above and give us simple API
     * for sending messages.
     * @return JmsTemplate exemplar
     */
    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        return template;
    }

    /**
     * Method register jmsListenerContainerFactory in spring context.
     * @return DefaultJmsListenerContainerFactory exemplar
     */
    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrency("1-1");
        return factory;
    }
}

