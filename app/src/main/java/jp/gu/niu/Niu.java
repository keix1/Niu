package jp.gu.niu;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by keiichi on 2017/05/09.
 */

public class Niu {
    private Context context = null;
    Thinker thinker = new Thinker();

    public Niu(Context context) {
        this.context = context;
        Toast.makeText(context, "にう！", Toast.LENGTH_SHORT).show();

    }

    public void say() {
        Toast.makeText(context, "にう！", Toast.LENGTH_SHORT).show();
    }

    public String getWill(String word) {
        if(!word.equals("") && word != null) {
            return thinker.will(word);
        } else
            return "";
    }

}
