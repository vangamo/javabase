package xyz.vangamo.base.photo;

import xyz.vangamo.base.storage.File;

public class PhotoFile extends File {


	private static final long serialVersionUID = 3778868531101337975L;



	protected PhotoFile(String[] fields) {
		super(fields);
		}



	public static String[] FIELD_NAMES = {
/* 15 */    "format"
/* 16 */   ,"width"
/* 17 */   ,"height"
/* 18 */   ,"pixelDepth"
/* 19 */   ,"widthDensity"
/* 20 */   ,"heightDensity"
/* 21 */   ,"event"
/* 22 */   ,"locations"
/* 23 */   ,"people"
            };

	public static String[] FIELD_TYPES = {
/* 15 */    "text"
/* 16 */   ,"int"
/* 17 */   ,"int"
/* 18 */   ,"int"
/* 19 */   ,"int"
/* 20 */   ,"int"
/* 21 */   ,"circleref"
/* 22 */   ,"circleref"
/* 23 */   ,"circleref"
            };	
/*
           ,"captureTime"
           ,""
           ,""
 */


    static {
        COMPOSE_FIELDS( PhotoFile.class.getSuperclass(), PhotoFile.class );

        for( int i=0; i<PhotoFile.FIELD_NAMES.length; i++ ) {
            System.out.println( "PhotoFile.FIELD_NAMES["+i+"] = " + PhotoFile.FIELD_NAMES[i] );
            }
        }


    public static PhotoFile getInstance( File file ) {
    	return new PhotoFile( PhotoFile.FIELD_NAMES );
    	}
    }
