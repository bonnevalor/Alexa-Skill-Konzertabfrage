
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;

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
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.amazon.speech.ui.SsmlOutputSpeech;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

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

	/**
	 * For now this method handles all Intents, but the actual work done is the
	 * handling of 'artistIntent'
	 * 
	 * @author Florian
	 */
	public SpeechletResponse onIntent(IntentRequest intentRequest, Session session) throws SpeechletException {

		Intent intent = intentRequest.getIntent();
		String artistName = intent.getSlot("artist").getValue();
		LastFM lastfm = new LastFM(artistName);
		ArrayList<String> similarAtristsList = lastfm.getSimilarArtistsList();
		SpeechletResponse speechletResponse = new SpeechletResponse();

		SsmlOutputSpeech antwort = new SsmlOutputSpeech();
		if (similarAtristsList.contains(lastfm.RETRIEVAL_FAILED)) {
			antwort.setSsml(SsmlHelper.wrapInSpeak(
					"<emphasis level=\\\"strong\\\"> Fack! </emphasis> <p> Es ist ein Fehler bei der Abfrage aufgetreten.</p> <p> <say-as interpret-as=\\\"interjection\\\">ohne scheiß.</say-as> </p> <p> Bitte kontaktieren sie den Entwickler.</p>"));

		} else {

			String antwortString = "";

			for (int i = 0; i < similarAtristsList.size() - 1; i++) {
				LastFM sArtist = new LastFM(similarAtristsList.get(i));
				if (sArtist.checkGerman()) {
					antwortString = antwortString + similarAtristsList.get(i) + "<break/>";
				} else {
					antwortString = antwortString + SsmlHelper.phonemeIPA(similarAtristsList.get(i)) + "<break/>";
				}

			}

			antwortString = antwortString + "sowie " + similarAtristsList.get(similarAtristsList.size() - 1);
			String sArtistName;
			if (lastfm.checkGerman()) {
				sArtistName = artistName;

			} else {
				sArtistName = SsmlHelper.phonemeIPA(artistName);
			}
			antwort.setSsml(SsmlHelper.wrapInSpeak(SsmlHelper.prosody("Ähnliche", "+15%") + " Künstler zu "
					+ sArtistName + "sind beispielsweise: " + antwortString));

			// mal schaun
			speechletResponse.setOutputSpeech(antwort);

		}

		/**
		 * This part generates a Card Response, with a reference to the searched term in
		 * the Title and the answers in the content.
		 * 
		 * @author Florian
		 */
		SimpleCard card = new SimpleCard();
		card.setTitle("Ähnliche Künstler zu: " + artistName);

		String cardContent = "";
		for (int i = 0; i < similarAtristsList.size(); i++) {
			cardContent = cardContent + similarAtristsList.get(i) + "\n \r\n";
		}
		cardContent = cardContent + "\r\n Diese Informationen stammen von Last.fm";

		card.setContent(cardContent);

		speechletResponse.setCard(card);
		return speechletResponse;
	}

	@Override
	public SpeechletResponse onLaunch(final LaunchRequest request, final Session session) throws SpeechletException {
		log.info("onLaunch requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());
		return getWelcomeResponse();

	}

	@Override
	public void onSessionEnded(SessionEndedRequest arg0, Session arg1) throws SpeechletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSessionStarted(SessionStartedRequest arg0, Session arg1) throws SpeechletException {
		// TODO Auto-generated method stub

	}

	/**
	 * Geeting Phrase at the Begining of the Skill
	 * 
	 * @author mbeckert
	 * 
	 */

	private SpeechletResponse getWelcomeResponse() {
		// Create the welcome message.
		String speechText = "Willkommen.";
		// "Willkommen bei Konzertor. Welche band wollen sie live sehen?";
		String repromptText = "Welche Band gefällt Ihnen?";

		return getSpeechletResponse(speechText, repromptText, true);
	}

	private SpeechletResponse getSpeechletResponse(String speechText, String repromptText, boolean isAskResponse) {
		// Create the Simple card content.
		SimpleCard card = new SimpleCard();
		card.setTitle("Session");
		card.setContent(speechText);

		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		if (isAskResponse) {
			// Create reprompt
			PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
			repromptSpeech.setText(repromptText);
			Reprompt reprompt = new Reprompt();
			reprompt.setOutputSpeech(repromptSpeech);

			return SpeechletResponse.newAskResponse(speech, reprompt, card);

		} else {
			return SpeechletResponse.newTellResponse(speech, card);
		}
	}

}