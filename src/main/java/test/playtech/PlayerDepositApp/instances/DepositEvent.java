package test.playtech.PlayerDepositApp.instances;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class DepositEvent {

    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column
    private long playerId;

    @Column
    private double deposit;

    @Column
    private String timestamp;

    public DepositEvent() {
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
