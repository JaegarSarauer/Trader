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
import org.rev317.min.api.wrappers.Item;
import org.rev317.min.api.wrappers.Tile;

public class Trading {
    private static HashMap<String, Integer> settings = Context.getInstance().getServerProviderInfo().getSettings();

    public static boolean isOpen(boolean first) {
        if (Loader.getClient().getOpenInterfaceId() == (first ? settings.get("first_trade_interface_id")
                : settings.get("second_trade_interface_id")).intValue()) {
            return true;
        }
        return false;
    }

    public static boolean isOpen() {
        if (Loader.getClient().getOpenInterfaceId() != settings.get("first_trade_interface_id").intValue()
                && Loader.getClient().getOpenInterfaceId() != settings.get("second_trade_interface_id").intValue()) {
            return false;
        }
        return true;
    }

    public static void close() {
        Players.getMyPlayer().getLocation().walkTo();
        Time.sleep((SleepCondition) new SleepCondition() {

            public boolean isValid() {
                return !Trading.isOpen();
            }
        }, (int) 2500);
    }

    public static Item[] getMyOffer() {
        ArrayList<Item> items = new ArrayList<Item>();
        int[] ids = Trading.getItemIDs(settings.get("my_offer_interface_id"));
        int[] stacks = Trading.getItemStacks(settings.get("my_offer_interface_id"));
        int i = 0;
        while (i < ids.length) {
            if (ids[i] > 0) {
                items.add(new Item(ids[i], stacks[i], i));
            }
            ++i;
        }
        return items.toArray(new Item[0]);
    }

    public static Item[] getOpponentsOffer() {
        ArrayList<Item> items = new ArrayList<Item>();
        int[] ids = Trading.getItemIDs(settings.get("opponent_offer_interface_id"));
        int[] stacks = Trading.getItemStacks(settings.get("opponent_offer_interface_id"));
        int i = 0;
        while (i < ids.length) {
            if (ids[i] > 0) {
                items.add(new Item(ids[i], stacks[i], i));
            }
            ++i;
        }
        return items.toArray(new Item[0]);
    }

    private static int[] getItemIDs(int interfaceID) {
        int[] items;
        Interface i = Loader.getClient().getInterfaceCache()[interfaceID];
        if (i != null && (items = Trading.copyFromLongArray(i.getItems())) != null && items.length > 0) {
            return items;
        }
        return new int[0];
    }

    private static int[] getItemStacks(int interfaceID) {
        int[] stacks;
        Interface i = Loader.getClient().getInterfaceCache()[interfaceID];
        if (i != null && (stacks = Trading.copyFromLongArray(i.getStackSizes())) != null && stacks.length > 0) {
            return stacks;
        }
        return new int[0];
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