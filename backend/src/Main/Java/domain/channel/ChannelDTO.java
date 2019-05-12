package domain.channel;

public class ChannelDTO {

    private long id;
    private String name;
    private long serverId;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getServerId() {
        return serverId;
    }
    public void setServerId(long serverId) {
        this.serverId = serverId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ChannelDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", serverId=" + serverId +
                '}';
    }


}
