import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class PronunciationHandler {

	// API Root URL
	private static final String ROOT_URL = "http://lingorado.com/ipa/";
	// Api Settings
	public static final String RETRIEVAL_FAILED = "PronunciationHandler:Invalid or missing response";

	/**
	 * Diese Klasse Verbindet sich mit http://lingorado.com/ipa/ um die IPA Codierung eines englischen Wortes zu ermitteln
	 * @author mbeckert
	 * @param artist
	 * @return String
	 */
	
	public static String getIPA(String artist) {

		Document doc = null;
		try {
			doc = Jsoup.connect(ROOT_URL).data("text_to_transcribe", artist).data("submit", "Show transcription")
					.post();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return RETRIEVAL_FAILED;
		}
		Elements ipa = doc.getElementsByClass("transcribed_word");
		// System.out.println(ipa.text());
		return ipa.text();

	}

	public static void main(String[] args) {
		System.out.println(getIPA("Iron Maiden"));
	}

}
