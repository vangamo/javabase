package xyz.vangamo.base.storage;

public class File extends Directory {

	private static final long serialVersionUID = 3669201130813297593L;

	public static String[] FIELD_NAMES = {
/* 13 */    "md5"
/* 14 */   ,"mime"
            };

	public static String[] FIELD_TYPES = {
/* 13 */    "text"
/* 14 */   ,"text"
            };
	
    static {
        COMPOSE_FIELDS( File.class.getSuperclass(), File.class );

        for( int i=0; i<File.FIELD_NAMES.length; i++ ) {
            System.out.println( "File.FIELD_NAMES["+i+"] = " + File.FIELD_NAMES[i] );
            }
        }


    public File( String uri, DirectoryManager actualManager ) {
    	this( File.FIELD_NAMES );

    	this.manager = actualManager;

    	this.getManager().readByURI( uri, this );
    	}



	public File( String uri ) {
    	this( File.FIELD_NAMES );


    	}

    protected File( String[] fields ) {
    	super( fields );

    	this.set( "type", "file" );
		}


    @Override
	public boolean isDirectory() {
        return false;
        }

    @Override
	public boolean isFile() {
        return true;
        }

    }  //  class File
