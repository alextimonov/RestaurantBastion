package ua.goit.timonov.enterprise.module_3_1;

/**
 * Interface for simple semaphore
 */
public interface Semaphore {
    /**
     * Requires a permit from semaphore. If there is free permit, acquires it.
     * If not - suspends the thread until free permit will appear
     */
    public void acquire();

    /**
     * Requires given number of permits from semaphore. If there are required number of free permits, acquires them.
     * If not - suspends the thread until required number of free permits will appear
     * @param permits   required permits
     */
    public void acquire(int permits);

    /**
     * Releases permit and returns it to semaphore
     */
    public void release();

    /**
     * Releases given number of permits and returns them to semaphore
     * @param permits       number of permits to release
     */
    public void release(int permits);

    /**
     * returns available number of permits
     * @return      available number of permits
     */
    public int getAvailablePermits();
}
