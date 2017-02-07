package tbone.config;

import org.cfg4j.provider.ConfigurationProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DefaultConfig implements Config {
    private ApplicationConfig mApplicationConfig;
    private AudioConfig mAudioConfig;

    @Inject
    public DefaultConfig(ConfigurationProvider configurationProvider) {
        mApplicationConfig = configurationProvider.bind("application", ApplicationConfig.class);
        mAudioConfig = configurationProvider.bind("audio", AudioConfig.class);
    }

    @Override
    public ApplicationConfig getApplicationConfig() {
        return mApplicationConfig;
    }

    @Override
    public AudioConfig getAudioConfig() {
        return mAudioConfig;
    }
}
