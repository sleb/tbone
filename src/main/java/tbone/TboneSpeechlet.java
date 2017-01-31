package tbone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SsmlOutputSpeech;

import static com.amazon.speech.speechlet.SpeechletResponse.newAskResponse;
import static com.amazon.speech.speechlet.SpeechletResponse.newTellResponse;

public class TboneSpeechlet implements Speechlet {
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

        Reprompt reprompt = new Reprompt();
        PlainTextOutputSpeech hintOutputSpeech = new PlainTextOutputSpeech();
        hintOutputSpeech.setText(hint);

        return newAskResponse(promptOutputSpeech, reprompt);
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
        outputSpeech.setText("Try asking me to play a fail sound");
        return newTellResponse(outputSpeech);
    }

    private SpeechletResponse handlePlayWaWaIntent() {
        SsmlOutputSpeech outputSpeech = new SsmlOutputSpeech();
        outputSpeech.setSsml(
            "<speak>" +
            "  <audio src=\"https://s3.amazonaws.com/tbone-audio/trombone.mp3\" />" +
            "</speak>");

        return newTellResponse(outputSpeech);
    }

    @Override
    public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {

    }

    private static final Logger log = LoggerFactory.getLogger(TboneSpeechlet.class);
}
