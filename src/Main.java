import java.io.File;
import java.io.IOException;

import midi.converter.MidiConverter;
import midi.input.*;

public class Main 
{
    
	public static void main(String args[])
	{
		   String midiPath = args[0];
		   String directoryName = args[0].substring(0, args[0].lastIndexOf('.'));
		   String fileName = "";
		   if(fileName.contains(directoryName))
		   {
			   fileName = directoryName.substring(directoryName.lastIndexOf("/"));
		   }
		   else
			   fileName = directoryName;
		   
		   File directory = new File(directoryName);
		   boolean isDirectoryCreated = directory.mkdirs();
		   if(isDirectoryCreated)
		   {
			   int r = MidiConverter.toMp3(midiPath, directoryName, fileName);
			   if(r == 0)
			   {
				   FileMIDI fichier = new FileMIDI(args[0]);
				   fichier = MidiReader.lireMIDI(fichier);
				   if(fichier != null)
				   {
					   MidiConverter.toDwi(fichier, midiPath, directoryName, fileName);
					   System.out.println("done.");
				   }
			   }
		   }
	}	   
}
 
