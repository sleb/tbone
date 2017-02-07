package tbone.di;

import com.google.inject.AbstractModule;
import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.context.filesprovider.ConfigFilesProvider;
import tbone.config.Config;
import tbone.config.DefaultConfig;
import tbone.config.DefaultConfigFilesProvider;

public class GuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ConfigFilesProvider.class).to(DefaultConfigFilesProvider.class);
        bind(ConfigurationSource.class).toProvider(DefaultConfigurationSourceProvider.class);
        bind(ConfigurationProvider.class).toProvider(DefaultConfigurationProvider.class);
        bind(Config.class).to(DefaultConfig.class);
    }
}
