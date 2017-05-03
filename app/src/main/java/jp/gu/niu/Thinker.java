package jp.gu.niu;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xkei4 on 2017/05/02.
 */

public class Thinker {
    String word = "";
    Map<String, String> brain = new HashMap<>();
    int nextTask = 0;
    String keyWord = "";
    String valueWord = "";

    //SharedPreferences pref = null;

    public Thinker(){
        //this.pref = pref;

        //初期設定
        brain.put("にう", "おまえもにうか");
        brain.put("はい", "首を縦に振るだけの人生か");
        brain.put("いいえ", "上司に歯向かうのかクビだ");
        brain.put("others", "にう８っちゃいだからわかんなーい");

    }

    public String will(String word) {

        //学習フェーズかチェック
        if(nextTask == 2) {
            keyWord = word;
            nextTask--;
            return "どう返せばいい？";
        } else if(nextTask == 1) {
            valueWord = word;
            nextTask--;
            learn(keyWord, valueWord);
            return "わかった。\n「"+keyWord+"」のとき「"+valueWord+"」って返すね。";
        }

        //ユーザが「がくしゅう」と入力すると学習フェーズスタート
        if(word.equals("がくしゅう")) {
            nextTask = 2;
            return "がくしゅーしゅる\nなんて言葉？";
        }
        else {
            nextTask = 0;
            return getReply(word);
        }

    }

    public String getReply(String word) {
        return thinkBase(word);
    }

    public void learn(String key, String value) {
        brain.put(key, value);
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
