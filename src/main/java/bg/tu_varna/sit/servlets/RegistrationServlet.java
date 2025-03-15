package bg.tu_varna.sit.servlets;

import bg.tu_varna.sit.model.ChessPlayer;
import bg.tu_varna.sit.model.Message;
import bg.tu_varna.sit.repository.Storage;
import bg.tu_varna.sit.utils.XmlParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.JAXBException;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/players/add")
public class RegistrationServlet extends HttpServlet {

    private Storage storage;

    @Override
    public void init() throws ServletException {
       storage = Storage.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        Message message = new Message();

        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();

        try {
            ChessPlayer chessPlayer = XmlParser.readFromXML(sb.toString());
            if(storage.addChessPlayer(chessPlayer)) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                message.setContent("Successfully added chess player");
                XmlParser.writeToXML(out, message);

            }
            else {
                resp.setStatus(HttpServletResponse.SC_OK);
                message.setContent("Already added chess player");
                XmlParser.writeToXML(out, message);
            }
        } catch (JAXBException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
