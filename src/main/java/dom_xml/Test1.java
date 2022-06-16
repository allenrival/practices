package dom_xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @Description: TODO
 * @author: allen
 * @date: 2022年03月07日 13:48
 */
public class Test1 {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, FileNotFoundException {
       Document document = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .newDocument();
        Element sssss = document.createElement("sssss");
        Element bbbbb = document.createElement("bbbbb");
        bbbbb.setTextContent("sdsdsdsdsdsdsdsd");
        sssss.appendChild(bbbbb);
        document.appendChild(sssss);
        TransformerFactory factory= TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING,"utf-8");
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        FileOutputStream fos = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\practices1.xml");
        transformer.transform(new DOMSource(document),new StreamResult(fos));
    }
}
