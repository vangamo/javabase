package xyz.vangamo.base.photo;

import xyz.vangamo.base.storage.File;

public class PhotoFile extends File {


	private static final long serialVersionUID = 3778868531101337975L;



	protected PhotoFile(String[] fields) {
		super(fields);
		}



	public static String[] FIELDS = {
/* 13 */    "format"
/* 14 */   ,"width"
/* 15 */   ,"height"
/* 16 */   ,"pixelDepth"
/* 17 */   ,"density"
/* 18 */   ,"event"
/* 19 */   ,"locations"
/* 20 */   ,"people"
            };
/*
           ,"captureTime"
           ,""
           ,""
 */


    static {
        COMPOSE_FIELDS( PhotoFile.class.getSuperclass(), PhotoFile.class );

        for( int i=0; i<PhotoFile.FIELDS.length; i++ ) {
            System.out.println( "PhotoFile.FIELDS["+i+"] = " + PhotoFile.FIELDS[i] );
            }
        }


    public static PhotoFile getInstance( File file ) {
    	return new PhotoFile( PhotoFile.FIELDS );
    	}
    }
