import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import generated.Artist;
import generated.Entry;

public class LastFM {
	
	// Api Key for LastFM api
	private static final String API_KEY = "443dbedaa3a878fd0ddd50d469ef77cf";
	//API Root URL
	private static final String ROOT_URL = "http://ws.audioscrobbler.com/2.0/?";
	//Api Settings
	public static final String AUTOCORRECT = "1"; 
	public static final String LIMIT = "3";  //minimum is 3
	
	
	public static final String RETREVEAL_FAILED = "LASTFM:Invalid or missing response";
	

	private LastFM() {	
	}

	// JSON from URL to Object

	public static String getSimilarArtist(String artist) {
		ObjectMapper mapper = new ObjectMapper();
		String similarArtist = " ";
		String queryUrl = getQueryStringSimilarArtist(artist);
		Entry obj;
		try {
			obj = mapper.readValue(new URL(queryUrl), Entry.class);
		} catch (IOException e) {
			
			//e.printStackTrace();
			return RETREVEAL_FAILED;
		}
		for (Artist a : obj.similarartists.artist) {
			//System.out.println(a.name);
			similarArtist += a.name+", ";
		}
		return similarArtist;
	}
	
	private static String getQueryStringSimilarArtist(String artist) {
		String queryUrl= ROOT_URL+"method=artist.getsimilar"+"&limit="+LIMIT+"&autocorrect="+AUTOCORRECT+"&artist="+artist+"&api_key="+API_KEY+"&format=json";
		return queryUrl;
	}
	
//	public static void main(String[] args) {
//		//System.out.println(getQueryStringSimilarArtist("Madsen"));
//		System.out.println(getSimilarArtist("Madsen"));
//		
//	}
}
