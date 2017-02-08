package tbone;

import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tbone.di.GuiceModule;

import java.util.Collections;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

public class TboneSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
    private static final String SUPPORTED_APPLICATION_ID = "SUPPORTED_APPLICATION_ID";

    private static final Injector injector = Guice.createInjector(new GuiceModule());
    private static final Logger log = LoggerFactory.getLogger(TboneSpeechletRequestStreamHandler.class);

    public TboneSpeechletRequestStreamHandler() {
        super(
            injector.getInstance(Speechlet.class),
            getSupportedApplicationIds());
    }

    private static Set<String> getSupportedApplicationIds() {
        String supportedApplicationId = System.getenv(SUPPORTED_APPLICATION_ID);
        log.info("{} = {}", SUPPORTED_APPLICATION_ID, supportedApplicationId);
        return Collections.singleton(
            checkNotNull(
                supportedApplicationId,
                "missing environment variable: '{}'",
                SUPPORTED_APPLICATION_ID));
    }
}
