package midi.converter;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.util.ArrayList;

import midi.input.FileMIDI;
import midi.input.NoteMIDI;
import midi.input.Pair;
import midi.input.TrackMIDI;
import utils.Stepmania;
import utils.Utils;

public class MidiConverter {

	public static int toMp3(String midiPath, String directoryName, String fileName)
	{
		int r = -1;
		try {
			Process process = Runtime.getRuntime().exec("C:/timidity/timidity.exe -Og " + midiPath);
			try {
				r = process.waitFor();
				if(r == 0)
				{
					String sourcemp3 = midiPath.replaceAll(".mid", ".mp3");
					String destinationMp3 = directoryName + "/" + fileName + ".mp3";
					Files.move(Paths.get(sourcemp3), Paths.get(destinationMp3), StandardCopyOption.REPLACE_EXISTING);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return r;
	}
	
	public static int toDwi(FileMIDI midi, String midiPath, String directoryName, String fileName)
	{
		Writer writer = null;
		try 
		{
			String destinationDwi = directoryName + "/" + fileName + ".dwi";
		    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destinationDwi), "utf-8"));
		    writer.write("#TITLE:" + fileName + ";\n");
		    writer.write("#ARTIST:MIDIToDWI;\n");
		    writer.write("#GAP:0;\n");
		   
		    
		    ArrayList<Pair<Double, Long>> tempoChanges = midi.getTempoChanges();
		    writer.write("#BPM:" + tempoChanges.get(0).getR() + ";\n");
		    writer.write("#CHANGEBPM:");
		    int k =0;
		    for(Pair<Double, Long> pair : tempoChanges)
		    {
		    	String beat = String.format("%.3f", pair.getL()).replaceAll(",",  ".");
		    	String tempo = String.format("%.3f", (double)pair.getR()).replaceAll(",",  ".");
		    	
		    	writer.write(beat + "=" + tempo);
		    	if(k<tempoChanges.size() - 1)
		    	{
		    		writer.write(",");
		    	}
			    k++;
		    }
		    writer.write(";\n\n");
		    writer.write("#SINGLE:SMANIAC:12:\n(");
		    TrackMIDI track = midi.getTrack(0);
		    long resolution = track.getResolution();
		    ArrayList<NoteMIDI> notes = track.getAggregatedNotes();
		    double lastCompared = 1;
		    
		    for(NoteMIDI note : notes)
		    {
		    	long length = note.getTick();
		    	double compared = (double)((double)length / (double)resolution);
		    	if(compared % 0.25 == 0)
		    	{
		    		compared = Utils.roundDouble(compared);
		    		int nbZeros = (int)((compared - lastCompared)/0.25) - 1;
			    	
			    	String zeros = "";
			    	for(int j =0; j<nbZeros;j++)
			    	{
			    		zeros += "0";
			    	}
			    	writer.write(zeros);
			    	System.out.println(compared + " " +  zeros);
			    	writer.write(Stepmania.randomArrows(compared));
			    	lastCompared = compared;
		    	}
		    	
		    	//System.out.println(compared);
		    }
		    writer.write(");\n");
		} 
		catch (IOException ex) {
		  // report
		} 
		finally 
		{
		   try 
		   {
			   writer.close();
		   } 
		   catch (Exception ex)
		   {/*ignore*/}
		}
		return 0;
	}
}
