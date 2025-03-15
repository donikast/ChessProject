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

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/players/view")
public class ShowPlayerServlet extends HttpServlet {

    private Storage storage;

    @Override
    public void init() throws ServletException {
        storage = Storage.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       PrintWriter out = resp.getWriter();
       Message message = new Message();

        String id = req.getParameter("id");
        if (id != null) {
            ChessPlayer chessPlayer = storage.getChessPlayer(id);
            if (chessPlayer != null) {
                try {
                    XmlParser.writeToXML(out, chessPlayer);
                } catch (JAXBException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                message.setContent("No player with id " + id);
                try {
                    XmlParser.writeToXML(out, message);
                } catch (JAXBException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
