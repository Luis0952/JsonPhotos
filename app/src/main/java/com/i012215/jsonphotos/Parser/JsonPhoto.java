package com.i012215.jsonphotos.Parser;

import android.provider.ContactsContract;

import com.i012215.jsonphotos.Models.Photo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luis Eduardo on 8/10/2017.
 */

public class JsonPhoto {
    public static List<Photo> getData(String content) throws JSONException {
        JSONArray jsonArray = new JSONArray(content);
        List<Photo> photoList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject item = jsonArray.getJSONObject(i);
            Photo photo = new Photo();
            photo.setTitle(item.getString("title"));
            photo.setUrl(item.getString("url"));
            photoList.add(photo);
        }
        return photoList;
    }
}