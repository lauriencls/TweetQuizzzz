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

import com.google.appengine.api.datastore.Entity;
import twitter4j.*;
import twitter4j.auth.AccessToken;

public class TweetquizzzzServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// DatastoreService datastore =
	// DatastoreServiceFactory.getDatastoreService();
	private Logger log = Logger.getLogger(TweetquizzzzServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		
		String keyword = req.getParameter("playerName");
		
		//Verify first char for '#'
		if ('#' != keyword.charAt(0)){
			keyword = "#"+keyword;
		} 
		
		
		
		try {
			JSONArray jsonArrayWithTweets = getTopTweetsFromTweeter(keyword);
			 if (jsonArrayWithTweets == null){
				 JSONArray jsonArrayWithError = new JSONArray();
				 JSONObject error = new JSONObject();
				 error.put("message", "Problème avec la récupération de tweets");
				 jsonArrayWithError.put(error);
				 out.print(jsonArrayWithError); 
				 out.flush();
			 } else {
				 out.print(jsonArrayWithTweets); 
				 out.flush();
			 }
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		/*
		 * //Récupération du nom de compte du joueur String playerName =
		 * req.getParameter("playerName"); log.info(playerName);
		 * 
		 * //Récupération des tweets pour le quiz List<Entity> tweetsPourLeQuiz
		 * = getTweetsQuiz(playerName);
		 * 
		 * int nombreDeTweets = tweetsPourLeQuiz.size();
		 * 
		 * PrintWriter out = resp.getWriter();
		 * 
		 * //Si on a moins de 10 tweets disponibles, on en récupère de nouveaux
		 * via Twitter if (nombreDeTweets < 10){
		 * 
		 * //Stockage de nouveaux tweets pour le joueur dans le dataStore
		 * getTweetsFromTweeter(playerName);
		 * 
		 * //Récupération des tweets pour le jeu tweetsPourLeQuiz =
		 * getTweetsQuiz(playerName); nombreDeTweets = tweetsPourLeQuiz.size();
		 * 
		 * //On a trouvé plus de 10 tweets disponibles, on les envoie au quiz if
		 * (nombreDeTweets >=10){ try { JSONArray jsonArrayWithTweets =
		 * listeTweetsVersJSONArray(tweetsPourLeQuiz);
		 * out.print(jsonArrayWithTweets); out.flush(); } catch (JSONException
		 * e) { e.printStackTrace(); } } else { //Pas assez de tweets
		 * disponibles, on en informe le joueur resp.getWriter().
		 * println("Vérifier le nom de votre compte, et la disponibilité des tweets associés"
		 * ); } } else {
		 * 
		 * //On a assez de tweets disponibles, on peut les stocker dans un
		 * JSONArray pour les envoyer au quiz try {
		 * 
		 * JSONArray jsonArrayTweetsQuiz =
		 * listeTweetsVersJSONArray(tweetsPourLeQuiz);
		 * out.print(jsonArrayTweetsQuiz); out.flush();
		 * 
		 * } catch (JSONException e) { e.printStackTrace(); } }
		 */
	}

	/*
	 * Récupération des tweets utilisés pour le quiz
	 * 
	 * @param String playerName : Nom du compte Twitter du joueur
	 * 
	 * @return List<Entity> : Liste des tweets qu'on utilisera pour le quiz
	 */
	private List<Entity> getTweetsQuiz(String playerName) {
		/*
		 * // Requête pour la récupération des tweets dans le datastore par
		 * playerName et pas déjà joués Filter playerFilter = new
		 * FilterPredicate("playerName", FilterOperator.EQUAL, playerName);
		 * Filter isAlreadyPlayedFilter = new FilterPredicate("isAlreadyPlayed",
		 * FilterOperator.EQUAL, false); Query q = new
		 * Query("entityTweet").setFilter(playerFilter).setFilter(
		 * isAlreadyPlayedFilter);
		 * 
		 * //Récupération des tweets du datastore return
		 * datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());
		 */
		return null;
	}

	/*
	 * Stockage de la liste de tweets dans un JSONArray
	 * 
	 * @param List<Entity> tweets : Ensemble des tweets à envoyer pour le quiz
	 * 
	 * @return JSONArray : Tableau JSON des tweets
	 */
	public JSONArray listeTweetsVersJSONArray(List<Entity> tweets) throws JSONException {
		/*
		 * JSONArray jsonArrayWithTweets = new JSONArray();
		 * 
		 * Random r = new Random(); int p = 0; int nb = tweets.size(); int x =
		 * r.nextInt(nb); do { String author = (String)
		 * tweets.get(x).getProperty("autor"); String content = (String)
		 * tweets.get(x).getProperty("content"); String falseAuthor1 = (String)
		 * tweets.get(x).getProperty("falseAuthor1"); String falseAuthor2 =
		 * (String) tweets.get(x).getProperty("falseAuthor2"); String
		 * falseAuthor3 = (String) tweets.get(x).getProperty("falseAuthor3");
		 * 
		 * Boolean isAlreadyPlayed = (Boolean)
		 * tweets.get(x).getProperty("isAlreadyPlayed");
		 * 
		 * //Mise à jour de l'attribut "isAlreadyPlayed" if
		 * (isAlreadyPlayed.equals(false)) {
		 * tweets.get(x).setProperty("isAlreadyPlayed", true); } x++;
		 * 
		 * Tweet t = new Tweet(author, content, falseAuthor1, falseAuthor2,
		 * falseAuthor3);
		 * 
		 * JSONObject tweetJson = new JSONObject(); tweetJson.put("author",
		 * t.getAuthor().toString()); tweetJson.put("content",
		 * t.getContent().toString()); tweetJson.put("falseAuthor1",
		 * t.getFalseAuthor1().toString()); tweetJson.put("falseAuthor2",
		 * t.getFalseAuthor2().toString()); tweetJson.put("falseAuthor3",
		 * t.getFalseAuthor3().toString());
		 * 
		 * jsonArrayWithTweets.put(tweetJson);
		 * 
		 * p++; } while (p != 10);
		 * 
		 * datastore.put(tweets);
		 * 
		 * return jsonArrayWithTweets;
		 */
		return null;
	}

	/*
	 * Connexion à un compte Twitter
	 * 
	 * @return Twitter : Objet Twitter après connexion
	 */
	public Twitter connexion() {

		Twitter twitter = new TwitterFactory().getInstance();

		twitter.setOAuthConsumer("DGVfkSkL9v15unjXssXaAlkAU", "qkiM1kCTkX3gCXT8r3HpQCOE3dSPGLaSTXhnRoa3yhNKgiF8Cu");
		AccessToken myAccessToken = new AccessToken("258368311-qvQLVEtFy3GwAukTZrCme53gZbddu5hBrXfIfNYf",
				"z5f6bsiE3YCVjVPUIJqQK2QL4o9NUc1ZbHUFVcWVgEfjJ");
		twitter.setOAuthAccessToken(myAccessToken);

		return twitter;

	}

	/*
	 * Récupération et stockage de tweets du compte donné en paramètre
	 * 
	 * @param String playerName : Nom du compte Twitter du joueur
	 * 
	 * @return ArrayList<Tweet> listeTweets : Tweets r�cup�r�s et stock�s
	 */
	public void getTweetsFromTweeter(String playerName) {
		/*
		 * try {
		 * 
		 * // Connexion sur le compte Twitter de base Twitter twitter =
		 * connexion();
		 * 
		 * long cursor = -1; IDs ids; int j = 0; int nbStatutsMax = 3;
		 * 
		 * do { // Récupération des ID des comptes suivis par le joueur ids =
		 * twitter.getFriendsIDs(playerName, cursor);
		 * 
		 * // Parcours des comptes suivis for (long id : ids.getIDs()) { User
		 * user = twitter.showUser(id); List<Status> lesStatuts =
		 * twitter.getUserTimeline(user.getScreenName());
		 * 
		 * for(int i=0; i<nbStatutsMax; i++) { Status currentStatut =
		 * lesStatuts.get(i);
		 * 
		 * // Stockage du tweet Entity tweet = new Entity("entityTweet");
		 * 
		 * tweet.setProperty("playerName", playerName);
		 * tweet.setProperty("author", user.getName());
		 * tweet.setProperty("content", currentStatut.getText());
		 * tweet.setProperty("isAlreadyPlayed", false);
		 * 
		 * //Récupération de faux auteurs pour faire les propositions
		 * ArrayList<String> falseAuthors = getFalseAuthors(ids, id);
		 * tweet.setProperty("falseAuthor1", falseAuthors.get(0));
		 * tweet.setProperty("falseAuthor2", falseAuthors.get(1));
		 * tweet.setProperty("falseAuthor3", falseAuthors.get(2));
		 * 
		 * //Stockage dans le datastore datastore.put(tweet);
		 * 
		 * }
		 * 
		 * if (j == 10){ break; } else { j ++; } }
		 * 
		 * } while ((cursor = ids.getNextCursor()) != 0 && j < 10);
		 * 
		 * } catch (TwitterException ex) { log.info(ex.getErrorMessage()); }
		 */
	}

	/*
	 * Renvoie une liste de faux auteurs pour un tweet
	 * 
	 * @param IDs ids
	 * 
	 * @param long id
	 * 
	 * @return ArrayList<String> : Liste des faux auteurs de tweets sous forme
	 * d'un tableau de String
	 */
	private ArrayList<String> getFalseAuthors(IDs ids, long id) {
		/*
		 * ArrayList<String> falseAuthors = new ArrayList<String>(); int i = 0;
		 * int random;
		 * 
		 * //Connexion à Twitter Twitter twitter = connexion();
		 * 
		 * long[] idsReadable = ids.getIDs(); int nbIds = idsReadable.length;
		 * Random rand = new Random();
		 * 
		 * do {
		 * 
		 * random = rand.nextInt(nbIds);
		 * 
		 * try { User user = twitter.showUser(idsReadable[random]);
		 * falseAuthors.add(user.getScreenName()); } catch (TwitterException e)
		 * { e.printStackTrace(); }
		 * 
		 * i++; } while (i < 3);
		 * 
		 * return falseAuthors;
		 */

		return null;
	}

	private JSONArray getTopTweetsFromTweeter(String keyword) throws JSONException {
		Twitter twitter = new TwitterFactory().getInstance();

		Query query = new Query(keyword.toString());
		query.setCount(10);
		QueryResult qr = null;
		
		try {
			qr = twitter.search(query);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		List<Status> qrTweets = qr.getTweets();
		
		JSONArray jsonArrayWithTweets = getTweetsAsJSONArray(qrTweets);
		
		return jsonArrayWithTweets;
	}

	private JSONArray getTweetsAsJSONArray(List<Status> qrTweets) throws JSONException {
		JSONArray jsonArrayWithTweets = new JSONArray();
		
		if (qrTweets.size() != 10){
			jsonArrayWithTweets = null;
		} else {
			Random r = new Random();
			ArrayList<String> falseAuthors = new ArrayList<String>();
			
			for (int i = 0; i < qrTweets.size(); i++) {
				Status s = qrTweets.get(i);
				int k = 0;
				while (k < 3){
					int j = r.nextInt(qrTweets.size());
					if (j != i){
						
						falseAuthors.add(qrTweets.get(j).getUser().getScreenName());
						k++;
					}
				}
				
				JSONObject tweetJson = new JSONObject();
				tweetJson.put("author", s.getUser().getScreenName());
				tweetJson.put("content", s.getText());
				tweetJson.put("falseAuthor1", falseAuthors.get(0));
				tweetJson.put("falseAuthor2", falseAuthors.get(1));
				tweetJson.put("falseAuthor3", falseAuthors.get(2));
				
				jsonArrayWithTweets.put(tweetJson);
			}
		}
		
		return jsonArrayWithTweets;
	}
}
