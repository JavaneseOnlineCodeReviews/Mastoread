package house.winkleak.mastoreader.data.managers;


import android.content.SharedPreferences;

import house.winkleak.mastoreader.util.ConstantManager;
import house.winkleak.mastoreader.util.MastoReadApplication;

public class OwnPreferenceManager {

    private SharedPreferences mSharedPreferences;

    OwnPreferenceManager(){
        this.mSharedPreferences = MastoReadApplication.getSharedPreferences();
    }
    //сохраняем запрос введенный пользователем в SearchView из StatusListActivity
    public void saveSearchTag(String searchTag){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(ConstantManager.SEARCH_TAG_KEY, searchTag);
        editor.apply();
    }
    //Получаем запрос введенный пользователем в SearchView из StatusListActivity
    public String getSearchTag(){
        return mSharedPreferences.getString(ConstantManager.SEARCH_TAG_KEY, "");
    }
}
