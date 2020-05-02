package fkst;

import app.HashAlgorithm;
import app.HexUtils;
import app.Parameters;

public class FkstHashString {

    public static String generateSha3_512(String data) {

        HashAlgorithm hashAlgo = new HashAlgorithm();
        byte[] hash = hashAlgo.getHash(data.getBytes(), Parameters.SHA3_512);
        return HexUtils.convertBytesToString(hash);
    }

}
