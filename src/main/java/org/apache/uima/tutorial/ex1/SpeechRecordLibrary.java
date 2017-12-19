package org.apache.uima.tutorial.ex1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.uima.exceptions.EmptyResultException;
import org.apache.uima.exceptions.FileNotExistException;

import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;
import com.google.protobuf.ByteString;

public class SpeechRecordLibrary {

	
	public String recognize (String fileName) throws EmptyResultException, FileNotExistException {
		  // Reads the audio file into memory
		
		Path path =null;
		path=Paths.get(fileName);
	  
	// Instantiates a client
	    SpeechClient speech = null;
		try {
			speech = SpeechClient.create();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	    // The path to the audio file to transcribe
	   // String fileName = "./resources/audio.raw";
	    
	    byte[] data=null;
		try {
			data = Files.readAllBytes(path);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
		
			throw new FileNotExistException();
		}
	    ByteString audioBytes = ByteString.copyFrom(data);

	    // Builds the sync recognize request
	    RecognitionConfig config = RecognitionConfig.newBuilder()
	        .setEncoding(AudioEncoding.LINEAR16)
	        .setSampleRateHertz(16000)
	        .setLanguageCode("ru-RU")
	        .build();
	    RecognitionAudio audio = RecognitionAudio.newBuilder()
	        .setContent(audioBytes)
	        .build();

	    // Performs speech recognition on the audio file
	    RecognizeResponse response = speech.recognize(config, audio);
	    List<SpeechRecognitionResult> results = response.getResultsList();
	   
	    try {
			speech.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    if(results.isEmpty()) {
	    		throw new EmptyResultException();
	    }
	    
	    return results.get(0).getAlternativesList().get(0).getTranscript();

	}
	
	
}
