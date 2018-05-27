package gr.mfa.as.data;

public class Fakeloi {

    String envelope;
    int lastNumber;

    public Fakeloi(String envelope, int lastNumber) {
        this.envelope = envelope;
        this.lastNumber = lastNumber;
    }

    public String getEnvelope() {
        return envelope;
    }

    public void setEnvelope(String envelope) {
        this.envelope = envelope;
    }

    public int getLastNumber() {
        return lastNumber;
    }

    public void setLastNumber(int lastNumber) {
        this.lastNumber = lastNumber;
    }
}
