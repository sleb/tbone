package tbone.di;

import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.provider.ConfigurationProviderBuilder;
import org.cfg4j.source.ConfigurationSource;

import javax.inject.Inject;
import javax.inject.Provider;

public class DefaultConfigurationProvider implements Provider<ConfigurationProvider> {
    private final ConfigurationSource mConfigurationSource;

    @Inject
    public DefaultConfigurationProvider(ConfigurationSource mConfigurationSource) {
        this.mConfigurationSource = mConfigurationSource;
    }

    @Override
    public ConfigurationProvider get() {
        return new ConfigurationProviderBuilder()
                .withConfigurationSource(mConfigurationSource)
                .build();
    }
}
