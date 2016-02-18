package trader;

import java.io.PrintStream;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.api.utils.Timer;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Player;
import org.rev317.min.api.wrappers.Tile;

public class Trade implements Strategy {
    public boolean activate() {
        return Game.isLoggedIn();
    }

    public void execute() {
        try {
            Player[] arrplayer = Players.getNearest();
            int n = arrplayer.length;
            int n2 = 0;
            while (n2 < n) {
                Player player = arrplayer[n2];
                if (player != null && player.getName().toLowerCase().contains("zqa")) {
                    if (player.getLocation().equals((Object) Constants.spawnTile.getLocation())) {
                        Menu.sendAction((int) 2027, (int) player.getIndex(), (int) 0, (int) 0);
                        Time.sleep(() -> Game.getOpenInterfaceId() == 3323, (int) 15000);
                    }
                    break;
                }
                ++n2;
            }
        } catch (Exception player) {
            // empty catch block
        }
        if (Game.getOpenInterfaceId() == 3323) {
            Variables.timer.restart();
            System.out.println("Reset timer" + Variables.timer.getElapsedTime());
            Time.sleep((int) 500);
            if (TradingLocal.getOpponentsOffer().length < 2) {
                Time.sleep(() -> TradingLocal.getOpponentsOffer().length == 2, (int) 5000);
                this.acceptTrade();
            } else  {
                this.acceptTrade();
            }
        }
        if (Game.getOpenInterfaceId() == 3443) {
            this.acceptOffer();
            Time.sleep((int) 2000);
        }
    }

    public void acceptTrade() {
        Menu.sendAction((int) 315, (int) 0, (int) 0, (int) 3420);
        Time.sleep(() -> Game.getOpenInterfaceId() == 3443, (int) 5000);
    }

    public void acceptOffer() {
        Menu.sendAction((int) 315, (int) 0, (int) 0, (int) 3546);
        Time.sleep(() -> Game.getOpenInterfaceId() == -1, (int) 5000);
    }
}