package trader;

import java.util.ArrayList;
import java.util.HashMap;

import org.parabot.core.Context;
import org.parabot.core.desc.ServerProviderInfo;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.rev317.min.Loader;
import org.rev317.min.accessors.Interface;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.Trading;
import org.rev317.min.api.wrappers.Item;
import org.rev317.min.api.wrappers.Tile;

public class TradingLocal {
    private static HashMap<String, Integer> settings = Context.getInstance().getServerProviderInfo().getSettings();

    public static boolean isOpen(boolean first) {
        return (Loader.getClient().getOpenInterfaceId() == (first ? settings.get("first_trade_interface_id")
                : settings.get("second_trade_interface_id")).intValue());
    }

    public static boolean isOpen() {
        return (Loader.getClient().getOpenInterfaceId() == settings.get("first_trade_interface_id").intValue()
                || Loader.getClient().getOpenInterfaceId() == settings.get("second_trade_interface_id").intValue());
    }

    public static void close() {
        Players.getMyPlayer().getLocation().walkTo();
        Time.sleep((SleepCondition) new SleepCondition() {

            public boolean isValid() {
                return !TradingLocal.isOpen();
            }
        }, (int) 2500);
    }

    public static Item[] getMyOffer() {
        if (isOpen())
            return Trading.getMyOffer();
        return null;
    }

    public static Item[] getOpponentsOffer() {
        if (isOpen())
            return Trading.getOpponentsOffer();
        return null;
    }
    
    public static void acceptOffer() {
        Time.sleep((int) 500, (int) 750);
        Mouse.getInstance().click(260, 190, true);
        Time.sleep((int) 500, (int) 750);
    }

    public static void acceptTrade() {
        Time.sleep((int) 500, (int) 750);
        Mouse.getInstance().click(230, 310, true);
        Time.sleep((int) 500, (int) 750);
    }

    public static int[] copyFromLongArray(int[] js) {
        int[] dest = new int[js.length];
        int i = 0;
        while (i < js.length) {
            dest[i] = (int) js[i];
            ++i;
        }
        return dest;
    }

}