package bg.tu_varna.sit.repository;

import bg.tu_varna.sit.model.ChessPlayer;
import jakarta.xml.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name="chessPlayers")
@XmlAccessorType(XmlAccessType.FIELD)
public class Storage {

    private static Storage instance = null;

    @XmlElement(name="chessPlayer")
    private Set<ChessPlayer> chessPlayers;


    private Storage() {
        chessPlayers = new HashSet<ChessPlayer>();
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public Set<ChessPlayer> getChessPlayers() {
        return chessPlayers;
    }

    public boolean addChessPlayer(ChessPlayer chessPlayer) {
        return chessPlayers.add(chessPlayer);
    }

    public ChessPlayer getChessPlayer(String id) {
        for (ChessPlayer chessPlayer : chessPlayers) {
            if(chessPlayer.getFideId().equals(id)) {
                return chessPlayer;
            }
        }
        return null;
    }

}
