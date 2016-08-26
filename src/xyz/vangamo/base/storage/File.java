package xyz.vangamo.base.storage;

public class File extends Directory {

	private static final long serialVersionUID = 3669201130813297593L;

	public static String[] FIELDS = {
/* 11 */    "md5"
/* 12 */   ,"mime"
            };

    static {
        COMPOSE_FIELDS( File.class.getSuperclass(), File.class );

        for( int i=0; i<File.FIELDS.length; i++ ) {
            System.out.println( "File.FIELDS["+i+"] = " + File.FIELDS[i] );
            }
        }


    public File( String uri, DirectoryManager actualManager ) {
    	this( File.FIELDS );

    	this.manager = actualManager;

    	this.getManager().readByURI( uri, this );
    	}



	public File( String uri ) {
    	this( File.FIELDS );


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
