package controllers;

import models.User;
import play.Logger;
import play.libs.OAuth2;
import play.libs.WS;
import play.mvc.Before;
import play.mvc.Controller;

import com.google.gson.JsonObject;

public class UserController extends Controller {

    public static OAuth2 FACEBOOK = new OAuth2(
            "https://graph.facebook.com/oauth/authorize",
            "https://graph.facebook.com/oauth/access_token",
            "285484724882779",
            "72201774418c568cf030fdea59fdf441"
    );

    public static void doLogin() {
        User u = connected();
        JsonObject me = null;
        if (u != null && u.access_token != null) {
            me = WS.url("https://graph.facebook.com/me?access_token=%s", WS.encode(u.access_token)).get().getJson().getAsJsonObject();
        }
        //render(me);
		
		TaskController.showAll("allCategories");
    }

    public static void auth() {
        if (OAuth2.isCodeResponse()) {
            User u = connected();
            OAuth2.Response response = FACEBOOK.retrieveAccessToken(authURL());
            u.access_token = response.accessToken;
            u.save();
            doLogin();
        }
        FACEBOOK.retrieveVerificationCode(authURL());
    }

    @Before
    static void setuser() {
        User user = null;
        if (session.contains("uid")) {
            Logger.info("existing user: " + session.get("uid"));
            user = User.get(Long.parseLong(session.get("uid")));
        }
        if (user == null) {
            user = User.createNew();
            session.put("uid", user.uid);
        }
        renderArgs.put("user", user);
    }

    static String authURL() {
        return play.mvc.Router.getFullUrl("UserController.auth");
		// return "http://localhost";
    }

    static User connected() {
        return (User)renderArgs.get("user");
    }

}
