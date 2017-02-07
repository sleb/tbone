package tbone.di;

import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.classpath.ClasspathConfigurationSource;
import org.cfg4j.source.context.filesprovider.ConfigFilesProvider;

import javax.inject.Inject;
import javax.inject.Provider;

public class DefaultConfigurationSourceProvider implements Provider<ConfigurationSource> {
    private final ConfigFilesProvider mConfigFilesProvider;

    @Inject
    public DefaultConfigurationSourceProvider(ConfigFilesProvider mConfigFilesProvider) {
        this.mConfigFilesProvider = mConfigFilesProvider;
    }

    @Override
    public ConfigurationSource get() {
        return new ClasspathConfigurationSource(mConfigFilesProvider);
    }
}
