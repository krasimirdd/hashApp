import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TestHelper {
    public static Logger logger = Logger.getLogger("---TestWatcher---");

    public static class TestResultLoggerExtension implements TestWatcher, AfterAllCallback, BeforeEachCallback {
        enum TestResultStatus {
            SUCCESSFUL, ABORTED, FAILED, DISABLED;
        }

        private List<FkstHashFileTest.TestResultLoggerExtension.TestResultStatus> testResultsStatus = new ArrayList<>();

        @Override
        public void beforeEach(ExtensionContext context) {
            logger.info(String.format("Executing test %s", context.getElement().get()));
        }

        @Override
        public void testDisabled(ExtensionContext context, Optional<String> reason) {
            logger.info(String.format("Test Disabled for test %s - %s : with reason :- %s",
                    context.getElement().get(),
                    context.getDisplayName(),
                    reason.orElse("No reason")));

            testResultsStatus.add(FkstHashFileTest.TestResultLoggerExtension.TestResultStatus.DISABLED);
        }

        @Override
        public void testAborted(ExtensionContext context, Throwable cause) {
            logger.info(String.format("Test Aborted for test %s - %s: ", context.getElement().get(), context.getDisplayName()));

            testResultsStatus.add(FkstHashFileTest.TestResultLoggerExtension.TestResultStatus.ABORTED);
        }

        @Override
        public void testSuccessful(ExtensionContext context) {
            logger.info(String.format("Test Successful for test %s - %s ", context.getElement().get(), context.getDisplayName()));

            testResultsStatus.add(FkstHashFileTest.TestResultLoggerExtension.TestResultStatus.SUCCESSFUL);
        }

        @Override
        public void testFailed(ExtensionContext context, Throwable cause) {
            logger.info(String.format("Test Failed for test %s - %s: ", context.getElement().get(), context.getDisplayName()));

            testResultsStatus.add(TestResultStatus.FAILED);
        }

        @Override
        public void afterAll(ExtensionContext context) {
            Map<FkstHashFileTest.TestResultLoggerExtension.TestResultStatus, Long> summary = testResultsStatus.stream()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

            logger.info(String.format("Test result summary for %s %s", context.getDisplayName(), summary.toString()));
            logger.info(System.lineSeparator() + System.lineSeparator());
        }


    }

    public String getBCsha(String data) {
        final SHA3.DigestSHA3 sha3 = new SHA3.Digest512();
        sha3.update(data.getBytes());

        return new String(Hex.encode(sha3.digest()));
    }

    public String getBCsha(Path path) throws IOException {
        final SHA3.DigestSHA3 sha3 = new SHA3.Digest512();
        byte[] bytes = Files.readAllBytes(path);
        sha3.update(bytes);

        return new String(Hex.encode(sha3.digest()));
    }
}
