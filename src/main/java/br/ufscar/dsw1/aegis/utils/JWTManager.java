package br.ufscar.dsw1.aegis.utils;

import br.ufscar.dsw1.dao.UserDAO;
import br.ufscar.dsw1.domain.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class JWTManager {
    private static Algorithm getAlgorithm() {
        return Algorithm.HMAC256(System.getenv("SECRET"));
    }

    public static String generateJWTToken(User user) {
        String token = null;
        try {
            Algorithm algorithm = JWTManager.getAlgorithm();
            token = JWT.create()
                    .withIssuer("debatr")
                    .withExpiresAt(Date.from(Instant.now().plus(300, ChronoUnit.SECONDS)))
                    .withClaim("userid", user.getId())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            e.printStackTrace();
        }

        return token;
    }

    public static DecodedJWT getDecodedJWT(String token) {
        try {
            Algorithm algorithm = JWTManager.getAlgorithm();
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("debatr").build();

            return verifier.verify(token);
        } catch (JWTVerificationException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void changePasswordForTokenOwner(String token, String plaintextPassword) {
        DecodedJWT jwt = JWTManager.getDecodedJWT(token);

        if (jwt != null) {
            Claim userIdClaim = jwt.getClaim("userid");
            final int userId = userIdClaim.asInt();

            UserDAO.changeUserPassword(userId, plaintextPassword);
        }
    }
}


