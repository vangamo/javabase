package xyz.vangamo.base.storage;

import java.util.Vector;

import jcifs.smb.NtlmAuthenticator;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;

public class CifsDirectoryManager extends DirectoryManager {

    // Static attributes.

    static {
    	DirectoryManager.register( new DirectoryManagerRegisterer() {

			@Override
			public boolean validateURI(String uri) {
				return uri.startsWith("smb://");
				}

			@Override
			public DirectoryManager getDirectoryManager(String uri) {
				return new CifsDirectoryManager( uri );
				}

    		});
        }





    // Instance attributes.

    private String  baseURI    = null;
    private SmbFile baseObject = null;





    // Constructors.

    public CifsDirectoryManager() {
        super();

        NtlmAuthenticator.setDefault( new NtlmAuthenticator() {
            @Override
			protected NtlmPasswordAuthentication getNtlmPasswordAuthentication() {
                try {
                    String username = "emota";
                    String password = "TKMy1M";

                    return new NtlmPasswordAuthentication( null, username, password );
                    } catch( Exception e ) {
                    }
                return null;
                }

            });
        }



    public CifsDirectoryManager( String uri ) {
        this();

        this.baseURI = uri;
        }





    // Instance methods

    @Override
	public Directory getDirectory() {
        return this.getDirectory( this.baseURI );
        }

    @Override
	public Directory getDirectory( String uri ) {
        System.out.print( " - I got it: " + uri + "\n" );

        SmbFile file = null;

        try {
            file = new SmbFile( uri );
            }
        catch( Exception ex ) {
            System.out.println( "Error: " + ex.getMessage() );
            }

        Directory baseDir =  new Directory( uri, this );

        baseDir.setManager( this );
        // TODO!!! baseDir.setSmbFile( file );

        try {
            SmbFile[] subfiles = file.listFiles();
            Vector<String> subfilenames = new Vector<String>();
            Vector<String> subdirnames  = new Vector<String>();

            for( SmbFile subfile : subfiles ) {
                if( subfile.isDirectory() ) {
                    subdirnames.add( subfile.getCanonicalPath() );
                    }
                else if( subfile.isFile() ) {
                    subfilenames.add( subfile.getCanonicalPath() );
                    }
                }  // END for

            baseDir.setChildren( null );
            }
        catch( SmbException smbEx ) {
            System.out.println( "Error: " + smbEx.getMessage() );
            }

//        if( baseDir.isDirty() ) {}

/*
        Directory baseDir = new Directory();
        baseDir.setSmbFile( file );

        this.readFromDB( baseDir );   //  --> readFromDB is necesary?? I must be called from Directory constructor.
        //insert( baseDir );

        //SmbFile[] files = file.listFiles();
*/
        return baseDir;
        }

    @Override
	public File getFile( String uri ) {
        return null;
        }

    @Override
	protected Vector<Directory> readChildren( Directory dir ) {
    	return null;
    	}

    }
