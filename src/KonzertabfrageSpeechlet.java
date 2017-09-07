

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;

public class KonzertabfrageSpeechlet implements Speechlet {


    private static final Logger log = LoggerFactory.getLogger(KonzertabfrageSpeechlet.class);

   	@Override
	public SpeechletResponse onIntent(IntentRequest arg0, Session arg1) throws SpeechletException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SpeechletResponse onLaunch(LaunchRequest arg0, Session arg1) throws SpeechletException {
		// TODO Auto-generated method stub
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