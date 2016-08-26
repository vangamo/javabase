package xyz.vangamo.base.storage;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Vector;

public class LocalDirectoryManager extends DirectoryManager {

    // Static attributes.

    static {
    	DirectoryManager.register( new DirectoryManagerRegisterer() {

			@Override
			public boolean validateURI(String uri) {
				return uri.startsWith("/") || uri.startsWith("file:///");
				}

			@Override
			public DirectoryManager getDirectoryManager(String uri) {
				return new LocalDirectoryManager( uri );
				}

    		});
        }





    // Instance attributes.

    private String baseURI = null;





    // Constructors.

    public LocalDirectoryManager() {
        super();
        }

    public LocalDirectoryManager( String uri ) {
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
        System.out.println( "localDirectory.getDirectory("+uri+")" );
        Directory           directory = null;
        BasicFileAttributes attr      = null;
        Path                file      = null;

        try {
        	file = Paths.get(uri).toAbsolutePath();
			attr = Files.readAttributes(file, BasicFileAttributes.class);
		} catch (IOException e) {
			System.out.println( e.getMessage() );
		}

        if( null != attr && attr.isDirectory() ) {
        	uri = file.toFile().getAbsolutePath();

        	directory = new Directory( uri, this );

        	directory.setManager( this );

        	HashMap<String, Object> data = new HashMap<String, Object>();
            data.put( "storage",          "" );
            data.put( "parent",           "" );
            data.put( "uri",              uri );
            data.put( "name",             file.getFileName().toString() );
            data.put( "creationTime",     attr.creationTime().toMillis() );
            data.put( "modificationTime", attr.lastModifiedTime().toMillis() );

            directory.setDataFromDisk(data);
        	}

        try {
        	final Path rootDir = file;
        	final Directory parentBaseDir = directory;
        	final Vector<Directory> children = new Vector<Directory>();

			Files.walkFileTree( file, new FileVisitor<Path>() {

				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

					if( rootDir.equals(dir) ) {
						return FileVisitResult.CONTINUE;
						}
					else {
System.out.println( "localDirectoryManager.walking PRE-DIR " + dir.getFileName() );
						Directory subdir = new Directory( dir.toFile().getAbsolutePath() );

						subdir.set("parent", parentBaseDir);
						children.add( subdir );
						}
					return FileVisitResult.SKIP_SUBTREE;
				}

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
System.out.println( "localDirectoryManager.walking FILE " + file.getFileName() );
					File subfile = new File( file.toFile().getAbsolutePath() );
					subfile.set( "parent", parentBaseDir );
					subfile.set( "size",   attrs.size() );
					subfile.set( "mime",   Files.probeContentType(file) );
					children.add( subfile );

					System.out.println( "localDirectoryManager.walking FILE " + Files.probeContentType(file) );

					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
System.out.println( "localDirectoryManager.walking FILE FAIL " + file.getFileName() );
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
System.out.println( "localDirectoryManager.walking POST DIR " + dir.getFileName() );
					return FileVisitResult.SKIP_SUBTREE;
				}

				});

			HashMap<String, Object> data = new HashMap<String, Object>();
			data.put( "size", new Long(children.size()) );

			directory.setDataFromDisk(data);
			directory.setChildren( children );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return directory;
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