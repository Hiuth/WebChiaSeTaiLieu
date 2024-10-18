package com.project.webchiasetailieu.services;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.project.webchiasetailieu.models.dtos.DriveDTO;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class DriveService {
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String SERVICE_ACCOUNT_KEY_PATH = getPathToGoogleCredentials();

    private static String getPathToGoogleCredentials() {
        String currentDirectory = System.getProperty("user.dir");
        Path filePath = Paths.get(currentDirectory, "pdfhub3.json");
        return filePath.toString();
    }

    public DriveDTO uploadImageToDrive (File file) throws GeneralSecurityException, IOException {
        DriveDTO res = new DriveDTO();

        try{
            String folderId = "1Te50W56VmP_u8Q0_zUf0mFSXfZVXdNk4";
            Drive drive = createDriveService();
            com.google.api.services.drive.model.File fileMetadata = new com.google.api.services.drive.model.File();
            fileMetadata.setName(file.getName());
            fileMetadata.setParents(Collections.singletonList(folderId));
            FileContent mediaContent = new FileContent("application/pdf", file);
            com.google.api.services.drive.model.File uploadedFile = drive.files().create(fileMetadata, mediaContent)
                    .setFields("id").execute();
            String imageUrl = "https://drive.google.com/uc?export=view&id=" + uploadedFile.getId();
            System.out.println("FILE URL : " + imageUrl);
            file.delete();
            res.setStatus(200);
            res.setMessage("File uploaded successfully");
            res.setUrl(imageUrl);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            res.setStatus(500);
            res.setMessage(e.getMessage());
        }

        return res;
    }

    private Drive createDriveService() throws GeneralSecurityException, IOException {
        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(SERVICE_ACCOUNT_KEY_PATH))
                .createScoped(Collections.singleton(DriveScopes.DRIVE));

        return new Drive.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                credential).build();
    }
}
