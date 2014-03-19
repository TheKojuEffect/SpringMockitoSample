package io.koju.sample.directory;

import io.koju.sample.directory.context.AppContext;
import io.koju.sample.directory.entity.User;
import io.koju.sample.directory.exceptions.EntityValidationException;
import io.koju.sample.directory.service.impl.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Kapil Koju on 3/19/14.
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);

        User user = new User();
        user.setName("Jimmy Sales");
        user.setUsername("jimmy");
        user.setPassword("J3n32nunhdu7");

        UserService userService = context.getBean(UserService.class);

        try {
            user = userService.createNewUser(user);
            System.out.println("Name: " + user.getName());
            System.out.println("username: " + user.getUsername());
            System.out.println("password: " + user.getPassword());

            if (userService.isPasswordValid(user)) {
                System.out.println(user.getUsername() + " has long password");
            } else {
                System.out.println(user.getUsername() + " has short password");
            }

        } catch (EntityValidationException e) {
            System.err.println(e.getMessage());

        }


    }
}
