package com.example.springboot_amqs.service;


import com.example.springboot_amqs.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @RabbitListener(queues = "guli.news")
    public void receive(Book book){
        System.out.println("收到消息："+book);
    }

/*    @RabbitListener(queues = "pipili")
    public void receive02(Message message){
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }*/
}
