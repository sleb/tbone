package tbone;

import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;
import com.google.inject.Guice;
import com.google.inject.Injector;
import tbone.config.Config;
import tbone.di.GuiceModule;

public class TboneSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
    private static final Injector injector = Guice.createInjector(new GuiceModule());

    public TboneSpeechletRequestStreamHandler() {
        super(
                injector.getInstance(Speechlet.class),
                injector.getInstance(Config.class).getApplicationConfig().supportedIds());
    }
}
