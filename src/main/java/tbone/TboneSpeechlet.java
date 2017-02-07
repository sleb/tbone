package tbone;

import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SsmlOutputSpeech;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tbone.config.AudioConfig;
import tbone.config.Config;

import java.util.List;
import java.util.Random;

import static com.amazon.speech.speechlet.SpeechletResponse.newAskResponse;
import static com.amazon.speech.speechlet.SpeechletResponse.newTellResponse;

public class TboneSpeechlet implements Speechlet {
    private final Config mConfig;

    TboneSpeechlet(Config config) {
        mConfig = config;
    }

    @Override
    public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {
        log.info(
            "onSessionStarted requestId={}, userId={}, sessionId={}",
            request.getRequestId(),
            session.getUser().getUserId(),
            session.getSessionId());
    }

    @Override
    public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
        log.info(
            "onLaunch requestId={}, userId={}, sessionId={}",
            request.getRequestId(),
            session.getUser().getUserId(),
            session.getSessionId());

        String hint = "Try asking me to play a Wah Wah";

        PlainTextOutputSpeech promptOutputSpeech = new PlainTextOutputSpeech();
        promptOutputSpeech.setText("Welcome to the Sad T-Bone. " + hint);

        PlainTextOutputSpeech hintOutputSpeech = new PlainTextOutputSpeech();
        hintOutputSpeech.setText(hint);

        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(hintOutputSpeech);

        SpeechletResponse response = newAskResponse(promptOutputSpeech, reprompt);
        log.info("should end session is '{}'", response.getShouldEndSession());
        return response;
    }

    @Override
    public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {

        log.info(
            "onIntent intent={}, requestId={}, userId={}, sessionId={}",
            request.getIntent().getName(),
            request.getRequestId(),
            session.getUser().getUserId(),
            session.getSessionId());

        switch (request.getIntent().getName()) {
            case "PlayWaWaIntent":
                return handlePlayWaWaIntent();
            case "AMAZON.HelpIntent":
                return handleHelpIntent();
            case "AMAZON.StopIntent":
                return handleStopIntent();
            default:
                throw new SpeechletException("Invalid Intent");
        }
    }

    private SpeechletResponse handleStopIntent() {
        PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
        outputSpeech.setText("OK");
        return newTellResponse(outputSpeech);
    }

    private SpeechletResponse handleHelpIntent() {
        PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
        outputSpeech.setText("You can try asking me to play a fail sound");

        PlainTextOutputSpeech repromptOutputSpeech = new PlainTextOutputSpeech();
        repromptOutputSpeech.setText("Try asking me to play a fail sound");
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(repromptOutputSpeech);

        return newAskResponse(outputSpeech, reprompt);
    }

    private SpeechletResponse handlePlayWaWaIntent() {
        AudioConfig audioConfig = mConfig.getAudioConfig();
        List<String> fileNames = audioConfig.fileNames();
        Random random = new Random();
        int i = random.nextInt(fileNames.size());
        SsmlOutputSpeech outputSpeech = new SsmlOutputSpeech();
        outputSpeech.setSsml(
                String.format(
                        "<speak>\n" +
                        "  <audio src=\"%s/%s\" />\n" +
                        "</speak>\n",
                        audioConfig.prefix(),
                        fileNames.get(i)));

        return newTellResponse(outputSpeech);
    }

    @Override
    public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {
        log.info("onSessionEnded: sessionId={}", session.getSessionId());
    }

    private static final Logger log = LoggerFactory.getLogger(TboneSpeechlet.class);
}
