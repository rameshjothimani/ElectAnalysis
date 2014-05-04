package com.twitter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class SearchTweets {
 
    private static final String CONSUMER_KEY = "ppZcFjFOKmMo16dxazgKu9bLH";
	private static final String CONSUMER_SECRET = "DxzaIDaIOYEUGc9hymyqQvqZKaX2QPeH2Arp9TY4cVd1YJCeTN";
	private static final String ACCESS_KEY = "80581835-DVDri11kk38XLWCDwFFx1XIjJ8hgjzUoOJIZ7Jefx";
	private static final String ACCESS_SECRET = "RCPoxugrLIMCqDeN73qvChENcOieIlAblnQWaIckF8B7C";
    
	
	
	public static void main(String[] args) throws IOException 
    {
		
		SearchTweets searchObj=new SearchTweets();
		searchObj.candidateAnalysis();
		searchObj.partyAnalysis();
		
    }
	public void selector() throws IOException
	{
		SearchTweets Obj=new SearchTweets();
		System.out.println("**********Welcome to ElectAnalysis**********");
		System.out.println("");
		System.out.println("What are you interested in ?");
		System.out.println("1.Candidate Analysis");
		System.out.println("2.Party Analysis");
		System.out.println("");
		System.out.println("Enter the preference to proceed :");
		
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

	    int opt=Integer.parseInt(bufferRead.readLine());
	    
		switch(opt)
		{
		case 1:
			Obj.candidateAnalysis();
			break;
		case 2:
			Obj.candidateAnalysis();
			break;
		default:
			System.out.println("Invalid Choice");
			
		}
	}
	
	public void candidateAnalysis() throws IOException
	{
		
		SearchTweets tweetObj=new SearchTweets();
		
		File  namoObj = new File ("#NaMo.txt");
		File  kejObj = new File ("#ArvindKejriwal.txt");
		File  rahulObj = new File ("#RahulGandhi.txt");
		int namoCnt=0;
		int kejCnt=0;
		int rahulCnt=0;
		
		int posNamoCnt=0;
		int posKejCnt=0;
		int posRahulCnt=0;
		
		int negNamoCnt=0;
		int negKejCnt=0;
		int negRahulCnt=0;
		
		int neuNamoCnt=0;
		int neuKejCnt=0;
		int neuRahulCnt=0;
		
		if(namoObj.exists()==false)
		{
          		namoObj.createNewFile();
		}
		if(kejObj.exists()==false)
		{
				kejObj.createNewFile();
		}
		if(rahulObj.exists()==false)
		{
				rahulObj.createNewFile();
		}
		ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET)
                .setOAuthAccessToken(ACCESS_KEY)
                .setOAuthAccessTokenSecret(ACCESS_SECRET);

        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        
        String policianHashTags[]={"#NaMo","#ArvindKejriwal","#RahulGandhi"};
        String positive[]={"good","best","better"};
        String negative[]={"bad","worst","defeat","loss","losing"};
        
        int positiveLen=positive.length;
        int negativeLen=negative.length;
        
        int totalPoliticians=policianHashTags.length;
        String temp=null;
        PrintWriter out=null;
        try 
        {        	
        	
        	
        	for(int i=0;i<totalPoliticians;i++)
        	{	
        		Query query = new Query(policianHashTags[i]);
        		//query.setResultType(Query.POPULAR);
        		query.setCount(100);
        		QueryResult result = twitter.search(query);
        		for (Status status : result.getTweets())
        		{
        			out = new PrintWriter(new FileWriter(policianHashTags[i]+".txt", true));
        			out.append(System.lineSeparator());
        			out.append(status.getText());
        			
        			/*Check for positive tweets*/
        			for(int j=0;j<positiveLen;j++)
        			{
        				if(status.getText().contains(positive[j]))
        				{
        					if(i==0)
                			{
        						posNamoCnt++;
                			}
                			if(i==1)
                			{
                				posKejCnt++;
                			}
                			if(i==2)
                			{
                				posRahulCnt++;
                			}
        				}
        				
        				
        				else
        				{
        					if(i==0)
                			{
        						neuNamoCnt++;
                			}
                			if(i==1)
                			{
                				neuKejCnt++;
                			}
                			if(i==2)
                			{
                				neuRahulCnt++;
                			}
        					
        				}
        			}
        			
        			
        			/*Check for negative tweets*/
        			
        			for(int k=0;k<negativeLen;k++)
        			{
        				if(status.getText().contains(negative[k]))
        				{
        					if(i==0)
                			{
        						negNamoCnt++;
                			}
                			if(i==1)
                			{
                				negKejCnt++;
                			}
                			if(i==2)
                			{
                				negRahulCnt++;
                			}
        				}
        				
        				else
        				{
        					if(i==0)
                			{
        						neuNamoCnt++;
                			}
                			if(i==1)
                			{
                				neuKejCnt++;
                			}
                			if(i==2)
                			{
                				neuRahulCnt++;
                			}
        					
        				}
        			}
        			
        			
        			
        			out.close();
        			
        		}
        	}
        	
        	
        	/*Check for the popular tweets*/
        	
        	for(int j=0;j<totalPoliticians;j++)
        	{	
        		Query sortquery = new Query(policianHashTags[j]);
        		sortquery.setResultType(Query.POPULAR);
        		sortquery.setCount(100);
        		QueryResult sortresult = twitter.search(sortquery);
        		for (Status sortStatus : sortresult.getTweets())
        		{
        			if(j==0)
        			{
        				namoCnt++;
        			}
        			if(j==1)
        			{
        				kejCnt++;
        			}
        			if(j==2)
        			{
        				rahulCnt++;
        			}
        			
        		}
        		
        		
        	}

        	System.out.println("****************************************************");
        	System.out.println("\t Narendra Modi");
        	System.out.println("****************************************************");
        	System.out.println("");
        	System.out.println("Positive Tweets:"+posNamoCnt);
        	System.out.println("Negative Tweets:"+negNamoCnt);
        	System.out.println("");
        	System.out.println("");
        	
        	System.out.println("****************************************************");
        	System.out.println("\t Aravind Kejriwal");
        	System.out.println("****************************************************");
        	System.out.println("");
        	System.out.println("Positive Tweets:"+posKejCnt);
        	System.out.println("Negative Tweets:"+negKejCnt);
        	System.out.println("");
        	System.out.println("");
        	
        	
        	System.out.println("****************************************************");
        	System.out.println("\t Rahul Gandhi");
        	System.out.println("****************************************************");
        	System.out.println("");
        	System.out.println("Positive Tweets:"+posRahulCnt);
        	System.out.println("Negative Tweets:"+negRahulCnt);
        	System.out.println("");
        	System.out.println("");
        	System.out.println("****************************************************");
        	System.out.println("");
        	System.out.println("");
        	
        	if(namoCnt>=kejCnt&&namoCnt>=rahulCnt)
        	{
        		System.out.println("");
        		System.out.println("Narendra Modi is most talked in twitter");
        		System.out.println("Discussed by "+namoCnt+ " People");
        	}
        	if(kejCnt>=namoCnt&&kejCnt>=rahulCnt)
        	{
        		System.out.println("");
        		System.out.println("Arvind Kejriwal is most talked in twitter");
        		System.out.println("Discussed by "+kejCnt+ " People");
        	}
        	if(rahulCnt>=kejCnt&&rahulCnt>=namoCnt)
        	{
        		System.out.println("");
        		System.out.println("Rahul Gandhi is most talked in twitter");
        		System.out.println("Discussed by "+rahulCnt+ " People");
        	}
		
		} 
        
    
        catch (TwitterException e) 
        {
			e.printStackTrace();
		}
   
        finally
        {
        	//tweetObj.selector();
        }
	}
	
	
	
	public void partyAnalysis() throws IOException
	{

		SearchTweets tweetObj=new SearchTweets();
		
		File  namoObj = new File ("#BJP.txt");
		File  kejObj = new File ("#AAP.txt");
		File  rahulObj = new File ("#UPA.txt");
		int bjpCnt=0;
		int aapCnt=0;
		int upaCnt=0;
		
		int posbjpCnt=0;
		int posaapCnt=0;
		int posupaCnt=0;
		
		int negbjpCnt=0;
		int negaapCnt=0;
		int negupaCnt=0;
		
		int neuNamoCnt=0;
		int neuKejCnt=0;
		int neuRahulCnt=0;
		
		if(namoObj.exists()==false)
		{
          		namoObj.createNewFile();
		}
		if(kejObj.exists()==false)
		{
				kejObj.createNewFile();
		}
		if(rahulObj.exists()==false)
		{
				rahulObj.createNewFile();
		}
		ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET)
                .setOAuthAccessToken(ACCESS_KEY)
                .setOAuthAccessTokenSecret(ACCESS_SECRET);

        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        
        String policianHashTags[]={"#BJP","#AAP","#UPA"};
        String positive[]={"good","best","better"};
        String negative[]={"bad","worst","defeat","loss","losing"};
        
        int positiveLen=positive.length;
        int negativeLen=negative.length;
        
        int totalPoliticians=policianHashTags.length;
        String temp=null;
        PrintWriter out=null;
        try 
        {        	
        	
        	
        	for(int i=0;i<totalPoliticians;i++)
        	{	
        		Query query = new Query(policianHashTags[i]);
        		//query.setResultType(Query.POPULAR);
        		query.setCount(100);
        		QueryResult result = twitter.search(query);
        		for (Status status : result.getTweets())
        		{
        			out = new PrintWriter(new FileWriter(policianHashTags[i]+".txt", true));
        			out.append(System.lineSeparator());
        			out.append(status.getText());
        			
        			/*Check for positive tweets*/
        			for(int j=0;j<positiveLen;j++)
        			{
        				if(status.getText().contains(positive[j]))
        				{
        					if(i==0)
                			{
        						posbjpCnt++;
                			}
                			if(i==1)
                			{
                				posaapCnt++;
                			}
                			if(i==2)
                			{
                				posupaCnt++;
                			}
        				}
        				
        				
        				else
        				{
        					if(i==0)
                			{
        						neuNamoCnt++;
                			}
                			if(i==1)
                			{
                				neuKejCnt++;
                			}
                			if(i==2)
                			{
                				neuRahulCnt++;
                			}
        					
        				}
        			}
        			
        			
        			/*Check for negative tweets*/
        			
        			for(int k=0;k<negativeLen;k++)
        			{
        				if(status.getText().contains(negative[k]))
        				{
        					if(i==0)
                			{
        						negbjpCnt++;
                			}
                			if(i==1)
                			{
                				negaapCnt++;
                			}
                			if(i==2)
                			{
                				negupaCnt++;
                			}
        				}
        				
        				else
        				{
        					if(i==0)
                			{
        						neuNamoCnt++;
                			}
                			if(i==1)
                			{
                				neuKejCnt++;
                			}
                			if(i==2)
                			{
                				neuRahulCnt++;
                			}
        					
        				}
        			}
        			
        			
        			
        			out.close();
        			
        		}
        	}
        	
        	
        	/*Check for the popular tweets*/
        	
        	for(int j=0;j<totalPoliticians;j++)
        	{	
        		Query sortquery = new Query(policianHashTags[j]);
        		sortquery.setResultType(Query.POPULAR);
        		sortquery.setCount(100);
        		QueryResult sortresult = twitter.search(sortquery);
        		for (Status sortStatus : sortresult.getTweets())
        		{
        			if(j==0)
        			{
        				bjpCnt++;
        			}
        			if(j==1)
        			{
        				aapCnt++;
        			}
        			if(j==2)
        			{
        				upaCnt++;
        			}
        			
        		}
        		
        		
        	}

        	System.out.println("");
        	System.out.println("****************************************************");
        	System.out.println("\t Bhartiya Janata Party(BJP)");
        	System.out.println("****************************************************");
        	System.out.println("");
        	System.out.println("Positive Tweets:"+posbjpCnt);
        	System.out.println("Negative Tweets:"+negbjpCnt);
        	System.out.println("");
        	System.out.println("");
        	
        	System.out.println("****************************************************");
        	System.out.println("\t Aam Admi Party(AAP)");
        	System.out.println("****************************************************");
        	System.out.println("");
        	System.out.println("Positive Tweets:"+posaapCnt);
        	System.out.println("Negative Tweets:"+negaapCnt);
        	System.out.println("");
        	System.out.println("");
        	
        	
        	System.out.println("****************************************************");
        	System.out.println("\t United Progressive Alliance(UPA)");
        	System.out.println("****************************************************");
        	System.out.println("");
        	System.out.println("Positive Tweets:"+posupaCnt);
        	System.out.println("Negative Tweets:"+negupaCnt);
        	System.out.println("");
        	System.out.println("");
        	System.out.println("****************************************************");
        	System.out.println("");
        	System.out.println("");
        	
        	if(bjpCnt>=aapCnt&&bjpCnt>=upaCnt)
        	{
        		System.out.println("");
        		System.out.println("BJP is most talked in twitter");
        		System.out.println("Discussed by "+bjpCnt+ " People");
        	}
        	if(aapCnt>=bjpCnt&&aapCnt>=upaCnt)
        	{
        		System.out.println("");
        		System.out.println("AAP is most talked in twitter");
        		System.out.println("Discussed by "+aapCnt+ " People");
        	}
        	if(upaCnt>=aapCnt&&upaCnt>=bjpCnt)
        	{
        		System.out.println("");
        		System.out.println("UPA is most talked in twitter");
        		System.out.println("Discussed by "+upaCnt+ " People");
        	}
		
		} 
        
    
        catch (TwitterException e) 
        {
			e.printStackTrace();
		}
   
        finally
        {
        	//tweetObj.selector();
        }
    
	}
}
