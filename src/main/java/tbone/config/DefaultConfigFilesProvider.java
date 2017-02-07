package tbone.config;

import org.cfg4j.source.context.filesprovider.ConfigFilesProvider;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

public class DefaultConfigFilesProvider implements ConfigFilesProvider {
    @Override
    public Iterable<Path> getConfigFiles() {
        return Collections.singletonList(Paths.get("application.yaml"));
    }
}
