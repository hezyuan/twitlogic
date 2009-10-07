package net.fortytwo.twitlogic.persistence;

import net.fortytwo.twitlogic.flow.Handler;
import net.fortytwo.twitlogic.model.Tweet;
import net.fortytwo.twitlogic.model.User;
import net.fortytwo.twitlogic.twitter.TweetHandlerException;
import net.fortytwo.twitlogic.twitter.TwitterClient;
import net.fortytwo.twitlogic.twitter.TwitterClientException;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO: make it persistent
 * <p/>
 * User: josh
 * Date: Oct 5, 2009
 * Time: 1:16:55 AM
 */
public class UserRegistry {
    private final Map<String, User> usersByScreenName;
    private final TwitterClient client;

    public UserRegistry(final TwitterClient client) {
        this.client = client;
        usersByScreenName = new HashMap<String, User>();
    }

    /**
     * @param user must contain at least user id and screen name
     */
    public void add(final User user) {
        if (null == user.getId() || null == user.getScreenName()) {
            throw new IllegalArgumentException("null id or screen name");
        }

        if (null == usersByScreenName.get(user.getScreenName())) {
            usersByScreenName.put(user.getScreenName(), user);
        }
    }

    public Integer resolveUserId(final String screenName) throws TwitterClientException {
        User user = usersByScreenName.get(screenName);

        if (null == user) {
            user = client.findUserInfo(screenName);
            add(user);
        }

        return user.getId();
    }

    public Handler<Tweet, TweetHandlerException> createUserRegistryFilter(
            final Handler<Tweet, TweetHandlerException> baseHandler) {
        return new Handler<Tweet, TweetHandlerException>() {
            public boolean handle(final Tweet tweet) throws TweetHandlerException {
                add(tweet.getUser());
                return baseHandler.handle(tweet);
            }
        };
    }

    public User findUserInfo(final String screenName) throws TwitterClientException {
        User user = client.findUserInfo(screenName);
        add(user);
        return user;
    }
}
