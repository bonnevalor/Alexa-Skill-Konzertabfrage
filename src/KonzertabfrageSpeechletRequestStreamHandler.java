import java.util.HashSet;
import java.util.Set;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

/**
 * This is my Main class, for starting the lambda function.
 * 
 * @author mbeckert
 *
 */

public class KonzertabfrageSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {

	// Konstruktor
	public KonzertabfrageSpeechletRequestStreamHandler() {
		super(new KonzertabfrageSpeechlet(), supportedApplicationIds);
	}

	private static final Set<String> supportedApplicationIds = new HashSet<String>();
	static {
		/*
		 * This Id can be found on https://developer.amazon.com/edw/home.html#/ "Edit"
		 * the relevant Alexa Skill and put the relevant Application Ids in this Set.
		 */
    	
    	//Id von Flo
		supportedApplicationIds.add("amzn1.ask.skill.d530b7c3-4cae-494b-995a-aee924c5ac1d");
		supportedApplicationIds.add("amzn1.ask.skill.a1926dab-ac4e-4c20-91c4-bb0fd1aea8d5");
    	//Id von Markus
		supportedApplicationIds.add("amzn1.ask.skill.5725a42e-91e3-47f9-b9fb-99cda946ab8f");
		
	}

}
