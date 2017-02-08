package tbone.config;

import org.cfg4j.provider.ConfigurationProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DefaultConfig implements Config {
    private final AudioConfig mAudioConfig;

    @Inject
    public DefaultConfig(ConfigurationProvider configurationProvider) {
        mAudioConfig = configurationProvider.bind("audio", AudioConfig.class);
    }

    @Override
    public AudioConfig getAudioConfig() {
        return mAudioConfig;
    }
}
