package collisionDetection;

/**
 * The HitNotifier interface notifies objects when they have been hit.
 */
public interface HitNotifier {

    /**
     * This method adds a hit listener to the list of listeners.
     * @param hl the hit listener to be added.
     */
    void addHitListener(HitListener hl);

    /**
     * This method removes a hit listener from the list of listeners.
     * @param hl the hit listener to be removed.
     */
    void removeHitListener(HitListener hl);
}
