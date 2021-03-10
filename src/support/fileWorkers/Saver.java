package support.fileWorkers;

import model.student.PublicWork;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.student.Student;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.File;
import java.util.List;

public class Saver {
    final List<Student> toSave;
    final Stage parent;

    public Saver(List<Student> toSave, Stage stage) {
        this.toSave = toSave;
        parent = stage;
    }

    public void save() {
        try {
            FileChooser directoryChooser = new FileChooser();
            directoryChooser.setInitialDirectory(new File("saves"));
            directoryChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files (*.xml)","*.xml"));
            File selectedFile = directoryChooser.showSaveDialog(parent);
            if (!selectedFile.exists())
                selectedFile.createNewFile();
            Transformer saver = TransformerFactory.newInstance().newTransformer();
            saver.transform(new DOMSource(genDoc()), new StreamResult(selectedFile));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Document genDoc() throws ParserConfigurationException {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element rootElement = doc.createElement("root");
        doc.appendChild(rootElement);
        for (Student student : toSave) {
            rootElement.appendChild(getStudent(doc, student));
        }
        return doc;
    }

    private Node getStudent(Document doc, Student toSave) {
        Element student = doc.createElement("Student");
        student.appendChild(getElement(doc, "surname", toSave.getFio().getSurname()));
        student.appendChild(getElement(doc, "name", toSave.getFio().getName()));
        student.appendChild(getElement(doc, "patronymic", toSave.getFio().getPatronymic()));
        student.appendChild(getElement(doc, "group", String.valueOf(toSave.getGroup().getNumber())));
        student.appendChild(getPublicWorks(doc, toSave.getSemesters()));
        return student;
    }

    private Node getElement(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    private static Node getPublicWorks(Document doc, PublicWork[] toSave) {
        Element root = doc.createElement("Works");
        for (int i = 0; i < 10; i++) {
            Element node = doc.createElement("Work");
            node.setAttribute("id", String.valueOf(i));
            node.appendChild(doc.createTextNode(String.valueOf(toSave[i].getHours())));
            root.appendChild(node);
        }
        return root;
    }
}
