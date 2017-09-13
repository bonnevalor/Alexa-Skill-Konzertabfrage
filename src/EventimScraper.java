import java.io.IOException;
import java.util.ArrayList;

import javax.print.Doc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import generated.eventim.ParsedEvent;

public class EventimScraper {
	
	public static void main(String[] args) {
		getConcertList("Rolling Stones");
	}

	public static String getArtistEventOverviewPageUrl(String name) {
		// get jsoup doc
		Document doc = null;
		try {
			doc = Jsoup.connect("http://www.eventim.de/Tickets.html?affiliate=EVE&fun=search&fuzzy=yes"
					+ "&doc=search&action=grouped&inline=false&suchbegriff=" + name + "&x10=2&x11=" + name
					+ "&x14=2&btn=true&group=artist").get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// get relevant elements
		/**
		 * Eigentlich müssen wir das ganze mit class artist und so nicht mehr machen, da
		 * wir "&group=artist" entdeckt haben. aber so funktioniert es auch und wir
		 * konzentrieren uns erstmal auf weitere features, bevor wir das ändern. Zeit
		 * ist geld und so :-)
		 * 
		 * @author Florian
		 */
		Elements titelElements = doc.getElementsByAttributeValueContaining("class", "artist");

		// make an arraylist to send to bestMatchIndex
		ArrayList<String> titelArray = new ArrayList<String>();
		for (Element a : titelElements) {
			Elements itemHeader = a.getElementsByClass("item-header");

			for (Element b : itemHeader) {
				titelArray.add(b.text());
			}
		}
		// get the best match
		int bestMatchIndex = LevenshteinDistance.bestMatchIndex(name, titelArray);
		String bestMatch = titelArray.get(bestMatchIndex);
		// our array starts at 0, the website starts at 1
		Elements idContainer = doc.getElementsByAttributeValueContaining("id", "artist-" + (bestMatchIndex + 1) + "-");
		String artistId = idContainer.attr("id").replace("artist-" + (bestMatchIndex + 1) + "-", "");

		String artistEventOverviewPageUrl = "http://www.eventim.de/" + bestMatch
				+ "-tickets.html?affiliate=EVE&doc=artistPages/tickets&fun=artist&action=tickets&kuid=" + artistId;
		System.out.println(artistEventOverviewPageUrl);

		return artistEventOverviewPageUrl;

	}

	/**
	 * Diese methode gibt eine ArrayList aus, welche arrays aus Veranstaltungsort
	 * und -Datum enthält. Ort ist an erster Stelle, Datum an zweiter Stelle.
	 * 
	 * @author Florian
	 * @param name
	 * @return
	 */

	public static ArrayList<ParsedEvent> getConcertList(String name) {
		ArrayList<ParsedEvent> eventArray = new ArrayList<ParsedEvent>();
		String url = getArtistEventOverviewPageUrl(name);
		return getConcertListFromUrl(eventArray,url);
	}

	public static ArrayList<ParsedEvent> getConcertListFromUrl(ArrayList<ParsedEvent> eventArray,String url) {
		
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Elements artistElements = doc.getElementsByAttributeValue("class", "selectionList results");
		// System.out.println(artistElements);
		// System.out.println(artistElements.isEmpty());
		if (artistElements.isEmpty()) {
			Elements eventElements = doc.getElementsByAttributeValue("class", "teaserText");
			for (Element b : eventElements) {
				String newUrl = "http://www.eventim.de/"+b.getElementsByAttribute("href").attr("href");
				getConcertListFromUrl(eventArray,newUrl);
			}
		} else {
			Elements test = doc.getElementsByAttributeValue("type", "application/ld+json");
			System.out.println(test);
			for (Element x : test) {
				System.out.println(x.data());
				String eventJson = x.data();
				ObjectMapper mapper = new ObjectMapper();
				ParsedEvent obj = null;
				try {
					obj = mapper.readValue(eventJson, ParsedEvent.class);
				} catch (JsonParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (JsonMappingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				eventArray.add(obj);
				System.out.println(obj.name +obj.startDate+ obj.location.name+obj.location.address);
			}
			// for (Element a : artistElements) {
			// Elements events = a.getElementsByAttributeValue("valign", "middle");
			//
			// // System.out.println(events);
			// int index = 0;
			// for (Element b : events) {
			// String title = "test";
			// String location = b.getElementsByAttributeValue("class", "textS
			// col-location").text();
			// String date = b.getElementsByAttributeValue("class", "textS
			// col-date").text();
			//
			// Event e = new Event(title, location, date);
			//
			// eventArray.add(e);
			//
			// }
			//
			// }
			// }
			// for (Event e : eventArray) {
			// // System.out.println(e.location + e.date);
			// }

		}
		
	
		return eventArray;
	}

}
