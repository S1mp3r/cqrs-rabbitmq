package br.rafael.users.api.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQTopicConfig {
    
    public final String exchangeName = "usersExchange";
    public final  String customerQueue = "customerQueue";
    
    @Bean
    TopicExchange exchange(){
        return new TopicExchange(exchangeName);
    }

    @Bean
    Queue customerQueue() {
        return new Queue(customerQueue, true);
    }

    @Bean
    Binding bindingCustomer(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(exchange).to(exchange).with("customer.#");
    }

}
