package net.fortytwo.twitlogic.syntax.afterthought.impl;

import net.fortytwo.twitlogic.model.Hashtag;
import net.fortytwo.twitlogic.model.PlainLiteral;
import net.fortytwo.twitlogic.model.Resource;
import net.fortytwo.twitlogic.model.Triple;
import net.fortytwo.twitlogic.model.URIReference;
import net.fortytwo.twitlogic.syntax.MatcherTestBase;
import net.fortytwo.twitlogic.syntax.afterthought.DemoAfterthoughtMatcher;
import net.fortytwo.twitlogic.vocabs.RDF;
import net.fortytwo.twitlogic.vocabs.Review;

/**
 * @author Joshua Shinavier (http://fortytwo.net).
 */
public class ReviewMatcherTest extends MatcherTestBase {
    private static final Resource
            THEBESTMOVIEEVER = new Hashtag("thebestmovieever");

    private static final URIReference
            HASREVIEW = new URIReference(Review.HASREVIEW),
            MAXRATING = new URIReference(Review.MAXRATING),
            MINRATING = new URIReference(Review.MINRATING),
            RATING = new URIReference(Review.RATING),
            REVIEW = new URIReference(Review.REVIEW),
            REVIEWER = new URIReference(Review.REVIEWER),
            TEXT = new URIReference(Review.TEXT),
            TYPE = new URIReference(RDF.TYPE);

    public void setUp() {
        matcher = new DemoAfterthoughtMatcher();
    }

    public void testAll() throws Exception {
        Resource review = bnode(bnodeIndex + 1);
        assertExpected("IMHO, #thebestmovieever (3/5) is only so-so.",
                new Triple(THEBESTMOVIEEVER, HASREVIEW, review),
                new Triple(review, TYPE, REVIEW),
                new Triple(review, RATING, new PlainLiteral("3")),
                new Triple(review, MAXRATING, new PlainLiteral("5")),
                new Triple(review, MINRATING, new PlainLiteral("0")),
                // FIXME: restore this once the corresponding statement is restored in ReviewMatcher
          //      new Triple(review, REVIEWER, tweetContext.thisPerson()),
                new Triple(review, TEXT, new PlainLiteral("IMHO, #thebestmovieever (3/5) is only so-so.")));
    }

    public void testOutOfRangeNumbers() throws Exception {
        assertExpected("... #foo (3/6) ...");
        assertExpected("... #foo (3/3) ...");
        assertExpected("... #foo (5/4) ...");
        assertExpected("... #foo (-1/5) ...");
    }
}
