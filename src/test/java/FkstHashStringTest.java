import fkst.FkstHashString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(TestHelper.TestResultLoggerExtension.class)
public class FkstHashStringTest extends TestHelper {

    @ParameterizedTest
    @ValueSource(strings = {"password", "changeit", "1234", " ", "[Sv.NB]D3X<;f/W[X&VKta:}hUe*3)r/'jeV6fx6W)W]qUA{yMx=ns<^p@9&%W8G"})
    void generateHash(String data) {
        String expected = getBCsha(data);

        assertDoesNotThrow(() -> {
            String actual = FkstHashString.generateSha3_512(data);
            logger.info(" -> result  :  " + actual);
            assertEquals(expected, actual);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"password"})
    @DisplayName("Make sure same strings have same hash")
    void generateHash_assert_same(String data) {

        assertDoesNotThrow(() -> {
            String actual = FkstHashString.generateSha3_512(data);
            String actual_next = FkstHashString.generateSha3_512(data);
            assertEquals(actual, actual_next);
            logger.info(" -> result  :  " + actual);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"password"})
    @DisplayName("Make almost the same strings have different hash")
    void generateHash_assert_different(String data) {

        assertDoesNotThrow(() -> {
            String actual = FkstHashString.generateSha3_512(data);
            String actual_next = FkstHashString.generateSha3_512(data + "1");
            assertNotEquals(actual, actual_next);
            logger.info(" -> result  :  " + actual);
        });
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null pointer exception is expected when trying to generate hash of null string")
    void generateHash_null_string(String data) {

        assertThrows(NullPointerException.class, () -> FkstHashString.generateSha3_512(data));
    }

    @ParameterizedTest
    @EmptySource
    @DisplayName("No exception is expected when trying to generate hash of empty sting")
    void generateHash_empty_string(String data) {
        assertDoesNotThrow(() -> {
            String actual = FkstHashString.generateSha3_512(data);
            logger.info(" -> result  :  " + actual);
        });
    }
}
