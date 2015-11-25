package ploobs.plantevolution.Interpolators;

/**
 * Created by Bruno on 09/11/2015.
 */
public class InterpolatorsManager {


    private InterpolatorsManager(){}

    /**
     * SingletonHolder is loaded on the first execution of Singleton.getInstance()
     * or the first access to SingletonHolder.INSTANCE, not before.
     */
    private static class SingletonHolder {
        private static final InterpolatorsManager INSTANCE = new InterpolatorsManager();
    }

    public static InterpolatorsManager getInstance() {
        return SingletonHolder.INSTANCE;
    }




}
