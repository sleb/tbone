package tbone;

import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;
import com.google.inject.Guice;
import com.google.inject.Injector;
import tbone.di.GuiceModule;

import java.util.Collections;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

public class TboneSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
    private static final String SUPPORTED_APPLICATION_ID = "SUPPORTED_APPLICATION_ID";

    private static final Injector injector = Guice.createInjector(new GuiceModule());

    public TboneSpeechletRequestStreamHandler() {
        super(
            injector.getInstance(Speechlet.class),
            getSupportedApplicationIds());
    }

    private static Set<String> getSupportedApplicationIds() {
        String supportedApplicationId = System.getenv(SUPPORTED_APPLICATION_ID);
        return Collections.singleton(
            checkNotNull(
                supportedApplicationId,
                "missing environment variable: '{}'",
                SUPPORTED_APPLICATION_ID));
    }
}
