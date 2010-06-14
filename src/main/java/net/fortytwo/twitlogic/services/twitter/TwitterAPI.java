package net.fortytwo.twitlogic.services.twitter;

import org.json.JSONObject;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * User: josh
 * Date: Sep 3, 2009
 * Time: 10:07:42 PM
 */
public class TwitterAPI {
    // See http://apiwiki.twitter.com/Streaming-API-Documentation#statuses/filter
    public static final int
            TIMELINE_PAGE_COUNT_LIMIT = 200,
            DEFAULT_TRACK_KEYWORDS_LIMIT = 200,
            DEFAULT_FOLLOW_USERIDS_LIMIT = 400,
            DEFAULT_REST_API_REQUESTS_PER_HOUR_LIMIT = 150,
            STATUSES_LIMIT = 3200;  // At most this many statuses may be retrieved from a timeline via the 'page' and 'count' REST parameters.

    public static final String
            API_LISTS_URL = "http://api.twitter.com/1",
            OAUTH_REQUEST_TOKEN_URL = "http://twitter.com/oauth/request_token",
            OAUTH_ACCESS_TOKEN_URL = "http://twitter.com/oauth/access_token",
            OAUTH_AUTHORIZE_URL = "http://twitter.com/oauth/authorize",
            STREAM_STATUSES_FILTER_URL = "http://stream.twitter.com/1/statuses/filter.json",
            STREAM_STATUSES_SAMPLE_URL = "http://stream.twitter.com/1/statuses/sample.json",
            STATUSES_USER_TIMELINE_URL = "http://twitter.com/statuses/user_timeline",
            STATUSES_UPDATE_URL = "http://twitter.com/statuses/update",
            STATUSES_FRIENDS_URL = "http://api.twitter.com/1/statuses/friends",
            USER_TIMELINE_URL = "http://twitter.com/statuses/user_timeline",
            USERS_SHOW_URL = "http://twitter.com/users/show";

    public enum FieldContext {
        STATUS, USER
    }

    public enum UserListField {
        NEXT_CURSOR("next_cursor"),
        NEXT_CURSOR_STR("next_cursor_str"),
        PREVIOUS_CURSOR("previous_cursor"),
        PREVIOUS_CURSOR_STR("previous_cursor_str"),
        USERS("users");

        private static final Map<String, UserListField> fieldsByName;

        static {
            fieldsByName = new HashMap<String, UserListField>();
            for (UserListField f : UserListField.values()) {
                fieldsByName.put(f.name, f);
            }
        }

        private final String name;

        private UserListField(final String name) {
            this.name = name;
        }

        public static UserListField valueByName(final String name) {
            return fieldsByName.get(name);
        }

        public String toString() {
            return name;
        }
    }

    public enum ErrorField {
        ERROR("error"),
        REQUEST("request");

        private static final Map<String, ErrorField> fieldsByName;

        static {
            fieldsByName = new HashMap<String, ErrorField>();
            for (ErrorField f : ErrorField.values()) {
                fieldsByName.put(f.name, f);
            }
        }

        private final String name;

        private ErrorField(final String name) {
            this.name = name;
        }

        public static ErrorField valueByName(final String name) {
            return fieldsByName.get(name);
        }

        public String toString() {
            return name;
        }
    }

    public enum Field {
        ANNOTATIONS("annotations"),
        CONTRIBUTORS("contributors"),
        CONTRIBUTORS_ENABLED("contributors_enabled"),
        COORDINATES("coordinates"),
        CREATED_AT("created_at"), // Note: used in multiple contexts
        DESCRIPTION("description"),
        DELETE("delete"),
        FAVORITED("favorited"),
        FAVORITES_COUNT("favourites_count"),
        FOLLOWERS_COUNT("followers_count"),
        FOLLOWING("following"),
        FRIENDS_COUNT("friends_count"),
        GEO("geo"),
        GEO_ENABLED("geo_enabled"),
        ID("id"), // Note: used in multiple contexts
        IN_REPLY_TO_SCREEN_NAME("in_reply_to_screen_name"),
        IN_REPLY_TO_STATUS_ID("in_reply_to_status_id"),
        IN_REPLY_TO_USER_ID("in_reply_to_user_id"),
        LANG("lang"),
        LIMIT("limit"),
        LOCATION("location"),
        NAME("name"),
        NOTIFICATIONS("notifications"),
        PLACE("place"),
        PROFILE_BACKGROUND_COLOR("profile_background_color"),
        PROFILE_BACKGROUND_IMAGE_URL("profile_background_image_url"),
        PROFILE_BACKGROUND_TILE("profile_background_tile"),
        PROFILE_IMAGE_URL("profile_image_url"),
        PROFILE_LINK_COLOR("profile_link_color"),
        PROFILE_SIDEBAR_BORDER_COLOR("profile_sidebar_border_color"),
        PROFILE_SIDEBAR_COLOR("profile_sidebar_fill_color"),
        PROFILE_TEXT_COLOR("profile_text_color"),
        PROTECTED("protected"),
        RETWEETED_STATUS("retweeted_status"),
        SCREEN_NAME("screen_name"),
        SOURCE("source"),
        STATUS("status"),
        STATUSES_COUNT("statuses_count"),
        TEXT("text"),
        TIME_ZONE("time_zone"),
        TRUNCATED("truncated"),
        URL("url"),
        USER("user"),
        USERS("users"),
        UTC_OFFSET("utc_offset"),
        VERIFIED("verified");

        private static final Map<String, Field> fieldsByName;

        static {
            fieldsByName = new HashMap<String, Field>();
            for (Field f : Field.values()) {
                fieldsByName.put(f.name, f);
            }
        }

        private final String name;

        private Field(final String name) {
            this.name = name;
        }

        public static Field valueByName(final String name) {
            return fieldsByName.get(name);
        }

        public String toString() {
            return name;
        }
    }

    // API parameter names
    public static final String
            SCREEN_NAME = "screen_name",
            STATUS = "status",
            IN_REPLY_TO_STATUS_ID = "in_reply_to_status_id",
            LAT = "lat",
            LONG = "long",
            USER_ID = "user_id";

    // E.g.  Tue Nov 11 17:41:37 +0000 2008
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
            "EEE MMM dd HH:mm:ss ZZZZZ yyyy");

    /**
     * Checks for unknown keys in status element JSON.  If the Twitter API
     * is ever extended to include new keys (e.g. for geolocation), this check
     * will pick up on it.
     *
     * @param json the JSON object to check
     */
    public static void checkJSON(final JSONObject json,
                                 final FieldContext fieldContext) {
        for (Iterator iter = json.keys(); iter.hasNext();) {
            Object key = iter.next();
            if (!(key instanceof String) || null == Field.valueByName((String) key)) {
                System.err.println("unexpected field in " + fieldContext + ": " + key);
            }
        }
    }

    public static void checkUserListJSON(final JSONObject json) {
        for (Iterator iter = json.keys(); iter.hasNext();) {
            Object key = iter.next();
            if (!(key instanceof String) || null == UserListField.valueByName((String) key)) {
                System.err.println("unexpected field in user list: " + key);
            }
        }
    }

    public static Date parseTwitterDateString(final String dateStr) throws ParseException {
        //DATE_FORMAT.setLenient(false);
        return DATE_FORMAT.parse(dateStr);
    }

    public static String getString(final JSONObject json,
                                   final TwitterAPI.Field key) throws JSONException {
        String s = json.optString(key.toString());
        if (null != s && s.equals("null")) {
            s = null;
        }
        return s;
    }

    public static void main(final String[] args) {
        Field f = Field.valueByName("verified");
        System.out.println("f = " + f);
    }
}
