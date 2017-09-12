
public class SsmlHelper {

	public static final String CARDINAL = "cardinal";
	public static final String ORDINAL = "ordinal";
	public static final String DIGITS = "digits";
	public static final String FRACTION = "fraction";
	public static final String UNIT = "unit";
	public static final String DATE = "date";
	public static final String TIME = "time";
	public static final String TELEPHONE = "telephone";
	public static final String ADDRESS = "address";

	/**
	 * Wraps Speak Annotation to enable ssml support on alexa
	 * 
	 * @param s
	 * @return
	 */
	public static String wrapInSpeak(String s) {
		return "<speak>" + s + "</speak>";
	}

	/**
	 * 
	 * @param s
	 * @param type
	 * @return
	 */
	public static String interpretAs(String s, String type) {

		return "<say-as interpret-as=\"" + type + "\">" + s + "</say-as>";

	}

	/**
	 * Timed Break for specified Seconds
	 * 
	 * @param seconds
	 * @return
	 */
	public static String timedBreak(int seconds) {
		return "<break time=\"" + seconds + "s\"/>";

	}

	/**
	 * Standard Break "medium", like a
	 * 
	 * @return
	 */

	public static String sBreak() {
		return "<break/>";

	}

	/**
	 * Change Prosody
	 * 
	 * @return
	 */

	public static String prosody(String s, String value) {
		return "<prosody pitch=\"" + value + "\">" + s + "</prosody>";
	}

	/**
	 * Wrapps the phoneme Tags to the Output String and add the Ipa Information
	 * 
	 * @param input
	 * @return
	 */

	public static String phonemeIPA(String input) {
		String ipa = SsmlPronunciation.getIPA(input);
		return "<phoneme alphabet=\"ipa\" ph=\"" + ipa + "\">" + input + "</phoneme>";
	}

//	public static void main(String[] args) {
//		System.out.println(prosody("Tes", "+15%"));
//	}
}
