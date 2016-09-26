package midi.input;

import java.io.File;
import java.io.IOException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import utils.Utils;

public class MidiReader {
	public static final int NOTE_ON = 0x90;
    public static final int NOTE_OFF = 0x80;
    public static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

    public static FileMIDI lireMIDI(FileMIDI fichier)
    {
        Sequence sequence = null;
		try {
			sequence = MidiSystem.getSequence(new File(fichier.getPath()));
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(sequence != null)
		{
			FileMIDI file = fichier;
	        for (Track track :  sequence.getTracks()) 
	        {	
	        	TrackMIDI tr = new TrackMIDI(0, sequence.getResolution(), track.ticks());
	            for (int i=0; i < track.size(); i++) 
	            { 
	                MidiEvent event = track.get(i);
	                MidiMessage message = event.getMessage();
	                if (message instanceof ShortMessage) 
	                {
	                    ShortMessage sm = (ShortMessage) message;   
	                    if (sm.getCommand() == NOTE_ON && sm.getData2()!=0) 
	                    {
	                        tr.addNote(new NoteMIDI(sm.getData1(), (sm.getData1() / 12)-1, NOTE_NAMES[sm.getData1() % 12], sm.getData2(), 0, event.getTick()));
	                    }  
	                }
	                else if (message instanceof MetaMessage)
	                {
	                	MetaMessage mm = (MetaMessage) message;
	                	if(mm.getType() == 81)
	                	{
	                		String mpqnHex = "";
	                		byte[] data = mm.getData();
	                		for(int j=0;j<data.length;j++)
	                		{
	                			mpqnHex += Integer.toHexString(data[j] & 0xFF).toUpperCase();
	                		}
	                		long mpqn = Long.parseLong(mpqnHex, 16);
	                		long tempo = 60000000/mpqn;
	                		double beat = (double)((double)event.getTick()/(double)sequence.getResolution());
	                		if(tempo < 300)
	                		{
	                			file.addTempo(Utils.roundDouble3(beat), tempo);
	                		}
	                	}
	                }
	            }
	            
	            int i = tr.size() - 1;
	            int j = 0, limit=0;
	            NoteMIDI n = tr.getLastNote();
	            NoteMIDI n2 = n;
	            
	            if(n!=null)
	            {
	            	long l = tr.getLength() - n.getTick();
	            	while(n2.getTick() == n.getTick())
	            	{
	            		tr.getNote(i-limit).setLength(l);
	            		limit++;
	            		n2 = tr.getNote(i-limit);
	            	}
		            j=0;
		            for (i=0; i < tr.size()- limit ; i++)
		            {
		            	n = tr.getNote(i);
		            	n2 = n;
			            while(n.getTick() == n2.getTick() && j < tr.size()- limit)
			            {
			            	n2 = tr.getNote(i+j);
			            	j++;
			            }
			            tr.getNote(i).setLength((n2.getTick()-n.getTick()));
			            j=0;
		            }

		            file.addTrack(tr);
	            }
	        }
            return file; 
        }
		else return null;
    }
}