package test.playtech.PlayerDepositApp.instances;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "deposits")
public class DepositEvent {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Version
    private long version;

    @Column(name="playerId")
    private long playerId;

    @Column(name="deposit")
    private double deposit;

    @Column(name="timestamp")
    private String timestamp;

    public DepositEvent() {
    }

    public DepositEvent(long playerId, double deposit, String timestamp) {
        this.playerId = playerId;
        this.deposit = deposit;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public long getPlayerId() {
        return playerId;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "DepositEvent{" +
                "id='" + id + '\'' +
                ", playerId='" + playerId + '\'' +
                ", deposit='" + deposit + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
