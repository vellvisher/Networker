package vsp.networker;

import java.util.Scanner;

import org.scribe.builder.ServiceBuilder;


import org.scribe.builder.api.LinkedInApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import vsp.networker.data.AccessTokenSecretKeyPair;
import vsp.networker.data.User;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LinkedInActivity extends Activity implements Runnable
{

	
	private static final String PROTECTED_RESOURCE_URL = "http://api.linkedin.com/v1/people/~:(email-address)";
	private static final String FOLLOWING_RESOURCE_URL = "http://api.linkedin.com/v1/people/~/mailbox";
	private String verifierValue;
	private OAuthService service;
	private Token requestToken;
	private boolean returnFromBrowser;
	private Verifier verifier;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_linked_in);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.linked_in, menu);
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
		OAuthService service = new ServiceBuilder()
        .provider(LinkedInApi.withScopes("r_emailaddress+r_network+w_messages"))
        .apiKey("q3w3wlvc0ii5")
        .apiSecret("bfXAk13oRgfBG6aP")
        .build();


System.out.println("=== LinkedIn's OAuth Workflow ===");
System.out.println();

// Obtain the Request Token
System.out.println("Fetching the Request Token...");
Token requestToken = service.getRequestToken();
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

				System.out.println("Trading the Request Token for an Access Token...");
			    Token accessToken = service.getAccessToken(requestToken, verifier);
			    System.out.println("Got the Access Token!");
			    System.out.println("(if your curious it looks like this: " + accessToken + " )");
			    System.out.println();

			    // Now let's go and ask for a protected resource!
			    System.out.println("Now we're going to access a protected resource...");
				// Trade the Request Token and Verfier for the Access Token
				System.out.println("Got it! Lets see what we found...");
				System.out.println();
				OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
			    service.signRequest(accessToken, request);
			    Response response = request.send();
			    System.out.println("Got it! Lets see what we found...");
			    System.out.println();
			    System.out.println(response.getBody());
			    String Body = response.getBody();
			    int start=Body.indexOf("email-address");
			    start+=14;
			    int end= Body.indexOf("<", start);
			    String id = (String) Body.subSequence(start, end);
				User.currentUser.getDetails().put(User.LINKEDIN_ID, id);
				
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
	
	public void connectWithPerson(String first, String last, String email){
		
	    OAuthRequest request = new OAuthRequest(Verb.POST, FOLLOWING_RESOURCE_URL);
	    String userAccessToken = User.currentUser.socialMediaKeys.get(User.LINKEDIN).accessToken;
		String userSecretKey = User.currentUser.socialMediaKeys.get(User.LINKEDIN).secretKey;
		Token accessToken = new Token(userAccessToken, userSecretKey);
	    request.addHeader("Content-Type", "text/xml");
	    String xmlbody = "<?xml version='1.0' encoding='UTF-8'?>" + 
	    			"<mailbox-item>"+
	  "<recipients>" +
	    "<recipient>" +
	      "<person path=\"/people/email="+email+"\">" +
	        "<first-name>"+first+"</first-name>" +
	        "<last-name>"+last+"</last-name>" +
	      "</person>" + 
	    "</recipient>" +
	  "</recipients>" +
	  "<subject>Invitation to Connect</subject>" +
	  "<body>Please join my professional network o n LinkedIn.</body>"+
	  "<item-content>"+
	    "<invitation-request>"+
	      "<connect-type>friend</connect-type>"+
	    "</invitation-request>" +
	  "</item-content>"+
	"</mailbox-item>";
		request.addPayload(xmlbody );
	    service.signRequest(accessToken, request);
	    Response response = request.send();

	
	
	}



}
