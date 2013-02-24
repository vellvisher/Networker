package vsp.networker;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class SocialActivity extends Activity {

	private static final String PROTECTED_RESOURCE_URL = "https://api.twitter.com/1/account/verify_credentials.json";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_social);
		 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_social, menu);
		return true;
	}
	
	public boolean isNetworkAvailable() {
	    ConnectivityManager cm = (ConnectivityManager) 
	      getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = cm.getActiveNetworkInfo();
	    // if no network is available networkInfo will be null
	    // otherwise check if we are connected
	    if (networkInfo != null && networkInfo.isConnected()) {
	        return true;
	    }
	    return false;
	} 
	
	public void authenticateTwitter(View view) {
		OAuthService service = new ServiceBuilder()
        .provider(TwitterApi.class)
        .apiKey("gg6IacmVpkxQ1kQV1Y7yw")
        .apiSecret("4Nm7kPengNHT0Oej518JCECkqVMwPg79ByFOxOVU")
        .build();
		
		
		System.out.println("Trading the Request Token for an Access Token...");
	    Token accessToken = new Token("1043853068-JoXlXPlBm2cu8xzhLMrDnKDNDMAfKl2mqcqP33O", "gLGqHHSmd0pAOzJcztwW9qJfvItkocLWD7eV6en2GI");
	    System.out.println("Got the Access Token!");
	    System.out.println("(if your curious it looks like this: " + accessToken + " )");
	    System.out.println();

	    // Now let's go and ask for a protected resource!
	    System.out.println("Now we're going to access a protected resource...");
	    OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
	    //request.addBodyParameter("user_id", "1401881");
	    
	   
	    System.out.println(request.getBodyContents());
	    service.signRequest(accessToken, request);
	    Response response = request.send();
	    System.out.println("Got it! Lets see what we found...");
	    System.out.println();
	    System.out.println(response.getBody());
	    String Body = response.getBody();
	    int start=Body.indexOf("id_str");
	    start+=9;
	    int end= Body.indexOf("\"", start);
	    String id = (String) Body.subSequence(start, end);
	    System.out.println(id);

	    
	    System.out.println();
	    System.out.println("Thats it man! Go and build something awesome with Scribe! :)");
	}
	
}
