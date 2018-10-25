package test.playtech.PlayerDepositApp.instances;

import java.util.List;

public class DepositsAggregation {

    private String type;
    private double total;
    private List<DepositEvent> depositEvents;

    public DepositsAggregation() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<DepositEvent> getDepositEvents() {
        return depositEvents;
    }

    public void setDepositEvents(List<DepositEvent> depositEvents) {
        this.depositEvents = depositEvents;
    }
}
