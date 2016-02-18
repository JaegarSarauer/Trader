package trader;

import org.parabot.environment.api.utils.Time;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Tile;

public class Location {
    public Tile tile;

    public Location(int x, int y) {
        this(x, y, 0);
    }

    public Location(int x, int y, int z) {
        this.tile = new Tile(x, y, z);
    }

    public Location(Tile location) {
        this(location.getX(), location.getY(), location.getPlane());
    }

    public final int getX() {
        return this.tile.getX();
    }

    public final int getY() {
        return this.tile.getY();
    }

    public final int getRegionX() {
        return this.tile.getRegionX();
    }

    public final int getRegionY() {
        return this.tile.getRegionY();
    }

    public final int getPlane() {
        return this.tile.getPlane();
    }

    public final int distanceTo() {
        return this.tile.distanceTo();
    }

    public final boolean isOnMinimap() {
        if (this.distanceTo() < 16) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "Tile: [" + this.getX() + ", " + this.getY() + "]";
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        Tile t = (Tile) obj;
        if (t.getX() == this.getX() && t.getY() == this.getY() && t.getPlane() == this.getPlane()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.tile.hashCode();
    }

    public void walkTo() {
        this.walkTo(15000);
    }

    public void walkTo(int sleep) {
        this.tile.walkTo();
        Time.sleep(() -> Players.getMyPlayer().getLocation().equals((Object) this.getLocation()), (int) sleep);
    }

    public boolean isWalkable() {
        return this.tile.isWalkable();
    }

    public boolean isReachable(boolean isObject) {
        return this.tile.isReachable(isObject);
    }

    public boolean isReachable() {
        return this.isReachable(this.isObjectTile());
    }

    public boolean isObjectTile() {
        return this.tile.isObjectTile();
    }

    public Tile getLocation() {
        return this.tile;
    }
}