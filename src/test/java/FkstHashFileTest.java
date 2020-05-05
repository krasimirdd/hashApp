import fkst.FkstHashFile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(TestHelper.TestResultLoggerExtension.class)
public class FkstHashFileTest extends TestHelper {

    @ParameterizedTest
    @ValueSource(strings = {
            "src\\test\\resources\\test_1", "src\\test\\resources\\test_2", "src\\test\\resources\\test_3",
            "src\\test\\resources\\test_4", "src\\test\\resources\\test_5", "src\\test\\resources\\test_6"})
    void generateHash(Path path) throws IOException {
        String expected = getBCsha(path);

        assertDoesNotThrow(() -> {
            String actual = FkstHashFile.generateSha3_512(path);
            logger.info(" -> result  :  " + actual);
            assertEquals(expected, actual);
        });
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null pointer exception is expected when trying to generate hash of null file path")
    void generateHash_null_path(Path path) {
        assertThrows(NullPointerException.class, () -> FkstHashFile.generateSha3_512(path));
    }

}
