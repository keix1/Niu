package jp.live2d.Niu;

import android.content.Context;
import android.widget.Toast;

import jp.live2d.MyLive2D.LAppLive2DManager;

/**
 * Created by keiichi on 2017/05/09.
 */

public class Niu {
    private Context context = null;
    private Thinker thinker = new Thinker();
    private LAppLive2DManager live2DManager = null;


    public Niu(Context context) {
        this.context = context;
        Toast.makeText(context, "にう！", Toast.LENGTH_SHORT).show();
    }

    public void setLive2DManager(LAppLive2DManager live2DManager) {
        this.live2DManager = live2DManager;
    }

    public void say() {
        Toast.makeText(context, "にう！", Toast.LENGTH_SHORT).show();
    }

    public String getWill(String word) {
        if(!word.equals("") && word != null) {
            live2DManager.niuAction(1);
            return thinker.will(word);
        } else
            return "";
    }

}
