import java.sql.Timestamp;

public class Values {
	private Timestamp timestamp;
	private long weight;
	
	public Values(Timestamp timestamp, long weight) {
		super();
		this.timestamp = timestamp;
		this.weight = weight;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public long getWeight() {
		return weight;
	}
	public void setWeight(long weight) {
		this.weight = weight;
	}
}
