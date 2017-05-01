package com.tweetquiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.model.tweet;

import twitter4j.IDs;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;

@SuppressWarnings("serial")
public class TweetquizzzzServlet extends HttpServlet {
	
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	/*
	 * Méthode de base du servlet
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String playerName = req.getParameter("playerName");
		resp.getWriter().println(playerName);
		
		//Si user déjà présent dans datastore
			//tweets encore disponibles en nombre (10)?
				//Envoie des 10 tweets
			//Sinon pas suffisament de tweets
				//Récupéreration de tweets et envoie
		
		//Sinon pas présent dans le datastore
			//Si User passé en paramêtre pssède un compte valide sur twitter
				//récupération de tweets et envoie
			//Sinon compte invlide
				//Message utilisateur 		
	}
	
	/*
	 * Connexion à un compte Twitter
	 */
	public Twitter connexion() {
		
    	Twitter twitter = new TwitterFactory().getInstance();

    	twitter.setOAuthConsumer("DGVfkSkL9v15unjXssXaAlkAU", "qkiM1kCTkX3gCXT8r3HpQCOE3dSPGLaSTXhnRoa3yhNKgiF8Cu");
		AccessToken myAccessToken = new AccessToken("258368311-qvQLVEtFy3GwAukTZrCme53gZbddu5hBrXfIfNYf","z5f6bsiE3YCVjVPUIJqQK2QL4o9NUc1ZbHUFVcWVgEfjJ");
		twitter.setOAuthAccessToken(myAccessToken);
		
		return twitter;
		
	}
	
	/*
	 * Récupération des tweets d'un compte donné via l'API de Twitter et enregistrement dans le dataStore
	 * @param String playerName : Nom du compte Twitter du joueur
	 * @return ArrayList<Tweet> listeTweets : Tweets récupérés et stockés
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
							
							//Stockage du tweet
							Entity tweet = new Entity(user.getScreenName());
							tweet.setProperty("ID", status.getId());
							tweet.setProperty("Autor", user.getName());
							tweet.setProperty("Content", status.getText());
							tweet.setProperty("IsAlreadyPlayed", false); //La question a déjà été posée
							ArrayList<String> falseAuthors = getFalseAuthors(ids, id);
							tweet.setProperty("falseAuthor1", falseAuthors.get(0));
							tweet.setProperty("falseAuthor2", falseAuthors.get(1));
							tweet.setProperty("falseAuthor3", falseAuthors.get(2));
							datastore.put(tweet);
		
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
