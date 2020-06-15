public class Measurement {
    private String id;
    private String type;
    private double value;
    private long timestamp;

    public Measurement() {}

    public Measurement(String id, String type, double value, long timestamp) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.timestamp = timestamp;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", timestamp=" + timestamp +
                '}';
    }
}
