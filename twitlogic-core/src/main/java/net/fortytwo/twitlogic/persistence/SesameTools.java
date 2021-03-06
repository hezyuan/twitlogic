package net.fortytwo.twitlogic.persistence;

import net.fortytwo.twitlogic.TwitLogic;
import org.openrdf.model.Literal;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.URIImpl;
import org.openrdf.rio.RDFFormat;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Joshua Shinavier (http://fortytwo.net).
 */
public class SesameTools {
    private static final Random RANDOM = new Random();

    private static final Map<String, RDFFormat> RDF_FORMAT_BY_EXTENSION;

    // Note: the implementation of this class is assumed to be thread-safe.
    private static final DatatypeFactory DATATYPE_FACTORY;

    static {
        try {
            DATATYPE_FACTORY = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            throw new ExceptionInInitializerError(e);
        }

        RDF_FORMAT_BY_EXTENSION = new HashMap<String, RDFFormat>();
        for (RDFFormat f : RDFFormat.values()) {
            RDF_FORMAT_BY_EXTENSION.put(f.getDefaultFileExtension().toLowerCase(), f);
        }
        RDF_FORMAT_BY_EXTENSION.put("ntriples", RDFFormat.NTRIPLES);
        RDF_FORMAT_BY_EXTENSION.put("ntriple", RDFFormat.NTRIPLES);
        RDF_FORMAT_BY_EXTENSION.put("rdfxml", RDFFormat.RDFXML);
        RDF_FORMAT_BY_EXTENSION.put("turtle", RDFFormat.TURTLE);
        RDF_FORMAT_BY_EXTENSION.put("trix", RDFFormat.TRIX);
    }

    private SesameTools() {
    }

    public static XMLGregorianCalendar toXMLGregorianCalendar(final Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        return DATATYPE_FACTORY.newXMLGregorianCalendar(cal);
    }

    public static Literal createLiteral(final Date date,
                                        final ValueFactory valueFactory) {
        return valueFactory.createLiteral(SesameTools.toXMLGregorianCalendar(date));
    }

    // TODO: improve this
    //public static URI createRandomResourceURI(final ValueFactory valueFactory) {
    //    return valueFactory.createURI(TwitLogic.BASE_URI + randomIdString());
    //}

    // TODO: improve this

    public static URI createRandomGraphURI(final ValueFactory valueFactory) {
        return valueFactory.createURI(TwitLogic.GRAPHS_BASEURI + randomIdString());
    }

    // TODO: improve this
    public static URI createRandomPersonURI(final ValueFactory valueFactory) {
        return valueFactory.createURI(TwitLogic.PERSONS_BASEURI + randomIdString());
    }

    // TODO: improve this
    public static String createRandomMiscellaneousURIString() {
        return TwitLogic.MISCELLANEOUS_BASEURI + randomIdString();
    }

    public static String randomIdString() {
        return "" + RANDOM.nextInt(Integer.MAX_VALUE);
    }

    public static RDFFormat rdfFormatByExtension(final String ext) {
        return RDF_FORMAT_BY_EXTENSION.get(ext.toLowerCase());
    }
}
