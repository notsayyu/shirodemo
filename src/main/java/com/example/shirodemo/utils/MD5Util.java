package com.example.shirodemo.utils;

import java.security.MessageDigest;

/**
 * 来源自网络
 */
public class MD5Util {

    public static String toMD5( String plainText ) {
        assert plainText != null;
        try {
            MessageDigest md = MessageDigest.getInstance( "MD5" );
            md.update( plainText.getBytes() );
            byte b[] = md.digest();

            int i;
            StringBuilder buf = new StringBuilder( "" );
            for ( byte aB : b ) {
                i = aB;
                if ( i < 0 )
                    i += 256;
                if ( i < 16 )
                    buf.append( "0" );
                buf.append( Integer.toHexString( i ) );
            }
            return buf.toString();
        } catch ( Exception e ) {
            throw new RuntimeException( e );
        }
    }
}
