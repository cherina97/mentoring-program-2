package epam.com.task1;

import epam.com.task1.model.Person;
import epam.com.task1.model.Programmer;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person person = new Programmer(
                "Max", 25, 1500, "IntelliArts", "C++", 1.5);

        String pathToFile = "module-io-nio/src/main/resources/com.epam.task1.txt";
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(pathToFile));
        objectOutputStream.writeObject(person);
        objectOutputStream.close();

        System.out.println("Object has been serialized");

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(pathToFile));
        Programmer deserializeObject = (Programmer) objectInputStream.readObject();
        objectInputStream.close();

        System.out.println("Object has been deserialized");

        System.out.println("Name - " + deserializeObject.getName() +
                ", company name - " + deserializeObject.getCompanyName() +
                ", programing lang - " + deserializeObject.getProgramingLanguage());
    }
}
