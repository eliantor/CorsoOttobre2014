package me.aktor.simpleapp.strategies;

/**
 * Created by Andrea Tortorella on 11/8/14.
 */
public interface ShowDetailStrategy {

    public void showDetail(long id);

    public interface Setter {
        public void strategy(ShowDetailStrategy s);
    }
}
