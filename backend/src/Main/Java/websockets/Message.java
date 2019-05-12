package websockets;

public class Message {
    private String sender;
    private String to;
    private String message;

    @Override
    public String toString() {
        return super.toString();
    }

    public String getFrom() {
        return sender;
    }
    public void setFrom(String from) {
        this.sender = from;
    }

    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String content) {
        this.message = content;
    }
}
