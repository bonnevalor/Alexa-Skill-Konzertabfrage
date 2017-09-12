import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class EventimScraper {

	public static void main(String[] args) {
		getArtistEventOverviewPageUrl("Rolling stones");
	}

	public static String getArtistEventOverviewPageUrl(String name) {
		// get jsoup doc
		Document doc = null;
		try {
			doc = Jsoup.connect("http://www.eventim.de/Tickets.html?affiliate=EVE&fun=search&fuzzy=yes"
					+ "&doc=search&action=grouped&inline=false&suchbegriff=" + name + "&x10=2&x11=" + name
					+ "&x14=2&btn=true").get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// get relevant elements

		Elements titelElements = doc.getElementsByAttributeValue("id", "searchResultList");
		Elements test = doc.select("Künstler");
		test.get(0).siblingElements();
test.get(0).
		int künstlerIndex = 0;
		while (titelElements.get(künstlerIndex).text().contains("Künstler") != true) {
			künstlerIndex++;
		}

		for (int i = 0; i <= künstlerIndex; i++) {
			titelElements.remove(0);
		}

		System.out.println(titelElements);
		// make an arraylist to send to bestMatchIndex
		ArrayList<String> titelArray = new ArrayList<String>();
		for (Element a : titelElements) {
			titelArray.add(a.text());
		}

		// get the best match
		int bestMatchIndex = LevenshteinDistance.bestMatchIndex(name, titelArray);
		String bestMatch = titelArray.get(bestMatchIndex);

		Elements idContainer = doc.getElementsByAttributeValueContaining("id", "artist-" + bestMatchIndex + "-");

		String artistId = idContainer.attr("id").replace("artist-" + bestMatchIndex + "-", "");

		String artistEventOverviewPageUrl = "http://www.eventim.de/" + bestMatch
				+ "-tickets.html?affiliate=EVE&doc=artistPages/tickets&fun=artist&action=tickets&kuid=" + artistId;
		System.out.println(artistEventOverviewPageUrl);
		return artistEventOverviewPageUrl;

	}

}
