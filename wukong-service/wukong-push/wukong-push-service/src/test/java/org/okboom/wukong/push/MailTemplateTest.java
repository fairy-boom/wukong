package org.okboom.wukong.push;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author tookbra
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PushApplication.class)
public class MailTemplateTest {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testSendSingle() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("411742963@qq.com");
        message.setTo("tookbra@outlook.com");
        message.setSubject("test java mail");
        message.setText("this is test mail");
        mailSender.send(message);
    }

    @Test
    public void sendTemplateMail() throws MessagingException {
        Context context = new Context();
        context.setVariable("captcha", "123456");
        String emailContent = templateEngine.process("register", context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("411742963@qq.com");
        helper.setTo("tookbra@outlook.com");
        helper.setSubject("test html mail");
        helper.setText(emailContent, true);

        mailSender.send(message);
    }
}
