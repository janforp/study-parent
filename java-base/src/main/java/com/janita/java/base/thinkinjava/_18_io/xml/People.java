package com.janita.java.base.thinkinjava._18_io.xml;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * People
 *
 * @author zhucj
 * @since 20200528
 */
public class People extends ArrayList<Person> {

    public People(String fileName) throws ParsingException, IOException {
        Document document = new Builder().build(fileName);
        Element rootElement = document.getRootElement();
        Elements childElements = rootElement.getChildElements();
        for (int i = 0; i < childElements.size(); i++) {
            Element element = childElements.get(i);
            Person person = new Person(element);
            this.add(person);
        }
    }

    public static void main(String[] args) throws ParsingException, IOException {
        People people = new People("People.xml");
        System.out.println(people);
    }
}
