package net.fortytwo.twitlogic.vocabs;

/**
 * User: josh
 * Date: Oct 3, 2009
 * Time: 3:19:13 AM
 */
public interface SIOC {
    public static final String NAMESPACE = "http://rdfs.org/sioc/ns#";

    // TODO: some of these are actually FOAF properties
    public static final String
            ABOUT = NAMESPACE + "about",
            ACCOUNT_OF = NAMESPACE + "account_of",
            ADMINISTRATOR_OF = NAMESPACE + "administrator_of",
            ATTACHMENT = NAMESPACE + "attachement",
            AVATAR = NAMESPACE + "avatar",
            COMMUNITY = NAMESPACE + "Community",
            CONTAINER = NAMESPACE + "Container",
            CONTAINER_OF = NAMESPACE + "container_of",
            CONTENT = NAMESPACE + "content",
            CONTENT_ENCODED = NAMESPACE + "content_encoded",
            CREATED_AT = NAMESPACE + "created_at",
            CREATOR_OF = NAMESPACE + "creator_of",
            DECRIPTION = NAMESPACE + "description",
            EARLIER_VERSION = NAMESPACE + "earlier_version",
            EMAIL = NAMESPACE + "email",
            EMAIL_SHA1 = NAMESPACE + "email_sha1",
            FIRST_NAME = NAMESPACE + "first_name",
            FOLLOWS = NAMESPACE + "follows",
            FORUM = NAMESPACE + "Forum",
            FUNCTION_OF = NAMESPACE + "function_of",
            GROUP_OF = NAMESPACE + "group_of",
            HAS_ADMINISTRATOR = NAMESPACE + "has_administrator",
            HAS_CONTAINER = NAMESPACE + "has_container",
            HAS_CREATOR = NAMESPACE + "has_creator",
            HAS_DISCUSSION = NAMESPACE + "has_discussion",
            HAS_FUNCTION = NAMESPACE + "has_function",
            HAS_GROUP = NAMESPACE + "has_group",
            HAS_HOST = NAMESPACE + "has_host",
            HAS_MEMBER = NAMESPACE + "has_member",
            HAS_MODERATOR = NAMESPACE + "has_moderator",
            HAS_MODIFIER = NAMESPACE + "has_modifier",
            HAS_OWNER = NAMESPACE + "has_owner",
            HAS_PARENT = NAMESPACE + "has_parent",
            HAS_PART = NAMESPACE + "has_part",
            HAS_REPLY = NAMESPACE + "has_reply",
            HAS_SCOPE = NAMESPACE + "has_scope",
            HAS_SPACE = NAMESPACE + "has_space",
            HAS_SUBSCRIBER = NAMESPACE + "has_subscriber",
            HAS_USERGROUP = NAMESPACE + "has_usergroup",
            HOST_OF = NAMESPACE + "host_of",
            ID = NAMESPACE + "id",
            IP_ADDRESS = NAMESPACE + "ip_address",
            ITEM = NAMESPACE + "Item",
            LAST_NAME = NAMESPACE + "last_name",
            LATER_VERSION = NAMESPACE + "later_version",
            LINKS_TO = NAMESPACE + "links_to",
            MEMBER_OF = NAMESPACE + "member_of",
            MODERATOR_OF = NAMESPACE + "moderator_of",
            MODIFIED_AT = NAMESPACE + "modified_at",
            MODIFIER_OF = NAMESPACE + "modifier_of",
            NAME = NAMESPACE + "name",
            NEXT_BY_DATE = NAMESPACE + "next_by_date",
            NEXT_VERSION = NAMESPACE + "next_version",
            NOTE = NAMESPACE + "note",
            NUM_REPLIES = NAMESPACE + "num_replies",
            NUM_VIEWS = NAMESPACE + "num_views",
            OWNER_OF = NAMESPACE + "owner_of",
            PARENT_OF = NAMESPACE + "parent_of",
            PART_OF = NAMESPACE + "part_of",
            POST = NAMESPACE + "Post",
            PREVIOUS_BY_DATE = NAMESPACE + "previous_by_date",
            PREVIOUS_VERSION = NAMESPACE + "previous_version",
            REFERENCE = NAMESPACE + "reference",
            REPLY_OF = NAMESPACE + "reply_of",
            ROLE = NAMESPACE + "Role",
            SCOPE_OF = NAMESPACE + "scope_of",
            SIBLING = NAMESPACE + "sibling",
            SITE = NAMESPACE + "Site",
            SPACE = NAMESPACE + "Space",
            SPACE_OF = NAMESPACE + "space_of",
            SUBJECT = NAMESPACE + "subject",
            SUBSCRIBER_OF = NAMESPACE + "subscriber_of",
            THREAD = NAMESPACE + "Thread",
            TITLE = NAMESPACE + "title",
            TOPIC = NAMESPACE + "topic",
            USER = NAMESPACE + "User",
            USERGROUP = NAMESPACE + "Usergroup",
            USERGROUP_OF = NAMESPACE + "usergroup_of";

    public static final String
            EMBEDSKNOWLEDGE = NAMESPACE + "embedsKnowledge";
}
