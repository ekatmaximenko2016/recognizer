/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.uima.tutorial.ex1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.exceptions.EmptyResultException;
import org.apache.uima.exceptions.FileNotExistException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.tutorial.DateAnnot;
import org.apache.uima.tutorial.Meeting;
import org.apache.uima.tutorial.Recognited;
import org.apache.uima.tutorial.RoomNumber;
import org.apache.uima.tutorial.TimeAnnot;

import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.protobuf.ByteString;

/**
 * Example annotator that detects room numbers using Java 1.4 regular expressions.
 */
public class SpeechRecognitionAnnotator extends JCasAnnotator_ImplBase {

  /**
   * @see JCasAnnotator_ImplBase#process(JCas)
   */
  public void process(JCas aJCas) {
  
	  // get annotation indexes
	  
	  String fileName = "src/test/automob.wav";

	    
	  	SpeechRecordLibrary speech = null;
		try {
			speech = new SpeechRecordLibrary();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	  	String resRecog = null;
		try {
			resRecog = speech.recognize(fileName);
		} catch (EmptyResultException | FileNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	    
	      Recognited recogRes = new Recognited(aJCas);
	      recogRes.setRecognitionResult(resRecog);
	      recogRes.addToIndexes();
	    
	  
	  
	  
	   
	//	FSIndex roomNumberIndex = aJCas.getAnnotationIndex(RoomNumber.type);
	  
		

	 
	//    int lastMeetingEnd = -1;

	  
	//    Iterator roomNumberIter = roomNumberIndex.iterator();
	//    while (roomNumberIter.hasNext()) {
	//      RoomNumber room = (RoomNumber) roomNumberIter.next();
          // annotate
    //      Recognited recogRes = new Recognited(aJCas);
    //      recogRes.setRecognitionResult("oh my GGGGGGOOOD");
    //      recogRes.addToIndexes();
	  
	//  }
	  
	  
  }

}
