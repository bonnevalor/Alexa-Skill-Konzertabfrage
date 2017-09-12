import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import generated.apiLastFM.Artist_;
//import generated.Entry;
import generated.apiLastFM.Entry;
import generated.apiLastFM.Tag;

public class LastFM {

	// Api Key for LastFM api
	private final String API_KEY = "443dbedaa3a878fd0ddd50d469ef77cf";
	// API Root URL
	private final String ROOT_URL = "http://ws.audioscrobbler.com/2.0/?";
	// Api Settings
	public final String AUTOCORRECT = "1";
	// Limit for the answers
	public final String LIMIT = "3"; // minimum is 3
	// Error return String
	public final String RETRIEVAL_FAILED = "LASTFM:Invalid or missing response";
	// Bool value of RETRIEVAL_FAILED. Normally false if no error occurs
	private Boolean boolRetrieval = false;
	// Generated InfoArtist Obj
	private Entry obj;
	// Artist
	String artist;
	// List of German Tags
	String[] germanTags = { "german", "deutsch", "austrian" };

	// Constructor
	LastFM(String artist) {
		this.artist = artist;
		ObjectMapper mapper = new ObjectMapper();
		String queryUrl = getQueryStringInfoArtist(artist);

		try {
			obj = mapper.readValue(new URL(queryUrl), Entry.class);
		} catch (IOException e) {
			// e.printStackTrace();
			boolRetrieval = true;
		}

	}

	/**
	 * This Method uses the LASTFM Api to return a ArrayList containing the Similar
	 * artists divided by comma
	 * 
	 * @author flo, mbeckert
	 * @param artist
	 * @return ArrayList with Strings
	 */
	public ArrayList<String> getSimilarArtistsList() {
		ArrayList<String> similarArtistsList = new ArrayList<String>();
		// If there is an Error with the Api Request, then the list will contain
		// RETRIEVAL_FAILED
		if (boolRetrieval) {
			similarArtistsList.add(RETRIEVAL_FAILED);
			return similarArtistsList;
		}
		for (Artist_ a : obj.artist.similar.artist) {
			similarArtistsList.add(a.name);
		}
		return similarArtistsList;
	}

	/**
	 * This Method checks if there is a tag like "german, deutsch, austrian etc." in
	 * the tag list of last FM. If there is such an tag, then it is true.
	 * 
	 * @author mbeckert
	 * @param artist
	 * @return
	 */

	public Boolean checkGerman() {
		for (Tag t : obj.artist.tags.tag) {
			int i = 0;
			while (i < germanTags.length) {
				if (t.name.toLowerCase().contains((germanTags[i].toLowerCase()))) {
					return true;
				}
				i++;
			}
		}
		return false;
	}

	// Not Used Anymore
	// /**
	// * This Method builds the LAST FM Query String for the api-method
	// * "getsimilarartist"
	// *
	// * @author mbeckert
	// * @param artist
	// * @return String queryurl
	// */
	// private static String getQueryStringSimilarArtist(String artist) {
	// String queryUrl = ROOT_URL + "method=artist.getsimilar" + "&limit=" + LIMIT +
	// "&autocorrect=" + AUTOCORRECT
	// + "&artist=" + artist + "&api_key=" + API_KEY + "&format=json";
	// return queryUrl;
	// }

	/**
	 * This Method builds the LAST FM Query String for the api-method
	 * "getartistinfo"
	 * 
	 * @author mbeckert
	 * @param artist
	 * @return String queryurl
	 */
	@SuppressWarnings("deprecation")
	private String getQueryStringInfoArtist(String artist) {
		String queryUrl = ROOT_URL + "method=artist.getinfo&artist=" + artist + "&autocorrect=" + AUTOCORRECT
				+ "&api_key=" + API_KEY + "&format=json";
		//Sometimes there is an error with spaces and the lastfm api
		queryUrl = queryUrl.replace(" ", "+");
		return queryUrl;
	}

//	public static void main(String[] args) {
//		LastFM lastfm = new LastFM("Timi Hendrix");
//
//		System.out.println("is German: " + lastfm.checkGerman());
//		System.out.println("Similar Artists: " + lastfm.getSimilarArtistsList());
//	}
}
