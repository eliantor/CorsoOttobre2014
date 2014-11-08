package me.aktor.simpleapp.strategies;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * Created by Andrea Tortorella on 11/8/14.
 */
public class FragmentRambo extends Fragment implements ShowDetailStrategy{

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ShowDetailStrategy.Setter){
            ((ShowDetailStrategy.Setter) activity).strategy(this);
        }
    }

    @Override
    public void showDetail(long id) {

    }
}
