package fkst;

import app.HashAlgorithm;
import app.HexUtils;
import app.Parameters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

public class FkstHashFile {
    static Logger logger = Logger.getLogger(FkstHashFile.class.getName());

    public static String generateSha3_512(Path path) throws IOException {

        try {
            HashAlgorithm hashAlgo = new HashAlgorithm();
            byte[] hash = hashAlgo.getHash(Files.readAllBytes(path), Parameters.SHA3_512);
            return HexUtils.convertBytesToString(hash);
//            // Create MessageDigest instance for MD5
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            //Add password bytes to digest
//            md.update(Files.readAllBytes(path));
//            //Get the hash's bytes
//            byte[] bytes = md.digest();
//            //This bytes[] has bytes in decimal format;
//            //Convert it to hexadecimal format
//            StringBuilder sb = new StringBuilder();
//            for (byte aByte : bytes) {
//                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
//            }
//            //Get complete hashed password in hex format
//            return sb.toString();
        } catch (IOException e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

}
