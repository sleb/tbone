package tbone;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tbone.config.Config;
import tbone.di.GuiceModule;

public class TboneSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
    private static final Logger log = LoggerFactory.getLogger(TboneSpeechletRequestStreamHandler.class);
    private static final Injector injector = Guice.createInjector(new GuiceModule());
    private static final Config config = injector.getInstance(Config.class);

    public TboneSpeechletRequestStreamHandler() {
        super(new TboneSpeechlet(config), config.getApplicationConfig().supportedIds());
    }
}
