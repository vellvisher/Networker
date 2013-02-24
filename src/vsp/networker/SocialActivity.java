package vsp.networker;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import vsp.networker.data.AccessTokenSecretKeyPair;
import vsp.networker.data.User;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SocialActivity extends Activity implements Runnable {

	private static final String PROTECTED_RESOURCE_URL = "https://api.twitter.com/1/account/verify_credentials.json";
	private String verifierValue;
	private OAuthService service;
	private Token requestToken;
	private boolean returnFromBrowser;
	private Verifier verifier;
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
		Thread T = new Thread(this);
		T.start();
	}
		
	public void run(){
		service = new ServiceBuilder()
        .provider(TwitterApi.class)
        .apiKey("gg6IacmVpkxQ1kQV1Y7yw")
        .apiSecret("4Nm7kPengNHT0Oej518JCECkqVMwPg79ByFOxOVU")
        .build();



    System.out.println("=== Twitter's OAuth Workflow ===");
System.out.println();

// Obtain the Request Token
System.out.println("Fetching the Request Token...");
requestToken = service.getRequestToken();
System.out.println("Got the Request Token!");
System.out.println();

System.out.println("Now go and authorize Scribe here:");
System.out.println(service.getAuthorizationUrl(requestToken));
dispatchBrowser(service.getAuthorizationUrl(requestToken));
System.out.println("And paste the verifier here");
System.out.print(">>");

	}
	
	public void dispatchBrowser(String authURL){
		returnFromBrowser = true;
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(authURL));
        startActivity(i);
	}
	
	public void authCodeEntered(View view) {
		EditText tv = (EditText) findViewById(R.id.auth_code_text_field);
		verifierValue = (String)tv.getText().toString();
		System.out.println("verifierValue:"+verifierValue);
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				verifier = new Verifier(verifierValue);
				System.out.println();

				// Trade the Request Token and Verfier for the Access Token
				System.out.println("Trading the Request Token for an Access Token...");
				Token accessToken = service.getAccessToken(requestToken, verifier);
				System.out.println("Got the Access Token!");
				System.out.println("(if your curious it looks like this: " + accessToken + " )");
				System.out.println();

				// Now let's go and ask for a protected resource!
				System.out.println("Now we're going to access a protected resource...");
				OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
				//request.addBodyParameter("user_id", "1401881");


				System.out.println(request.getBodyContents());
				service.signRequest(accessToken, request);
				User.currentUser.socialMediaKeys.put(User.TWITTER, new AccessTokenSecretKeyPair(accessToken.getToken(), 
						accessToken.getSecret()));
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
				User.currentUser.getDetails().put(User.TWITTER_ID, id);
				
				System.out.println();
				System.out.println("Thats it man! Go and build something awesome with Scribe! :)");
			}
		});
		t2.start();
	}
	
	@Override
	public void onResume() {
		super.onResume();

		EditText tv = (EditText) findViewById(R.id.auth_code_text_field);
		Button submitButton = (Button) findViewById(R.id.submitButton);
		
		if(returnFromBrowser) {
			tv.setVisibility(View.VISIBLE);
			submitButton.setVisibility(View.VISIBLE);
			returnFromBrowser = false;
		} else {
			tv.setVisibility(View.INVISIBLE);
			submitButton.setVisibility(View.INVISIBLE);
			returnFromBrowser = false;
		}
		
	}
}


