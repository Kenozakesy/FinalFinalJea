package domain.accountServer;

public class AccountServerDTO {

    private long id;
    private long accountId;
    private long serverId;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getAccountId() {
        return accountId;
    }
    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }
    public long getServerId() {
        return serverId;
    }

    public void setServerId(long serverId) {
        this.serverId = serverId;
    }


    @Override
    public String toString() {
        return "AccountServerDTO{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", serverId=" + serverId +
                '}';
    }
}
