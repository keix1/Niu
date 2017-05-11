package jp.live2d.Niu;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;

import jp.live2d.MyLive2D.LAppLive2DManager;

/**
 * Created by keiichi on 2017/05/09.
 *
 * Niuの本体
 * 入力した言葉の他にもセンサーなど、
 * 他の情報も加味してGUIからユーザへ
 * Niuの感情をフィードバックする
 */

public class Niu {
    private Context context = null;
    private Thinker thinker = null;

    //Niuの体
    private LAppLive2DManager live2DManager = null;

    SharedPreferences prefs = null;


    public Niu(Context context, SharedPreferences prefs) {
        this.context = context;
        this.prefs = prefs;
        thinker = new Thinker(prefs);

        Toast.makeText(context, "にう！", Toast.LENGTH_SHORT).show();
    }

    public void setLive2DManager(LAppLive2DManager live2DManager) {
        this.live2DManager = live2DManager;
    }

    public void say() {
        Toast.makeText(context, "にう！", Toast.LENGTH_SHORT).show();
    }

    public String getWord(String word) {
        if(!word.equals("") && word != null) {
            thinker.will(word);
            live2DManager.takeAction(getEmotion());
            return thinker.getWord();

        } else
            return "";
    }

    private Emotion getEmotion() {
        Emotion emotion = thinker.getEmotion();
        // ....getEmotion();
        //.....getEmotion();
        // 体の部品ごとにEmotionを取ってきて、その総和を現在のエモーションとする
        // など、状況によってエモーションを分ける（これこそ機械学習？）
        return emotion;
    }

}
