package Service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import Model.User;

public class AuthService {
	
    Key key;

	public AuthService() {
		key = new SecretKeySpec(DatatypeConverter.parseBase64Binary("ZF94g8eN-xCh7M7JN5SnhIKGC_BFxl9CkcRTL5W7ow0jkljfdlk"), SignatureAlgorithm.HS256.getJcaName());
	}

	public String createUserToken(User user) {
		String token = Jwts.builder().setSubject(user.getUsername()).signWith(key).compact();
		return token;
	}

	public boolean verifyUserToken(String token, String username) {
		try {
			if (Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject().equals(username)) {
				return true;
			} else {
				return false;
			}

		} catch (JwtException e) {

			return false;
		}
	}

	public boolean verifyToken(String token) {
		System.out.println(key.getFormat());
		try {
			Jwts.parser().setSigningKey(key).parseClaimsJws(token);
			return true;
		} catch (JwtException e) {

			return false;
		}
	}
}
