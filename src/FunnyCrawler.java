
// Source: http://www.mkyong.com/java/jsoup-send-search-query-to-google/

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FunnyCrawler {

		public ArrayList<String> getDataFromGoogle(String query) {

			Set<String> result = new HashSet<String>();
			String request = "https://www.google.com/search?q=" + query + "&num=30";

			try {

					// need http protocol, set this as a Google bot agent :)
					Document doc = Jsoup.connect(request).userAgent("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)").timeout(5000).get();

					// get all links
					Elements links = doc.select("a[href]");
					for (Element link : links) {

						String temp = link.attr("href");
						if (temp.startsWith("/url?q=") && !temp.contains("webcache.googleusercontent")) {
								// use regex to get domain name
								result.add(temp.substring(7));
						}

					}

			} catch (IOException e) {
					e.printStackTrace();
			}
			ArrayList<String> resultList = new ArrayList<String>(result);
			return resultList;
		}

}