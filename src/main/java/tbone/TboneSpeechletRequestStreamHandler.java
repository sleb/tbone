package tbone;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;
import com.google.common.collect.ImmutableSet;
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

    public TboneSpeechletRequestStreamHandler() {
        super(new TboneSpeechlet(config), APPLICATION_IDS);
    }

    private static Config initializeConfig() {
        ConfigFilesProvider configFilesProvider = () -> Collections.singletonList(Paths.get("application.yaml"));

        ConfigurationSource configurationSource = new ClasspathConfigurationSource(configFilesProvider);

        ConfigurationProvider configurationProvider = new ConfigurationProviderBuilder()
                .withConfigurationSource(configurationSource)
                .build();

        return new DefaultConfig(configurationProvider);
    }

    private static final ImmutableSet<String> APPLICATION_IDS = ImmutableSet.of(
        "amzn1.ask.skill.aedee7a9-f813-4a4d-bf10-ddf8e2e0eb6e",  // Prod
        "amzn1.ask.skill.7df230ef-4a94-43b4-86cc-6b347a5a3a42"); // Test


}
