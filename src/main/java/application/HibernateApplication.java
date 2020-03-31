package application;

import daos.UserDao;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HibernateApplication {
    private final UserDao userDao;
    private static    BufferedReader userInputReader=new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] arg) throws Exception{
        EntityManager entityManager= Persistence.createEntityManagerFactory("user-unit").createEntityManager();
        UserDao userDao1=new UserDao(entityManager);

        new HibernateApplication(userDao1).run();

    }
    public HibernateApplication(UserDao userDao){
        this.userDao=userDao;

    }
    private void run() throws IOException {
        System.out.println("Enter an option: "
                + "1) Insert a new user. "
                + "2) Find a user. "
                + "3) Edit a user. "
                + "4) Delete a user.");
        int option = Integer.parseInt(userInputReader.readLine());

        switch (option) {
            case 1:
                persistNewUser();
                break;
            case 2:
                fetchExistingUser();
                break;
            case 3:
                updateExistingUser();
                break;

        }
    }
    private void persistNewUser() throws IOException{
        String name=requestStringInput("the name of user");
        String email=requestStringInput("the email of user");
        userDao.persist(name,email);
    }
    private void fetchExistingUser() throws IOException{
        String id=requestStringInput("the user id");
        User user=userDao.find(Integer.parseInt(id));
        System.out.print("Name "+user.getName() +"Email "+user.getEmail());

    }
    private void updateExistingUser() throws IOException{
        int id=requestIntegerInput("the user id");
        String name=requestStringInput("The name of user");
        String email=requestStringInput("The email of user");
        userDao.update(id,name,email);
    }

    private String requestStringInput(String request) throws  IOException{
        System.out.print("Enter "+request);
        return userInputReader.readLine();
    }
    private int requestIntegerInput(String request) throws IOException{
        System.out.println("Enter request "+request);
        return Integer.parseInt(userInputReader.readLine());
    }
}
