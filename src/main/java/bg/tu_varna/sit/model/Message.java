package bg.tu_varna.sit.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@XmlRootElement(name="message")
@XmlAccessorType(XmlAccessType.FIELD)
public class Message implements Serializable {
    private String content;

    public Message(String content) {
        this.content = content;
    }

    public Message() {}

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
