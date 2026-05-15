package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;


public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User vasya = new User("Vasya", "Vasechkin", "vasechkin@mail.io");
      User petya = new User("Petya", "Sidorov", "sidorov@mail.io");
      User olga = new User("Olga", "Petrova", "petrova@mail.io");
      User sveta = new User("Svetlana", "Ivanova", "ivanova@mail.io");

      Car volvo = new Car("Volvo", 9);
      Car bmw = new Car("BMW", 325);
      Car suzuki = new Car("Sisuki", 4);
      Car lada = new Car("Ladaa", 21014);

      userService.add(vasya.setCar(volvo).setUser(vasya));
      userService.add(petya.setCar(bmw).setUser(petya));
      userService.add(olga.setCar(suzuki).setUser(olga));
      userService.add(sveta.setCar(lada).setUser(sveta));

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }

      System.out.println(userService.getUserByCar("Sisuki", 4));

      try {
         User notUser = userService.getUserByCar("Ladaa", 4211);
      } catch (NoResultException e) {
         System.out.println("User not found");
      }


      context.close();
   }
}
