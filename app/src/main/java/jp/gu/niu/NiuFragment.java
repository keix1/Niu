package jp.gu.niu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

/**
 * Created by xkei4 on 2017/05/02.
 */

public class NiuFragment extends Fragment {

    Thinker thinker = null;

    // Viewが生成し終わった時に呼ばれるメソッド
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // TextViewをひも付けます

        Toast.makeText(getActivity(), "にう！", Toast.LENGTH_SHORT).show();
        thinker = new Thinker(getActivity());
    }

    public String getReply(String userWord) {
        return thinker.getReply(userWord);
    }

    public void say() {
        Toast.makeText(getActivity(), "にう！", Toast.LENGTH_SHORT).show();
    }

    public String getWill(String word) {
        if(!word.equals("") && word != null) {
            return thinker.will(word);
        } else
            return "";
    }

}
