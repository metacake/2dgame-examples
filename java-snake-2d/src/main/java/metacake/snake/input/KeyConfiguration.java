package metacake.snake.input;

import io.metacake.core.input.ActionTrigger;
import io.metacake.core.process.ActionRecognizerName;
import io.metacake.s2d.input.keyboard.KeyboardActionTrigger;
import io.metacake.s2d.process.recognizers.keyboard.KeyActionRecognizer;
import io.metacake.s2d.process.recognizers.keyboard.KeyClickActionRecognizer;

import java.awt.event.KeyEvent;

public class KeyConfiguration {
    public static final ActionRecognizerName W = new ActionRecognizerName();
    public static final KeyActionRecognizer wRecognizer = new KeyClickActionRecognizer(W);
    public static final ActionTrigger<KeyEvent> wTrigger = new KeyboardActionTrigger(KeyEvent.VK_W).bindRecognizer(wRecognizer);

    public static final ActionRecognizerName S = new ActionRecognizerName();
    public static final KeyActionRecognizer sRecognizer = new KeyClickActionRecognizer(S);
    public static final ActionTrigger<KeyEvent> sTrigger = new KeyboardActionTrigger(KeyEvent.VK_S).bindRecognizer(sRecognizer);

    public static final ActionRecognizerName A = new ActionRecognizerName();
    public static final KeyActionRecognizer aRecognizer = new KeyClickActionRecognizer(A);
    public static final ActionTrigger<KeyEvent> aTrigger = new KeyboardActionTrigger(KeyEvent.VK_A).bindRecognizer(aRecognizer);

    public static final ActionRecognizerName D = new ActionRecognizerName();
    public static final KeyActionRecognizer dRecognizer = new KeyClickActionRecognizer(D);
    public static final ActionTrigger<KeyEvent> dTrigger = new KeyboardActionTrigger(KeyEvent.VK_D).bindRecognizer(dRecognizer);
}