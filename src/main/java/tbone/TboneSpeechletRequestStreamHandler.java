package tbone;

import com.google.common.collect.ImmutableSet;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

public class TboneSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
    public TboneSpeechletRequestStreamHandler() {
        super(new TboneSpeechlet(), APPLICATION_IDS);
    }

    private static final ImmutableSet<String> APPLICATION_IDS = ImmutableSet.of(
        "amzn1.ask.skill.aedee7a9-f813-4a4d-bf10-ddf8e2e0eb6e");
}
