import fkst.FkstHashConsole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(TestHelper.TestResultLoggerExtension.class)
public class FkstHashConsoleTest extends TestHelper {

    @ParameterizedTest
    @ValueSource(strings = {"password", "changeit", "1234", " ", "[Sv.NB]D3X<;f/W[X&VKta:}hUe*3)r/'jeV6fx6W)W]qUA{yMx=ns<^p@9&%W8G"})
    void generateHash(String input) {
        final ByteArrayInputStream inStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inStream);
        Scanner scanner = new Scanner(System.in);

        assertDoesNotThrow(() -> {
            String actual = FkstHashConsole.generateSha3_512(scanner.nextLine());
            logger.info(" -> result  :  " + actual);
        });
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null pointer exception is expected when trying to generate hash of null input")
    void generateHash_null_input(String input) {

        assertThrows(NullPointerException.class, () -> FkstHashConsole.generateSha3_512(input));
    }

    @ParameterizedTest
    @EmptySource
    @DisplayName("No exception is expected when trying to generate hash of empty input")
    void generateHash_empty_input(String data) {
        assertDoesNotThrow(() -> {
            String actual = FkstHashConsole.generateSha3_512(data);
            logger.info(" -> result  :  " + actual);
        });
    }
}
