
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;

public class KonzertabfrageSpeechlet implements Speechlet {

	private static final Logger log = LoggerFactory.getLogger(KonzertabfrageSpeechlet.class);

	/**
	 * Diese methoden werden aus Nostalgiegründen als Kommentare Behalten.
	 * 
	 * @author Florian
	 */

	// @Override
	// public SpeechletResponse onIntent(IntentRequest arg0, Session arg1) throws
	// SpeechletException {
	// // Retrieves the name of our ubntet IT SHOULD BE : $HALLO
	// Intent i = arg0.getIntent();
	// String intent_name = i.getName();
	// System.out.println(intent_name);
	// String content_of_slot = i.getSlot("name").getValue();
	//
	// // We have to return a SpeechletResponse -> SEE FUNCTION DECLARATION
	// SpeechletResponse r = new SpeechletResponse();
	// // ere are different types of outputspeeches, plain-text, with phone
	// PlainTextOutputSpeech text = new PlainTextOutputSpeech();
	// if (content_of_slot.equals("Markus") || content_of_slot.equals("markus") ||
	// content_of_slot.equals("Marcus")
	// || content_of_slot.equals("marcus")) {
	//
	// text.setText(content_of_slot
	// + " besitzt keine Autorisierung für dieses Gelände. Sicherheitsdienst
	// verständigt. Bitte bleiben Sie vor Ort. Sie werden umgehend gelöscht.
	// Verzeihung, ich meine vom Gelände eskortiert.");
	// } else {
	// text.setText(content_of_slot + " autorisiert. Herzlich willkommen!");
	// }
	// r.setOutputSpeech(text);
	// return r;
	// }
	//
	// @Override
	// public SpeechletResponse onLaunch(LaunchRequest arg0, Session arg1) throws
	// SpeechletException {
	// // TODO Auto-generated method stub
	//
	// SpeechletResponse r = new SpeechletResponse();
	// PlainTextOutputSpeech text = new PlainTextOutputSpeech();
	// text.setText("Piep piep piep");
	// r.setOutputSpeech(text);
	// return r;
	// }

	@Override
	public SpeechletResponse onIntent(IntentRequest arg0, Session arg1) throws SpeechletException {

		//Noch einfügen!
		return null;
	}

	@Override
	public SpeechletResponse onLaunch(LaunchRequest arg0, Session arg1) throws SpeechletException {
		
		//NOCH EINFÜGEN
		return null;
		
	}

	@Override
	public void onSessionEnded(SessionEndedRequest arg0, Session arg1) throws SpeechletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSessionStarted(SessionStartedRequest arg0, Session arg1) throws SpeechletException {
		// TODO Auto-generated method stub

	}
}