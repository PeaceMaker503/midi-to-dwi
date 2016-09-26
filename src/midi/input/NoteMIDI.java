package midi.input;

public class NoteMIDI {
	
	public static final int NOTE_ON = 0x90;
    public static final int NOTE_OFF = 0x80;
    public static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

    private int key;
    private int octave;
    private String noteName;
    private int velocity;
    private long length;
    private long tick;

    public NoteMIDI(int key, int octave, String noteName, int velocity, long length, long tick)
    {
    	this.key = key;
    	this.octave = octave;
    	this.noteName = noteName;
    	this.velocity = velocity;
    	this.length = length;
    	this.tick = tick;

    }
    
    public long getTick()
    {
    	return this.tick;
    }
    
    public long getLength()
    {
    	return this.length;
    }
    
    public void setLength(long l)
    {
    	this.length = l;
    }
    
    public String toString()
	{
		return "@" + this.tick + "; Note: " + this.noteName + this.octave + "; Key: " + this.key + "; Velocity: " + this.velocity + "; Length: " + this.length +";";
	}
}
