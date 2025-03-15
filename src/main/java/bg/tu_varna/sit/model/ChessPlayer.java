package bg.tu_varna.sit.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.Objects;

@XmlRootElement(name="chessPlayer")
@XmlAccessorType(XmlAccessType.FIELD)
public class ChessPlayer implements Serializable {

    private String fideId;
    private String firstName;
    private String lastName;
    private int elo;


    public ChessPlayer() {
    }

    public ChessPlayer(String fideId, String firstName, String lastName, int elo) {
        this.fideId = fideId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.elo = elo;
    }

    public String getFideId() {
        return fideId;
    }

    public void setFideId(String fideId) {
        this.fideId = fideId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getElo() {
        return elo;
    }

    public void setElo(int elo) {
        this.elo = elo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ChessPlayer that = (ChessPlayer) o;
        return Objects.equals(fideId, that.fideId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(fideId);
    }

    @Override
    public String toString() {
        return "ChessPlayer{" +
                "fideId='" + fideId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", elo=" + elo +
                '}';
    }
}
