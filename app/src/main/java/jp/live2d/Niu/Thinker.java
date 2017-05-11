package jp.live2d.Niu;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.security.acl.LastOwnerException;
import java.util.HashMap;
import java.util.Map;

import jp.live2d.MyLive2D.LAppLive2DManager;

/**
 * Created by xkei4 on 2017/05/02.
 */

public class Thinker {
    private HashMap<String, String> brain = new HashMap<String, String>();
    private int nextTask = 0;
    private String keyWord = "";
    private String valueWord = "";
    private Emotion nowEmotion = Emotion.NORMAL;
    private String nowWord = "";
    private SharedPreferences prefs = null;

    //SharedPreferences pref = null;

    public Thinker(SharedPreferences prefs){

        this.prefs = prefs;
        		// 読み出し
		Gson gson = new Gson();
		brain = gson.fromJson(prefs.getString("niuBrain",""), new TypeToken<HashMap<String, String>>(){}.getType());
		if(brain!=null) {
			for(Map.Entry e : brain.entrySet()) {
				Log.d("OKOK","キー : " + e.getKey() + "  バリュー : " + e.getValue());
			}
		} else {
            brain = new HashMap<String, String>();
            //初期設定
            brain.put("にう", "おまえもにうか");
            brain.put("はい", "首を縦に振るだけの人生か");
            brain.put("いいえ", "上司に歯向かうのかクビだ");
            brain.put("others", "にう８っちゃいだからわかんなーい");
            brain.put("たのしー", "たのしー");
            brain.put("うれしー", "うれしー");
            brain.put("かなしー", "かなしー");
        }



    }


    public void will(String word) {

        //学習フェーズかチェック
        if(nextTask == 2) {
            keyWord = word;
            nextTask--;
            nowWord = "どう返せばいい？";
        } else if(nextTask == 1) {
            valueWord = word;
            nextTask--;
            learn(keyWord, valueWord);
            nowWord = "わかった。\n「"+keyWord+"」のとき「"+valueWord+"」って返すね。";
        }
        //ユーザが「がくしゅう」と入力すると学習フェーズスタート
        else if(word.equals("がくしゅう")) {
            nextTask = 2;
            nowWord = "がくしゅーしゅる\nなんて言葉？";
        }
        else {
            nextTask = 0;
            nowWord = getReply(word);
        }

        //Emotionの決定
        if(word.equals("たのしー")) {
            nowEmotion = Emotion.FUNNY;
        }
        else if(word.equals("うれしー")) {
            nowEmotion = Emotion.HAPPY;
        }
        else if(word.equals("かなしー")) {
            nowEmotion = Emotion.SAD;
        }
        else {
            nowEmotion = Emotion.NORMAL;
        }



    }

    public Emotion getEmotion() {
        // TODO: 感情を推定
        return nowEmotion;
    }

    public String getWord() {
        return nowWord;
    }

    public String getReply(String word) {
        return thinkBase(word);
    }

    public void learn(String key, String value) {
        brain.put(key, value);

		// 保存

		Gson gson = new Gson();
		prefs.edit().putString("niuBrain", gson.toJson(brain)).commit();

        //Gson gson = new Gson();
        //gson.toJson(brain);
        //pref.edit().putString("brain", gson.toJson(brain)).commit();
    }

    public String thinkBase(String word) {
        if(brain.get(word) != null)
            return brain.get(word);
        else
            return "わからないよ～(´；ω；｀)ﾌﾞﾜｯ";
    }

    private void thinkPersonality() {

    }

}
