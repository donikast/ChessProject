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
import java.util.Set;

@WebServlet("/players")
public class ShowAllPlayersServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init() throws ServletException {
        storage = Storage.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        Message message = new Message();

        Set<ChessPlayer> players = storage.getChessPlayers();

        if (players.isEmpty()) {
            message.setContent("There are no players in the database");
            try {
                XmlParser.writeToXML(out, message);
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            try {
                    XmlParser.writeToXML(out,storage);
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
