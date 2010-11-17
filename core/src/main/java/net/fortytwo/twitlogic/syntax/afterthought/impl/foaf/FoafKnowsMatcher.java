package net.fortytwo.twitlogic.syntax.afterthought.impl.foaf;

import net.fortytwo.twitlogic.vocabs.FOAF;
import net.fortytwo.twitlogic.syntax.afterthought.ObjectPropertyAfterthoughtMatcher;

/**
 * User: josh
 * Date: Sep 29, 2009
 * Time: 10:29:09 PM
 */
public class FoafKnowsMatcher extends ObjectPropertyAfterthoughtMatcher {
    protected String getPropertyURI() {
        return FOAF.KNOWS;
    }

    protected boolean predicateMatches(final String predicate) {
        String p = predicate.toLowerCase();
        return p.equals("knows")
                | p.equals("know");
    }
}