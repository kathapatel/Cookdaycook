package com.material.katha.cookdaycook;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;

import com.firebase.client.Firebase;

import java.io.ByteArrayOutputStream;

/**
 * Created by rajiv on 4/27/2016.
 */
public class DataEntry {
    public static final String FIREBASE_URL = "https://cookdaycook.firebaseIO.com/";
    public static Context context;
    private int[] ImagesArrayList = {
            R.drawable.slogo
    };

    public DataEntry(MainActivity mainActivity) {
        context = mainActivity;
    }

    public byte[] add_data() {
        Firebase.setAndroidContext(context);
        Firebase ref = new Firebase(FIREBASE_URL);
        ref.child("recipe").child("name").setValue("Greek Salad");
        ref.child("recipe").child("Description").setValue("Greek salad is a salad in Greek cuisine. Greek salad is made with pieces of tomatoes, sliced cucumbers, onion, feta cheese, and olives, typically seasoned with salt and oregano, and dressed with olive oil.");
        ref.child("recipe").child("price").setValue("120");
        ref.child("recipe").child("serve").setValue("2");
        ref.child("recipe").child("pro").setValue("3g");
        ref.child("recipe").child("carbs").setValue("20g");
        ref.child("recipe").child("fat").setValue("5g");
        ref.child("recipe").child("fib").setValue("16g");
        ref.child("recipe").child("rate").setValue("3.5");
        ref.child("recipe").child("category").setValue("Ready to Cook");
        ref.child("recipe").child("meal").setValue("Breakfast");
        ref.child("recipe").child("comment").child("name").setValue("Katha Patel");
        ref.child("recipe").child("comment").child("comment").setValue("Great");
        ref.child("recipe").child("comment").child("rate").setValue("5");
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.salad);//your image
        ByteArrayOutputStream bYtE = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, bYtE);
        bmp.recycle();
        byte[] byteArray = bYtE.toByteArray();
        String imageFile = Base64.encodeToString(byteArray, Base64.DEFAULT);
        ref.child("recipe").child("image").setValue(imageFile);
        return Base64.decode(imageFile, Base64.DEFAULT);
    }
}
