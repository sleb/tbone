package tbone.di;

import com.amazon.speech.speechlet.Speechlet;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.provider.ConfigurationProviderBuilder;
import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.classpath.ClasspathConfigurationSource;
import org.cfg4j.source.context.filesprovider.ConfigFilesProvider;
import tbone.TboneSpeechlet;
import tbone.config.Config;
import tbone.config.DefaultConfig;

import java.nio.file.Paths;
import java.util.Collections;

public class GuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Config.class).to(DefaultConfig.class);
        bind(Speechlet.class).to(TboneSpeechlet.class);
    }

    @Provides
    protected ConfigurationProvider provideConfigurationProvider() {
        ConfigFilesProvider configFilesProvider = () -> Collections.singletonList(Paths.get("application.yaml"));
        ConfigurationSource configurationSource = new ClasspathConfigurationSource(configFilesProvider);
        return new ConfigurationProviderBuilder().withConfigurationSource(configurationSource).build();
    }
}
