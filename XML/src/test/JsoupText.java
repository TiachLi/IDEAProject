package test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;

public class JsoupText {
    public static void main(String[] args) {

     //  String path = JsoupText.class.getClassLoader().getResource("student.xml").getPath();
       String path = JsoupText.class.getResource("/xsd/student.xml").getPath();
        try {
            Document document = Jsoup.parse(new File(path), "utf-8");
            Elements allElements = document.getElementsByTag("name");
            System.out.println(allElements.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
