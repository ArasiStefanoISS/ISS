import java.sql.Timestamp;
import java.util.HashMap;

public class Tickets {
	private HashMap<Long,Values> Tic;
	private long w;
	private long currID;
	private int secs;

	public Tickets() {
		this.Tic=new HashMap<Long,Values>();
		this.w=0;
		this.currID=0;
		this.secs=20;
	}
	
	public String store(long weight) {
		chackTickets();
		currID++;
		Tic.put(currID, new Values(new Timestamp(System.currentTimeMillis()), weight));
		return String.valueOf(currID);
	}
	
	public long spaceBooked()
	{
		chackTickets();
		return w;
	}
	
	public long checkValidity(String s) {
		long id=Long.getLong(s);
		long temp=0;
		chackTickets();
		if(Tic.get(id)!=null) {
			temp=Tic.get(id).getWeight();
			w=w-temp;
			Tic.remove(id);
			return temp;
		}
		return temp;
	}
	
	public void chackTickets() {
		for(long l:Tic.keySet()) {
			if (Tic.get(l).getTimestamp().getTime()+(secs*1000) < System.currentTimeMillis()) {
				w=w-Tic.get(l).getWeight();
				Tic.remove(l);
			}
		}
	}
}
