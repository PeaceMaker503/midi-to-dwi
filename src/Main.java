/*
MIT License

Copyright (c) 2016 Halim Chellal

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

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
 
