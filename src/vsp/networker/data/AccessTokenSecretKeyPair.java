package vsp.networker.data;

public class AccessTokenSecretKeyPair implements java.io.Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = -4635390461448504969L;
		public String accessToken, secretKey;
		public AccessTokenSecretKeyPair(String at, String sk) {
			accessToken = at;
			secretKey = sk;
		}
}