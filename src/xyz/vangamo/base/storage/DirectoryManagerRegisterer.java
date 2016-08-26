/**
 * 
 */
package xyz.vangamo.base.storage;

/**
 * @author igarrido
 *
 */
interface DirectoryManagerRegisterer {
    boolean          validateURI( String uri );
    DirectoryManager getDirectoryManager( String uri );
}
