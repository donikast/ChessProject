package bg.tu_varna.sit.utils;

import bg.tu_varna.sit.model.ChessPlayer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;

public class XmlParser {

    private static String xsdFile ="D:\\Courses\\IT\\2025\\Projects\\ChessProject\\src\\main\\resources\\xsdFile.xsd";

    public static <T> void writeToXML(Writer writer, T object) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(object, writer);
    }

    public static ChessPlayer readFromXML(String xml) throws JAXBException, SAXException {
        JAXBContext context = JAXBContext.newInstance(ChessPlayer.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

      SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File(xsdFile));
        unmarshaller.setSchema(schema);

        return (ChessPlayer)unmarshaller.unmarshal(new StringReader(xml));
    }
}
