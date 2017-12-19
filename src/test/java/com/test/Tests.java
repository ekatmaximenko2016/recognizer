package com.test;

import static org.junit.Assert.assertEquals;

import java.nio.file.NoSuchFileException;

import org.apache.uima.exceptions.EmptyResultException;
import org.apache.uima.exceptions.FileNotExistException;
import org.apache.uima.tutorial.ex1.SpeechRecordLibrary;
import org.junit.Test;

import com.google.api.gax.rpc.InvalidArgumentException;

public class Tests {

	 	@Test
	    public void test_correct() throws EmptyResultException, FileNotExistException {
	 	   String fileName = "src/test/automob.wav";
	       SpeechRecordLibrary library =new SpeechRecordLibrary();
	       String res=library.recognize(fileName);
	       assertEquals(res, "раз-два-три-четыре-пять проект");
	    }
	 	
	 	@Test(expected = EmptyResultException.class)
	 	public void empty_recognize() throws EmptyResultException, FileNotExistException {
		 	   String fileName = "src/test/empty.wav";
		       SpeechRecordLibrary library =new SpeechRecordLibrary();
		       String res=library.recognize(fileName);
		       System.out.println(res);
		    }
	 	@Test(expected = FileNotExistException.class)
	 	public void non_exist_file() throws EmptyResultException, FileNotExistException {
		 	   String fileName = "src/test/non_exist.wav";
		       SpeechRecordLibrary library =new SpeechRecordLibrary();
		       String res=library.recognize(fileName);
		       System.out.println(res);
		    }
	 	
	 	@Test(expected = InvalidArgumentException.class)
	 	public void wrong_bit() throws EmptyResultException, FileNotExistException {
		 	   String fileName = "src/test/wrong_bit.wav";
		       SpeechRecordLibrary library =new SpeechRecordLibrary();
		       String res=library.recognize(fileName);
		       System.out.println(res);
		    }
	 	
	 	
	
}
