

import instances.Car;
import instances.Document;
import instances.Owner;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Base64;

/**
 * Created by Olexandr Finchuk on 10/3/2016.
 */
public class Main {
    final static Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args){

        LOGGER.info("lol");
        String password = "user";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] passwordBytes = password.getBytes();
        byte[] hash = md.digest(passwordBytes);
        String passwordHash =
                Base64.getEncoder().encodeToString(hash);
        System.out.println("password hash: "+passwordHash);


    }
}
