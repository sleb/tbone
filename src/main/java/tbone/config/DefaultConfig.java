package tbone.config;

import org.cfg4j.provider.ConfigurationProvider;

public class DefaultConfig implements Config {

    private AudioConfig mAudioConfig;

    public DefaultConfig(ConfigurationProvider configurationProvider) {
        mAudioConfig = configurationProvider.bind("audio", AudioConfig.class);
    }

    @Override
    public AudioConfig getAudioConfig() {
        return mAudioConfig;
    }
}
