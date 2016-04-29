package com.material.katha.cookdaycook;

/**
 * Created by rajiv on 3/3/2016.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class OneFragment extends Fragment {
    RecyclerView recyclerview;
    Context context;
    int previouscount=0;
    public static final String FIREBASE_URL = "https://cookdaycook.firebaseIO.com/";
    RecipeDetailAdapter adapter;
    List<Detail> data = new ArrayList<>();

    public OneFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_one, container, false);
        // Inflate the layout for this fragment
        recyclerview = (RecyclerView) v.findViewById(R.id.rv);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecipeDetailAdapter(getActivity());
        recyclerview.setAdapter(adapter);
        Firebase ref = new Firebase(FIREBASE_URL);
        Query queryRef = ref.orderByValue();
        final Detail detail = new Detail();
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {
                //Log.d("check", "The " + snapshot.getKey() + " dinosaur's score is " + snapshot.getValue());
                if((snapshot.child("meal").getValue()).equals("Breakfast") && snapshot.getChildrenCount()!=previouscount) {
                    detail.price = (String) snapshot.child("price").getValue();
                    detail.name = (String) snapshot.child("name").getValue();
                    Log.d("check", (String) snapshot.child("price").getValue());
                    detail.image = Base64.decode((String) snapshot.child("image").getValue(), Base64.DEFAULT);
                    data.add(detail);
                    adapter.setList(data);
                    previouscount++;
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

            // ....
        });

        return v;
    }

}