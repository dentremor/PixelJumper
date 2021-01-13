package de.se2projekt.level.tiles;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import org.json.simple.JSONObject;

public interface ITile {

    public Rectangle getBounds();

    public boolean isSolid();

    public void setSolid(final boolean isSolid);

    public boolean isDeadly();

    public void setDeadly(final boolean isDeadly);

    public boolean isClimbable();

    public void setClimbable(final boolean climbable);

    public int getTileId();

    public int getWidth();

    public int getHeight();

    public void setX(final int x);

    public void setY(final int y);

    public int getX();

    public int getY();

    public Image getImage();

    public void setPos(final int x, final int y);

    public JSONObject getAsJson();
}
