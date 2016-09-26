package midi.input;

import java.util.* ;

public class TrackMIDI 
{

	private ArrayList<NoteMIDI> track;
	private int channel;
	private int resolution;
	private long length;
	
	public TrackMIDI(int channel, int resolution, long length)
	{
		this.track = new ArrayList<NoteMIDI>();
		this.channel = channel;
		this.resolution = resolution;
		this.length = length;
	}
	
	public ArrayList<NoteMIDI> getNotes()
	{
		return this.track;
	}
	
	public ArrayList<NoteMIDI> getAggregatedNotes()
	{
		HashMap<Long, NoteMIDI> aggregatedTrack = new HashMap<Long, NoteMIDI>();
		ArrayList<NoteMIDI> ret = new ArrayList<NoteMIDI>();
		for(NoteMIDI n : this.track)
		{
			if(!aggregatedTrack.containsKey(n.getTick()))
			{
				aggregatedTrack.put(n.getTick(), n);
				ret.add(n);
			}
		}
		return ret;
	}
	
	public void addNote(NoteMIDI n)
	{
		this.track.add(n);
	}
	
	public NoteMIDI getNote(int i)
	{
		return track.get(i);
	}
	
	public int size()
	{
		return this.track.size();
	}
	
	public int getResolution()
	{
		return this.resolution;
	}
	
	public long getLength()
	{
		return this.length;
	}
	
	public NoteMIDI getLastNote()
	{
		if(track.size()!=0)
		{
			return track.get(this.track.size() - 1 );
		}
		else return null;
	}
	
	public String toString()
	{
		String message="Channel: " + this.channel + "; Resolution: " + this.resolution + "; Length: " + this.length + "; \n\n";
		int i =0;
		for(NoteMIDI n : track)
		{
			i++;
			message = message + n.toString() + "\n";
			if(i>100)
				break;
		}
		return message;
	}
	
}