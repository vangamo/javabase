package xyz.vangamo.base.storage;

import java.util.HashMap;
import java.util.TreeSet;
import java.util.Vector;

public class Directory extends xyz.vangamo.base.Base {

    // Static attributes.

    /**
	 *
	 */
	private static final long serialVersionUID = 4489755107694156427L;

	public static String[] FIELDS = {
/* 03 */    "storage"
/* 04 */   ,"parent"
/* 05 */   ,"uri"
/* 06 */   ,"name"
/* 07 */   ,"creationTime"
/* 08 */   ,"modificationTime"
/* 09 */   ,"size"
/* 10 */   ,"owner"
            };

    static {
        COMPOSE_FIELDS( Directory.class.getSuperclass(), Directory.class );

        for( int i=0; i<FIELDS.length; i++ ) {
            System.out.println( "Directory.FIELDS["+i+"] = " + File.FIELDS[i] );
            }
        }





    // Instance attributes.

    protected DirectoryManager manager = null;

    Directory parent = null;
    HashMap<String,Directory> children = new HashMap<String,Directory>();







    // Constructors.

    public Directory( Long id, DirectoryManager actualManager ) {
    	this( Directory.FIELDS );

    	this.manager = actualManager;

    	this.getManager().readById( id, this );
    	}

    public Directory( Long id ) {
    	this( Directory.FIELDS );

    	this.getManager().readById( id, this );
    	}



    public Directory( String uri, DirectoryManager actualManager ) {
    	this( Directory.FIELDS );

    	this.manager = actualManager;
    	this.setString( "uri", uri );

    	this.getManager().readByURI( uri, this );
    	}



	public Directory( String uri ) {
    	this( Directory.FIELDS );

    	this.setString( "uri", uri );

    	this.getManager().readByURI( uri, this );
    	}



    protected Directory( String[] fields ) {
    	super( fields );

    	this.set( "type", "directory" );
		}





    // Instance methods.

	public void setManager( DirectoryManager manager ) {
        this.manager = manager;
        }



    public boolean isDirectory() {
        return true;
        }

    public boolean isFile() {
        return false;
        }



    public void setParent( Directory parent ) {
        this.parent = parent;
        }





    protected void setDataFromDisk( HashMap<String, Object> data ) {
    	//  Disk info goes to DIRTY.

    	this.setString( "uri",              (String)data.getOrDefault("uri",                this.get("uri")) );
    	this.setString( "name",             (String)data.getOrDefault("name",               this.get("name")) );
    	this.setLong(   "creationTime",     (Long)  data.getOrDefault("creationTime",       this.get("creationTime")) );
    	this.setLong(   "modificationTime", (Long)  data.getOrDefault("modificationTime",   this.get("modificationTime")) );
    	this.setLong(   "size",             (Long)  data.getOrDefault("size",               this.get("size")) );
        }

    protected void setDataFromDB( HashMap<String, Object> data ) {
    	//  DB data goes to Vector and any existing value goes to DIRTY.

    	for( String field: data.keySet() ) {
    		int    fieldIdx = java.util.Arrays.asList( this.FIELDNAMES ).indexOf( field );
    		Object oldValue = this.get( field );

    		this.setElementAt( data.get(field), fieldIdx );
    		this.DIRTY_VALUES.setElementAt( oldValue, fieldIdx );
    		}

        }


/*
    public void setDirectories( Vector<Directory> subdirectories ) {
        this.subdirectoryNames = subdirectories;
        }
*/

    public void setChildren( Vector<Directory> subdirectories ) {
    	this.children.clear();

    	for( Directory subdirectory: subdirectories ) {
    		this.children.put( subdirectory.getString("uri"), subdirectory );
    		}

    	}

    public Vector<Directory> getDirectories( TreeSet<String> willcards ) {
        Vector<Directory> directories = new Vector<Directory>();

        if( null == this.children || this.children.isEmpty() ) { return directories; }

        for( String dirname : this.children.keySet() ) {
        	Directory subdirectory = this.children.get( dirname );

            if( subdirectory.isDirectory() ) {
				directories.add( this.children.get(dirname) );
            	}
            }

        return directories;
        }

    public Vector<Directory> listDirectories() {
        return this.getDirectories( null );
        }


/*
    public void setFiles( Vector<String> subfiles ) {
        this.subfileNames = subfiles;
        }
*/
    public Vector<File> getFiles( TreeSet<String> willcards ) {
        Vector<File> files = new Vector<File>();

        if( null == this.children || this.children.isEmpty() ) { return files; }

        for( String filename : this.children.keySet() ) {
        	Directory subfile = this.children.get( filename );

            if( subfile.isFile() ) {
				files.add( (File)this.children.get(filename) );
            	}
            }

        return files;
        }  //  END method getFiles()

    public Vector<File> listFiles() {
        return this.getFiles( null );
        }


    @Override
	public String toString() {
    	String output = "directory #%d (type=%s, storage=%s, parent=%s, uri=%s, name=%s, creationTime=%d, modificationTime=%d, size=%d)";

    	output  = this.getClass().getSimpleName().toLowerCase();
    	output += " #" + String.format("%d", this.get("id"));
    	output += " (";

    	String separator = "";
    	for( String fieldName: this.FIELDNAMES ) {
    		int fieldIdx = java.util.Arrays.asList( this.FIELDNAMES ).indexOf( fieldName );
    		if( "id".equals(fieldName) ) {

    			}
    		else if( fieldName.endsWith("Time") ) {
    			output += separator + fieldName;
    			if( null != this.DIRTY_VALUES.elementAt(fieldIdx) ) { output += "*"; }
    			output += "=" + String.format( "%d", this.get(fieldName) );
    			separator = ", ";
    			}
    		else if( fieldName.startsWith("parent") ) {
    			output += separator + fieldName;
    			if( null != this.DIRTY_VALUES.elementAt(fieldIdx) ) { output += "*"; }
    			Object parent = this.get(fieldName);
    			if( parent instanceof Directory ) {
    				output += "=" + String.format( "[directory obj->id=%d]", ((Directory)parent).get("id") );
    				}
    			else {
    				output += "=" + String.format( "%d", parent );
    				}
    			separator = ", ";
    			}
    		else {
    			output += separator + fieldName;
    			if( null != this.DIRTY_VALUES.elementAt(fieldIdx) ) { output += "*"; }
    			output += "=" + String.format( "%s", this.get(fieldName) );
    			separator = ", ";
    			}

    		}
    	output += ")";
		return output;

    	}


    protected DirectoryManager getManager() {
    	if( null == this.manager && null != this.get("uri") ) {
    		this.manager = DirectoryManager.createDirectoryManager( this.getString("uri") );
    		}

		return this.manager;
    	}

    }  //  class Directory