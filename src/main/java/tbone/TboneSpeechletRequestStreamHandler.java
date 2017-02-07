package tbone;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;
import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.provider.ConfigurationProviderBuilder;
import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.classpath.ClasspathConfigurationSource;
import org.cfg4j.source.context.filesprovider.ConfigFilesProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tbone.config.Config;
import tbone.config.DefaultConfig;

import java.nio.file.Paths;
import java.util.Collections;

public class TboneSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
    private static final Logger log = LoggerFactory.getLogger(TboneSpeechletRequestStreamHandler.class);
    private static final Config config = initializeConfig();

    public static void main(String[] args) {
        new TboneSpeechletRequestStreamHandler();
        System.out.println(config.getApplicationConfig().supportedIds());
    }

    public TboneSpeechletRequestStreamHandler() {
        super(new TboneSpeechlet(config), config.getApplicationConfig().supportedIds());
    }

    private static Config initializeConfig() {
        ConfigFilesProvider configFilesProvider = () -> Collections.singletonList(Paths.get("application.yaml"));

        ConfigurationSource configurationSource = new ClasspathConfigurationSource(configFilesProvider);

        ConfigurationProvider configurationProvider = new ConfigurationProviderBuilder()
                .withConfigurationSource(configurationSource)
                .build();

        return new DefaultConfig(configurationProvider);
    }
}
