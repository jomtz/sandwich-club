package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String TAG = "JsonUtils";

    public static Sandwich parseSandwichJson(String json) {

    //Parse JSON data to a Sandwich object.
        try{
            JSONObject sandwichObject = new JSONObject(json);
            JSONObject name = sandwichObject.getJSONObject("name");

            String mainName = name.getString("mainName");

            List<String> alsoKnownAs;
            alsoKnownAs = new ArrayList<>();
            JSONArray alsoKnownAsArray = name.getJSONArray("alsoKnownAs");
            if (alsoKnownAsArray != null) {
                for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                    alsoKnownAs.add(alsoKnownAsArray.getString(i));
                }
            }

            String placeOfOrigin = sandwichObject.getString("placeOfOrigin");
            String description = sandwichObject.getString("description");
            String image = sandwichObject.getString("image");

            ArrayList<String> ingredients = new ArrayList<>();
            JSONArray ingredientsArray = sandwichObject.getJSONArray("ingredients");
            if (ingredientsArray != null) {
                for (int i = 0; i < ingredientsArray.length(); i++) {
                    ingredients.add(ingredientsArray.getString(i));
                }
            }

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
            
        }
        catch (JSONException e){
            Log.e(TAG, "parseJson: " + e.getMessage());
        }
        return null;
    }
}
