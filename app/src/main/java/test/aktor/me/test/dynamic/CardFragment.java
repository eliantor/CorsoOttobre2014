package test.aktor.me.test.dynamic;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import test.aktor.me.test.R;

/**
 * Created by eto on 10/18/14.
 */
public class CardFragment extends Fragment {


    private int color;
    private String text;

    public CardFragment(){
        color = Color.RED;
        text = "Thwe wrong way";
    }

//    public CardFragment(String text){
//        color = Color.RED;
//        text = "High hopes";
//    }


    public static CardFragment create(String text,int color){
        CardFragment f = new CardFragment();
        Bundle args = new Bundle();
        args.putString("text",text);
        args.putInt("color",color);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.card_fragment_layout,container,false);
        Bundle arguments = getArguments();
        text = arguments.getString("text");
        color = arguments.getInt("color");
        v.setBackgroundColor(color);
        TextView textView =(TextView) v.findViewById(R.id.tv_card_name);
        textView.setText(text);
        return v;
    }
}
