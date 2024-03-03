package ru.stepup;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.stepup.entities.User;
import ru.stepup.services.UserService;

/**
 * - Разверните локально postgresql БД, создайте таблицу users (id bigserial primary key, username varchar(255) unique);
 * - Создайте Maven проект и подключите к нему: драйвер postgresql, hickaricp, spring context.
 * - Создайте пул соединений в виде Spring бина
 * - Создайте класс User (Long id, String username)
 * - Реализуйте в виде бина класс UserDao который позволяет выполнять CRUD операции над пользователями
 * - Реализуйте в виде бина UserService, который позволяет: создавать, удалять, получать одного, получать всех пользователей из базы данных
 * - Создайте SpringContext, получите из него бин UserService выполните все возможные операции
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("ru.stepup");
        UserService userService = context.getBean(UserService.class);

        System.out.println(userService.createUser(new User("Boris")));
        System.out.println(userService.createUser(new User("Ivan")));
        System.out.println(userService.createUser(new User("Nikolay")));
        System.out.println(userService.getAllUsers());

    }
}