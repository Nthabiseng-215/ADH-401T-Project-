package tweet;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.util.ArrayList;
import java.util.List;

public class tweet {
	public static void main(String[] args) throws TwitterException
	{
		//call function here
		//System.out.println("i love you");
		
			sendTweet("hello lacturer");
		
	}
	
	public static Status sendTweet(String text) throws TwitterException{
		Status status = null;
		Twitter twitter = TwitterFactory.getSingleton();
		
		status = twitter.updateStatus(text);
		System.out.print("Successfully updated the status ["+status.getText()+"].");
		
		
		return status;
	}
	
	
	

}
