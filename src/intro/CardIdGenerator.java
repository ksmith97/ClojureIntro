    private static final String CHARS = "0123456789BCDFGHJKLMNPQRSTVWXZ";

    private static final String ALPHA = "BCDFGHJKLMNPQRSTVWXZ";

    private static final String NUMBERS = "0123456789";

    private static final String VOWELS = "AEIOUY";

    private static SecureRandom secureRandom = null;
    static
    {
        try
        {
            secureRandom = SecureRandom.getInstance( "SHA1PRNG" );
        }
        catch( NoSuchAlgorithmException e )
        {
            throw new RuntimeException( "error initializing ", e );
        }
    }

    public String getNextRandomString( int length, int maxAdjacentAlphas ) throws BenecardException
    {

        StringBuilder builder = null;
        try
        {
            int num = Math.abs( secureRandom.nextInt() );
            String retValue = Integer.toString( num, 36 );
            while( retValue.length() < length )
            {
                int pos = secureRandom.nextInt( retValue.length() );
                char character = CHARS.charAt( secureRandom
                    .nextInt( CHARS.length() ) );

                String pre = retValue.substring( 0, pos );
                String post = retValue.substring( pos );

                retValue = pre + character + post;
            }
            builder = new StringBuilder();
            int charsSinceLastDigit = 0;
            for( int i = 0; i < retValue.length(); i++ )
            {
                char curr = retValue.charAt( i );
                if ( Character.isDigit( curr ) )
                {
                    charsSinceLastDigit = 0;
                }
                else
                {
                    charsSinceLastDigit++;
                    if ( charsSinceLastDigit > maxAdjacentAlphas )
                    {
                        curr = NUMBERS.charAt( secureRandom.nextInt( 9 ) );
                    }
                    else if ( VOWELS.indexOf( curr ) < 0 )
                    {
                        curr = ALPHA
                            .charAt( secureRandom.nextInt( ALPHA.length() ) );
                    }
                }
                builder.append( curr );
            }
        }
        catch( RuntimeException exception )
        {
            logger.error( "Exception while generating Member Card Id : "
                          + exception );

            throw new BenecardSystemException( MessageHelper
                .getErrorMessage( "error.system.unknown" ), exception );

        }

        return builder.toString().toUpperCase();
    }
