import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.icafe4j.image.ImageType;
import com.icafe4j.image.meta.Metadata;
import com.icafe4j.image.meta.MetadataType;

import xyz.vangamo.base.storage.CifsDirectoryManager;
import xyz.vangamo.base.storage.Directory;
import xyz.vangamo.base.storage.DirectoryManager;
import xyz.vangamo.base.storage.File;
import xyz.vangamo.base.storage.LocalDirectoryManager;

public class RapidTest {

	public static void main(String[] args) {

		String YOUR_API_KEY = "440938199569-942nrdfoi4hsmena08mocl0q7daekcno.apps.googleusercontent.com";
		String YOUR_API_SECRET = "zHRPjb8eX0i0XGHcBSOmnZHx";

		OAuth20Service service = new ServiceBuilder()
                .apiKey(YOUR_API_KEY)
                .apiSecret(YOUR_API_SECRET)
                .build( com.github.scribejava.apis.GoogleApi20.instance() );

		String code = "4/D0ZckQTYNa8B4Qk7ddtLHShPG0Vohi-CWOk-CmB0dUY";
		if( null == code || "".equals(code) ) {
			HashMap<String,String> additionalParam = new HashMap<String,String>();
			additionalParam.put("scope", "https://www.googleapis.com/auth/drive.readonly");

			System.out.println( service.getAuthorizationUrl(additionalParam) );
			}

// Access: ya29.Ci9KA-82tqVGVo71Wh3bma9Bfore0ifpXD0JwFvxv1TYB4aAytVCkP0dnsRnU_7Lkw Refresh: 1/hW4KQOhtArL9_d4qHP0mrJ9GOEMEjhF6okfs90awmu0

		OAuth2AccessToken accessToken = null;
		String            sRefreshToken = "1/hW4KQOhtArL9_d4qHP0mrJ9GOEMEjhF6okfs90awmu0";
		if( null == sRefreshToken || "".equals(sRefreshToken) ) {
			try {
				accessToken = service.getAccessToken(code);

				System.out.println( "Access: " + accessToken.getAccessToken() + " Refresh: " + accessToken.getRefreshToken() );
				}
			catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}
			}
		else {
			try {
				accessToken = service.refreshAccessToken(sRefreshToken);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}

		if( !"".equals(sRefreshToken) && null != accessToken ) {
			//final OAuthRequest request = new OAuthRequest(Verb.GET, "https://www.googleapis.com/drive/v2/files", service);

System.out.println("STARTPAGE\n==============================");
			final OAuthRequest requestStart = new OAuthRequest(Verb.GET, "https://www.googleapis.com/drive/v3/changes/startPageToken", service);
			service.signRequest(accessToken, requestStart); // the access token from step 4
			final Response responseStart = requestStart.send();
			try {
				System.out.println(responseStart.getBody());
				}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}

System.out.println("FIND\n==============================");
			final OAuthRequest requestFind = new OAuthRequest(Verb.GET, "https://www.googleapis.com/drive/v3/files?q=name%3D'Fotos%20Picasa'", service);
			service.signRequest(accessToken, requestFind); // the access token from step 4
			final Response responseFind = requestFind.send();
			try {
				System.out.println(responseFind.getBody());
				}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}

System.out.println("META\n==============================");
			final OAuthRequest requestMeta = new OAuthRequest(Verb.GET, "https://www.googleapis.com/drive/v3/files/0B1GwWsVdO3gTRFd0SmxUdG55VEU?fields=appProperties,capabilities,contentHints,createdTime,description,explicitlyTrashed,fileExtension,folderColorRgb,fullFileExtension,headRevisionId,iconLink,id,imageMediaMetadata,isAppAuthorized,kind,lastModifyingUser,md5Checksum,mimeType,modifiedByMeTime,modifiedTime,name,originalFilename,ownedByMe,owners,parents,permissions,properties,quotaBytesUsed,shared,sharedWithMeTime,sharingUser,size,spaces,starred,thumbnailLink,trashed,version,videoMediaMetadata,viewedByMe,viewedByMeTime,viewersCanCopyContent,webContentLink,webViewLink,writersCanShare", service);
			service.signRequest(accessToken, requestMeta); // the access token from step 4
			final Response responseMeta = requestMeta.send();
			try {
				System.out.println(responseMeta.getBody());
				}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}

System.out.println("LIST\n==============================");
			final OAuthRequest requestList = new OAuthRequest(Verb.GET, "https://www.googleapis.com/drive/v3/files?q='0B1GwWsVdO3gTRFd0SmxUdG55VEU'%20in%20parents", service);
			service.signRequest(accessToken, requestList); // the access token from step 4
			final Response responseList = requestList.send();
			try {
				System.out.println(responseList.getBody());
				}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}

System.out.println("META FILE\n==============================");
			final OAuthRequest requestMetaFile = new OAuthRequest(Verb.GET, "https://www.googleapis.com/drive/v3/files/0B1GwWsVdO3gTSWdOeElGTFZ6dkk?fields=appProperties,capabilities,contentHints,createdTime,description,explicitlyTrashed,fileExtension,folderColorRgb,fullFileExtension,headRevisionId,iconLink,id,imageMediaMetadata,isAppAuthorized,kind,lastModifyingUser,md5Checksum,mimeType,modifiedByMeTime,modifiedTime,name,originalFilename,ownedByMe,owners,parents,permissions,properties,quotaBytesUsed,shared,sharedWithMeTime,sharingUser,size,spaces,starred,thumbnailLink,trashed,version,videoMediaMetadata,viewedByMe,viewedByMeTime,viewersCanCopyContent,webContentLink,webViewLink,writersCanShare", service);
			service.signRequest(accessToken, requestMetaFile); // the access token from step 4
			final Response responseMetaFile = requestMetaFile.send();
			try {
				System.out.println(responseMetaFile.getBody());
				}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}


System.out.println("HEAD FILE\n==============================");
			final OAuthRequest requestHeadContentFile = new OAuthRequest(Verb.GET, "https://www.googleapis.com/drive/v3/files/0B1GwWsVdO3gTSWdOeElGTFZ6dkk?alt=media", service);
			service.signRequest(accessToken, requestHeadContentFile); // the access token from step 4
			requestHeadContentFile.addHeader( "range", "bytes=0-" + String.valueOf(com.icafe4j.image.ImageIO.IMAGE_MAGIC_NUMBER_LEN) );
			final Response responseHeadContentFile = requestHeadContentFile.send();
			try {
				com.icafe4j.io.PeekHeadInputStream pk = new com.icafe4j.io.PeekHeadInputStream( responseHeadContentFile.getStream(), com.icafe4j.image.ImageIO.IMAGE_MAGIC_NUMBER_LEN );
				ImageType imageType = com.icafe4j.image.util.IMGUtils.guessImageType( pk );

				System.out.println( String.valueOf( com.icafe4j.image.ImageIO.IMAGE_MAGIC_NUMBER_LEN ) + " " + imageType );
/*
				String imageHeader = responseContentFile.getBody();
				ImageType imageType = com.icafe4j.image.util.IMGUtils.guessImageType( imageHeader.getBytes() );

				System.out.println( String.valueOf( imageHeader.length() ) + " " + imageType );
*/
				}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}



System.out.println("CONTENT FILE\n==============================");
			final OAuthRequest requestContentFile = new OAuthRequest(Verb.GET, "https://www.googleapis.com/drive/v3/files/0B1GwWsVdO3gTSWdOeElGTFZ6dkk?alt=media", service);
			service.signRequest(accessToken, requestContentFile); // the access token from step 4
			final Response responseContentFile = requestContentFile.send();
			try {
				Map<MetadataType, Metadata> metadataMap = Metadata.readMetadata( responseContentFile.getStream() );

			    System.out.println("Start of metadata information:");
			    System.out.println("Total number of metadata entries: " + metadataMap.size());
			    int i = 0;
			    for(Map.Entry<MetadataType, Metadata> entry : metadataMap.entrySet()) {
			        System.out.println("Metadata entry " + i + " - " + entry.getKey());
			        entry.getValue().showMetadata();
			        i++;
			        System.out.println("-----------------------------------------");
			    	}
			    System.out.println("End of metadata information.");

				}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
		}

/*
		HashMap headers = new HashMap();
		Verb httpVerb = null;
		String completeUrl;
		String bodyContents;
		OAuthAsyncRequestCallback callback;
		ResponseConverter converter;
		service.executeAsync(headers , httpVerb, completeUrl, bodyContents, callback, converter);
*/


		try {
			Map<MetadataType, Metadata> metadataMap = Metadata.readMetadata( "/home/igarrido/Descargas/DSC03231.JPG" );

		    System.out.println("Start of metadata information:");
		    System.out.println("Total number of metadata entries: " + metadataMap.size());
		    int i = 0;
		    for(Map.Entry<MetadataType, Metadata> entry : metadataMap.entrySet()) {
		        System.out.println("Metadata entry " + i + " - " + entry.getKey());
		        entry.getValue().showMetadata();
		        i++;
		        System.out.println("-----------------------------------------");
		    }
		    System.out.println("End of metadata information.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    String mountPointDirname = "smb://192.168.1.47/Elements/Google Drive/Fotos/Fotos Picasa/";
        mountPointDirname = "smb://192.168.1.47/Elements/Google Drive/Fotos/Fotos Picasa/De eventos/Con el nene/";

        mountPointDirname = "/home/igarrido/Proyectos/messages/WhatsApp/Media/WhatsApp Images/";
        mountPointDirname = "/home/igarrido/Descargas/";
if( 1-1 == 0 ) { return; }
DirectoryManager dm1 = new CifsDirectoryManager();
DirectoryManager dm2 = new LocalDirectoryManager();

        Directory dir = DirectoryManager.createDirectory( mountPointDirname );

System.out.println( "DIR " + dir );
if( dir.isDirty() ) { System.out.println( "DIR has changed" ); }


System.out.println( "DIR " + dir.getString("name") + " DIRECTORIES:" );

        Vector<Directory> subdirs = dir.listDirectories();

        for( Directory subdir : subdirs ) {
            System.out.println( "main(): " + subdir );
            }

System.out.println( "DIR " + dir.getString("name") + " FILES:" );

        Vector<File> subfiles = dir.listFiles();

        for( Directory subfile : subfiles ) {
            System.out.println( "main(): " + subfile );
            }


        return;
	}

}
