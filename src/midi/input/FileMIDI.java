package midi.input;
import java.util.* ;

import utils.Utils;


public class FileMIDI {

	private ArrayList<TrackMIDI> tracks;
	String path;
	String name;
	private ArrayList<Pair<Double, Long>> tempoChanges;
	
	public FileMIDI(String path)
	{
		this.tracks = new ArrayList<TrackMIDI>();
		this.path = path;
		tempoChanges = new ArrayList<Pair<Double, Long>>();
	}
	
	public ArrayList<Pair<Double, Long>> getTempoChanges()
	{
		return this.tempoChanges;
	}
	
	public void addTempo(double beat, long tempo)
	{
		this.tempoChanges.add(new Pair<Double, Long>(Utils.roundDouble3(beat), tempo));
	}
	
	public void addTrack(TrackMIDI tr)
	{
		tracks.add(tr);
	}
	
	public String toString()
	{
		return this.path + "; Nb de tracks : " + tracks.size();
	}
	
	public TrackMIDI getTrack(int i)
	{
		return this.tracks.get(i);
	}
	
	public void printTrack(int i)
	{
		System.out.println("Track n°" + i + ":\n\n" + this.tracks.get(i));
	}
	
	public void printAllTracks()
	{
		for(int i=0; i<=this.tracks.size()-1; i++)
		{
			System.out.println("Track n°" + i + ":\n\n" + this.tracks.get(i));
		}
	}
	
	public String getPath()
	{
		return this.path;
	}
	
}
