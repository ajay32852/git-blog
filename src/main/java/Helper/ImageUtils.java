
package Helper;

import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

public class ImageUtils {
   // private static final String UPLOAD_DIR = "src/main/resources/static/uploads/"; // Set your desired upload directory
    private static final String UPLOAD_DIR = "src/main/resources/static/";
    public static String saveImage(MultipartFile image,String Dir) throws IOException {
        String originalFilename = StringUtils.cleanPath(image.getOriginalFilename());
        String fileExtension = getFileExtension(originalFilename);
        String storedFilename = generateUniqueFileName(fileExtension);

        Path uploadPath = Path.of(UPLOAD_DIR+"/"+Dir).toAbsolutePath().normalize();
        Files.createDirectories(uploadPath);

        Path filePath = uploadPath.resolve(storedFilename);
        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        //return filePath.toString();
        return Dir+"/"+storedFilename;
    }

    private static String getFileExtension(String filename) {
        int extensionIndex = filename.lastIndexOf(".");
        return (extensionIndex == -1) ? "" : filename.substring(extensionIndex + 1);
    }

    private static String generateUniqueFileName(String fileExtension) {
        String uniqueFilename = UUID.randomUUID().toString();
        return uniqueFilename + "." + fileExtension;
    }
}
