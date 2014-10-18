package test.aktor.me.test;

import android.view.View;

/**
 * Created by eto on 11/10/14.
 */
public abstract class Presenter<T> {

    public Presenter(View view){

    }

    public abstract void bind(T obj);

}
