import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class SsmlPronunciation extends SsmlHelper {

	// API Root URL
	private static final String ROOT_URL = "http://lingorado.com/ipa/";
	// Api Settings
	public static final String RETRIEVAL_FAILED = "PronunciationHandler:Invalid or missing response";

	/**
	 * this method connects with http://lingorado.com/ipa/ to get the british english ipa code of words
	 * @author mbeckert
	 * @param input
	 * @return String
	 */
	
	public static String getIPA(String input) {

		Document doc = null;
		try {
			doc = Jsoup.connect(ROOT_URL).data("text_to_transcribe", input).data("submit", "Show transcription")
					.post();
		} catch (IOException e) {
			// e.printStackTrace();
			return RETRIEVAL_FAILED;
		}
		Elements ipa = doc.getElementsByClass("transcribed_word");
		// System.out.println(ipa.text());
		return ipa.text();

	}

//	public static void main(String[] args) {
//		System.out.println(getIPA("Iron Maiden"));
//	}

}
