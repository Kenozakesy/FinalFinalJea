package domain.post;

public class PostDTO {

    private long id;
    private String message;
    private long channelId;
    private String sender;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public long getChannelId() {
        return channelId;
    }
    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }


    @Override
    public String toString() {
        return "PostDTO{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", channelId=" + channelId +
//                ", from='" + from + '\'' +
                '}';
    }
}
