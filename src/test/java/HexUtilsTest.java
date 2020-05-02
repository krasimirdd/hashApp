import app.HexUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

@ExtendWith(TestHelper.TestResultLoggerExtension.class)
public class HexUtilsTest {

    @Test
    public void convertToUint() {
        String message = "password";
        byte[] messageBytes = message.getBytes();
        int[] messageInts = HexUtils.convertToUint(messageBytes);

        ByteBuffer byteBuffer = ByteBuffer.allocate(messageInts.length * 4);
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        intBuffer.put(messageInts);

        byte[] reconstructedMessageBytes = byteBuffer.array();
        String reconstructedMessage = new String(reconstructedMessageBytes);

        System.out.println("Actual: " + reconstructedMessage);
        System.out.println("Expected: " + message);

    }

    @Test
    public void convertFromLittleEndianTo64_AndBack() {
        String message = "password";
        byte[] messageBytes = message.getBytes();
        int[] messageInts = HexUtils.convertToUint(messageBytes);

        BigInteger bigInteger = HexUtils.convertFromLittleEndianTo64(messageInts);
        int[] ints = HexUtils.convertFrom64ToLittleEndian(bigInteger);

        Assert.assertArrayEquals(messageInts, ints);
    }
}
