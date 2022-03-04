package com.example.simplebank;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplebank.mainpage.recyclerListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<User> userArrayList = new ArrayList<>();
        userArrayList.add(new User("ahmad", "$", 123457.5f));
        userArrayList.add(new User("ahmad", "$", 123457.5f));
        userArrayList.add(new User("ahmad", "$", 123457.5f));
        userArrayList.add(new User("ahmad", "$", 123457.5f));
        userArrayList.add(new User("ahmad", "$", 123457.5f));
        userArrayList.add(new User("ahmad", "$", 123457.5f));

        Log.d("mytag", "us " + userArrayList.get(0).getUsername());
        RecyclerView recyclerView = findViewById(R.id.main_user_list_recycler);
        recyclerListAdapter adapter = new recyclerListAdapter(this, userArrayList);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        /*
                        RecyclerView recyclerView = findViewById(R.id.main_recycler);
                //
                viewAdapter adapter = new viewAdapter(mangaArrayList, context);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
         */
    }
}