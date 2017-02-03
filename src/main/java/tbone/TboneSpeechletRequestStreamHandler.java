package tbone;

import com.google.common.collect.ImmutableSet;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

public class TboneSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
    public TboneSpeechletRequestStreamHandler() {
        super(new TboneSpeechlet(), APPLICATION_IDS);
    }

    private static final ImmutableSet<String> APPLICATION_IDS = ImmutableSet.of("amzn1.ask.skill.7df230ef-4a94-43b4-86cc-6b347a5a3a42"); // Test
}
