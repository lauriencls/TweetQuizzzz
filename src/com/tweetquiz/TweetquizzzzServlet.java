package com.tweetquiz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import com.model.tweet;


import twitter4j.*;
import twitter4j.auth.AccessToken;



public class TweetquizzzzServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	private static final Logger log = Logger.getLogger(TweetquizzzzServlet.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String playerName = req.getParameter("playerName");
		
		/*
		Filter propertyFilterPlayed = new FilterPredicate("IsAlreadyPlayed", FilterOperator.EQUAL,false);
		Query q = new Query("Tweet").setFilter(propertyFilterPlayed);
		List<Entity> tweets = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());
		
		int nb = tweets.size();
		Random r = new Random();
		int i = 0;
		
		resp.setContentType("application/json");    
		PrintWriter out = resp.getWriter();
		
		try{
			JSONArray jsonArrayWithTweets = new JSONArray();
			
			do{
				/*
				String author = (String) tweets.get(r.nextInt(nb)).getProperty("Autor");
				String content = (String) tweets.get(r.nextInt(nb)).getProperty("Content");
				String falseAuthor1 = (String) tweets.get(r.nextInt(nb)).getProperty("falseAuthor1");
				String falseAuthor2 = (String) tweets.get(r.nextInt(nb)).getProperty("falseAuthor2");
				String falseAuthor3 = (String) tweets.get(r.nextInt(nb)).getProperty("falseAuthor3");
				
				Boolean IsAlreadyPlayed = (Boolean) tweets.get(r.nextInt(nb)).getProperty("IsAlreadyPlayed");
				if (IsAlreadyPlayed.equals(false)){
					tweets.get(r.nextInt(nb)).setProperty("IsAlreadyPlayed",true);
					datastore.put(tweets);
				}

				tweet t = new tweet(author, content, falseAuthor1, falseAuthor2, falseAuthor3);
				
				JSONObject tweetJson = new JSONObject();
				tweetJson.put("author", t.getAuthor().toString());
				tweetJson.put("content", t.getContent().toString());
				tweetJson.put("falseAuthor1", t.getFalseAuthor1().toString());
				tweetJson.put("falseAuthor2", t.getFalseAuthor2().toString());
				tweetJson.put("falseAuthor3", t.getFalseAuthor3().toString());
				
				jsonArrayWithTweets.put(tweetJson);
				
				i++;
			}while(i!=10);
			
			out.print(jsonArrayWithTweets);
			out.flush();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		*/
		
		//CODE QUI MARCHE POUR L'EXEMPLE D'ENVOIE EN JSON
		resp.setContentType("application/json");    
		PrintWriter out = resp.getWriter();
		
		try{
			JSONArray jsonArrayWithTweets = new JSONArray();
			
			for(int i = 0; i<10;i++){
				tweet t = new tweet("author", "content", "falseAuthor1", "falseAuthor2", "falseAuthor3");

				
				JSONObject tweetJson = new JSONObject();
				tweetJson.put("author", t.getAuthor().toString());
				tweetJson.put("content", t.getContent().toString());
				tweetJson.put("falseAuthor1", t.getFalseAuthor1().toString());
				tweetJson.put("falseAuthor2", t.getFalseAuthor2().toString());
				tweetJson.put("falseAuthor3", t.getFalseAuthor3().toString());
				
				jsonArrayWithTweets.put(tweetJson);
			}
			
			out.print(jsonArrayWithTweets);
			out.flush();
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Connexion � un compte Twitter
	 */
	public Twitter connexion() {
		
    	Twitter twitter = new TwitterFactory().getInstance();

    	twitter.setOAuthConsumer("DGVfkSkL9v15unjXssXaAlkAU", "qkiM1kCTkX3gCXT8r3HpQCOE3dSPGLaSTXhnRoa3yhNKgiF8Cu");
		AccessToken myAccessToken = new AccessToken("258368311-qvQLVEtFy3GwAukTZrCme53gZbddu5hBrXfIfNYf","z5f6bsiE3YCVjVPUIJqQK2QL4o9NUc1ZbHUFVcWVgEfjJ");
		twitter.setOAuthAccessToken(myAccessToken);
		
		return twitter;
		
	}
	
	/*
	 * R�cup�ration des tweets d'un compte donn� via l'API de Twitter et enregistrement dans le dataStore
	 * @param String playerName : Nom du compte Twitter du joueur
	 * @return ArrayList<Tweet> listeTweets : Tweets r�cup�r�s et stock�s
	 */
	public void getTweetsFromTweeter(String playerName){		
		try{
			
			//Connexion sur le compte Twitter de base
			Twitter twitter = connexion();
			
			long cursor = -1;
			IDs ids;
			
			 do {
				 //Récupération des ID des comptes suivis par le joueur
				 ids = twitter.getFriendsIDs(playerName, cursor);
				
				 //Parcours des comptes suivis
				for (long id : ids.getIDs()) {
					
						User user = twitter.showUser(id);
						
						Paging paging = new Paging();
						paging.setCount(5);
						
						List<Status> lesStatuts = twitter.getUserTimeline(user.getScreenName(),paging);						
						//Boucle sur les statuts
						for (Status status : lesStatuts) {

							Filter propertyFilter = new FilterPredicate("Content", FilterOperator.EQUAL,status.getText());
							Query query = new Query("Tweet").setFilter(propertyFilter);
							List<Entity> entities = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
							if (entities.isEmpty()) {
								//Stockage du tweet
								Entity tweet = new Entity("Tweet");
								tweet.setProperty("ID", status.getId());
								tweet.setProperty("Autor", user.getName());
								tweet.setProperty("Content", status.getText());
								tweet.setProperty("IsAlreadyPlayed", false); //La question a déjà été posée
								ArrayList<String> falseAuthors = getFalseAuthors(ids, id);
								tweet.setProperty("falseAuthor1", falseAuthors.get(0));
								tweet.setProperty("falseAuthor2", falseAuthors.get(1));
								tweet.setProperty("falseAuthor3", falseAuthors.get(2));
								datastore.put(tweet);
							}else{
								
								System.out.println("Le tweet existe déjà ");
							}
		
						}	
						
				}
			} while ((cursor = ids.getNextCursor()) != 0);
			
		} catch (TwitterException ex) {
		
		}
	}

	private ArrayList<String> getFalseAuthors(IDs ids, long id) {
		ArrayList<String> falseAuthors = new ArrayList<String>();
		int i = 0;
		int random;
		Twitter twitter = connexion();
		long[] idsReadable = ids.getIDs();
		int nbIds = idsReadable.length;
		Random rand = new Random();
		
		do{
			do{
				random = rand.nextInt(nbIds);
			} while (idsReadable[random] == id);
			try {
				User user = twitter.showUser(idsReadable[random]);
				falseAuthors.add(user.getScreenName());
			} catch (TwitterException e) {
				e.printStackTrace();
			}

			i++;
		} while (i < 3);
		
		return falseAuthors;
	}
}
