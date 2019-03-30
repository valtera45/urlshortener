package com.wright.urlshortener.utils;

import org.apache.log4j.Logger;

/**
 * 
 * 
 * @author christopherwright
 *
 */
public class Base62UrlEncoder {

    private static final String CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final Logger log = Logger
            .getLogger(Base62UrlEncoder.class.getName());

    private Base62UrlEncoder() {
        super();
    }

    /**
     * Return a Base62 encoded URL based on the current row count in the
     * database. Here, <code>i</code> can safely be cast to a Long variable
     * because 62^6 = 56,800,235,584 % 62 will always be less than 62 and won't
     * overflow the value of the integer.
     * 
     * @param i
     *            the current value of the auto incremented ID in the database,
     *            used as a counter to generate the unique Base62 6 character
     *            value.
     * @return
     */
    public static String encode(long i) {
        StringBuilder sb = new StringBuilder();

        int count = 0;

        while (i > 0) {
            count = (int) (i % 62);
            sb.append(String.valueOf(CHARS.charAt(count)));
            i /= 62;
        }

        while (sb.length() < 6) {
            sb.append("0");
        }

        log.info("The encoded URL is: " + sb.toString());
        return sb.toString();
    }

}
