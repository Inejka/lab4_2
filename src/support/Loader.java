package support;

import model.student.FIO;
import model.student.Group;
import model.student.PublicWork;
import model.student.Student;

import java.io.File;

import java.util.List;


import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;


public class Loader {
    List<Student> toSave;
    Stage parent;

    public Loader(List<Student> toSave, Stage stage) {
        this.toSave = toSave;
        parent = stage;
    }

    public void load() {
        try {
            FileChooser directoryChooser = new FileChooser();
            directoryChooser.setInitialDirectory(new File("saves"));
            directoryChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml"));
            File selectedFile = directoryChooser.showOpenDialog(parent);
            if (!selectedFile.exists())
                selectedFile.createNewFile();
            SAXParserFactory.newInstance().newSAXParser().parse(selectedFile, new MyHandler(toSave));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class MyHandler extends DefaultHandler {
        String surname, name, patronymic;
        int group;
        PublicWork[] works;
        List<Student> toAdd;
        String lastName;
        int i;

        public MyHandler(List<Student> toAdd) {
            this.toAdd = toAdd;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("Student")) works = new PublicWork[10];
            lastName = qName;
            if (qName.equals("Work")) i = Integer.parseInt(attributes.getValue("id"));
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String information = new String(ch, start, length);

            information = information.replace("\n", "").trim();

            if (!information.isEmpty()) {
                if (lastName.equals("surname"))
                    surname = information;
                if (lastName.equals("name"))
                    name = information;
                if (lastName.equals("patronymic"))
                    patronymic = information;
                if (lastName.equals("group"))
                    group = Integer.parseInt(information);
                if (lastName.equals("Work"))
                    works[i] = new PublicWork(Integer.parseInt(information));
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equals("Student"))
                toAdd.add(new Student(new FIO(surname, name, patronymic), new Group(group), works));
        }

    }

}
