package sprites;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The SpriteCollection class is used to define the methods that are used by objects that can be drawn on the screen.
 */
public class SpriteCollection {

    private List<Sprite> sprites;

    /**
     * This is a constructor method that initializes the sprite collection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * This method adds a sprite to the sprite collection.
     * @param s the sprite to be added.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * This method removes a sprite from the sprite collection.
     * @param s the sprite to be removed.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * This method notifies further methods that time has passed.
     */
    public void notifyAllTimePassed() {
        for (Sprite singleSprite: new LinkedList<>(this.sprites)) {
            singleSprite.timePassed();
        }
    }

    /**
     * This method draws all the sprites on the screen.
     * @param d the surface to be drawn on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite singleSprite : sprites) {
            singleSprite.drawOn(d);
        }
    }
}
