package xyz.vangamo.base.storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

import xyz.vangamo.base.BaseDB;

public abstract class DirectoryManager {

    // Static attributes.

    public    static Vector<DirectoryManagerRegisterer> managers = new Vector<DirectoryManagerRegisterer>();

    protected static final String selectDirectoryByIdSQL   = "SELECT id, storage_id, parent_id, canonicalPath, name, createTime, modTime, fileCount FROM path WHERE id=?";
    protected static final String selectDirectoryByPathSQL = "SELECT id, storage_id, parent_id, canonicalPath, name, createTime, modTime, fileCount FROM path WHERE canonicalPath=?";
    protected static final String insertDirectorySQL       = "INSERT INTO path SET storage_id=?, parent_id=?, canonicalPath=?, name=?, createTime=?, modTime=?, fileCount=?;";







    // Instance attributes.

    protected PreparedStatement psSelectDirectoryById   = null;
    protected PreparedStatement psSelectDirectoryByPath = null;
    protected PreparedStatement psInsertDirectory       = null;
    protected PreparedStatement psInsertFile            = null;





    // Instance methods

    public void create( Directory dir ) {

    }



    public Directory readById( Long id, Directory directory ) {
        return null;
        }  // END  method readById()



    public Directory readByURI( String uri, Directory directory ) {
        Connection db = BaseDB.INSTANCE.getConnection();

        if( null == this.psSelectDirectoryByPath ) {
            try {
                this.psSelectDirectoryByPath = db.prepareStatement( DirectoryManager.selectDirectoryByPathSQL );
                }
            catch( SQLException sqlEx ) {
                System.out.println( sqlEx.getMessage() );

                return null;
                }
            }

        try {
            this.psSelectDirectoryByPath.setString( 1, uri );
            }
        catch( SQLException sqlEx ) {
            System.out.println( sqlEx.getMessage() );

            return null;
            }


        try {
            ResultSet rs = this.psSelectDirectoryByPath.executeQuery();
            if( rs.next() ) {
System.out.println( "FOUND! id=" + rs.getLong(1) );

                HashMap<String, Object> data = new HashMap<String, Object>();
                data.put( "id",               rs.getLong("id") );
                data.put( "storage",          rs.getLong("storage_id") );
                data.put( "parent",           rs.getLong("parent_id") );
                data.put( "uri",              rs.getString("canonicalPath") );
                data.put( "name",             rs.getString("name") );
                data.put( "creationTime",     rs.getString("createTime") );
                data.put( "modificationTime", rs.getString("modTime") );
                data.put( "size",             rs.getLong("fileCount") );

                directory.setDataFromDB( data );
                }
            else {
                System.out.println( "NEW " + uri );
                }

            }
        catch( SQLException sqlEx ) {
            System.out.println( sqlEx.getMessage() );

            return null;
            }

        try {
            this.psSelectDirectoryByPath.clearParameters();
            }
        catch( SQLException sqlEx ) {
            System.out.println( sqlEx.getMessage() );
            }

        return directory;
        }  // END  method readByURI()



    public void update( Directory dir ) {

    	}



    public void delete( Directory dir ) {

		}



    public    abstract Directory getDirectory();
    public    abstract Directory getDirectory( String uri );
    public    abstract Directory getFile( String uri );

    protected abstract Vector<Directory> readChildren( Directory dir );





    // Static methods.

    public static void register( DirectoryManagerRegisterer dir ) {
System.out.println( "Adding manager " + dir.getClass().getName() );
        DirectoryManager.managers.add( dir );
        }

    public static DirectoryManager createDirectoryManager( String uri ) {

    	DirectoryManagerRegisterer bestManager = null;

        for( DirectoryManagerRegisterer manager : DirectoryManager.managers ) {
            if( manager.validateURI(uri) ) {
                bestManager = manager;
                break;
                }
            else { System.out.println("NO"); }

            }

        if( null == bestManager ) {
System.out.println( "No manager defined for " + uri );
            return null;
            }

        return bestManager.getDirectoryManager( uri );
        }



    public static Directory createDirectory( String uri ) {

        DirectoryManager bestManager = DirectoryManager.createDirectoryManager( uri );

        if( null == bestManager ) { return null; }

        return bestManager.getDirectory( uri );
        }

    }
