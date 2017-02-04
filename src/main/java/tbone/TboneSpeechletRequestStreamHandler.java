package tbone;

import com.google.common.collect.ImmutableSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

public class TboneSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
    public TboneSpeechletRequestStreamHandler() {
        super(new TboneSpeechlet(), APPLICATION_IDS);
        log.info("initialized speechlet with {}", APPLICATION_IDS);
    }

    private static final ImmutableSet<String> APPLICATION_IDS = ImmutableSet.of(
        "amzn1.ask.skill.aedee7a9-f813-4a4d-bf10-ddf8e2e0eb6e",  // Prod
        "amzn1.ask.skill.7df230ef-4a94-43b4-86cc-6b347a5a3a42"); // Test

    private static final Logger log = LoggerFactory.getLogger(TboneSpeechletRequestStreamHandler.class);
}
