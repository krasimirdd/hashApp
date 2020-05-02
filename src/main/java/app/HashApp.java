package app;

import fkst.FkstHashConsole;
import fkst.FkstHashFile;
import fkst.FkstHashString;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class HashApp {
    public static void main(String[] args) {
        try {
            System.out.println(FkstHashString.generateSha3_512("password"));
            System.out.println(FkstHashConsole.generateSha3_512(new Scanner(System.in).nextLine()));
            System.out.println(FkstHashFile.generateSha3_512(Paths.get("C:\\Users\\kdimitrov\\Home\\Java\\hashApp\\src\\main\\resources\\test")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
