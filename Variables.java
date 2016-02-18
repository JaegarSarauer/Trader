package trader;

import java.awt.Robot;
import java.util.Random;
import org.parabot.environment.api.utils.Timer;

public class Variables {
    public static boolean createAcc;
    public static int accCount;
    public static Robot robot = null;
    public static int macChange;
    public static Random random = new Random();
    public static boolean sentTrade;
    public static Timer timer = new Timer();
}