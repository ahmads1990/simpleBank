package com.example.simplebank.mainpage;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplebank.R;
import com.example.simplebank.User;

import java.util.ArrayList;

public class listAdapter extends  RecyclerView.Adapter<listAdapter.ViewHolder> {

    //data
    private Context context;
    private ArrayList<User> localDataSet;

    public listAdapter(Context context, ArrayList<User> localDataSet) {
        this.context = context;
        this.localDataSet = localDataSet;
    }

    @NonNull
    @Override
    public listAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("mytag", "onBindViewHolder: called.");
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull listAdapter.ViewHolder holder, int position) {
/*
* Picasso.get().load(localDataSet.get(position).getImageUrl()).resize(256, 256).into(holder.imageView);
        holder.title.setText(localDataSet.get(position).getTitle());
        holder.publishYear.setText("Year: " + String.valueOf(localDataSet.get(position).getPublishYear()));
        holder.author.setText("Author: " + localDataSet.get(position).getAuthor());
        holder.artist.setText("Artist: " + localDataSet.get(position).getArtist());

        holder.parentlayout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, gallery.class);
                        intent.putExtra("manga", localDataSet.get(position));
                        context.startActivity(intent);
                    }
                }
        );*/
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        /*  //define views
        ImageView imageView;
        TextView title;
        TextView publishYear;
        TextView author;
        TextView artist;

        ConstraintLayout parentlayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.main_item_image);
            title = (TextView) itemView.findViewById(R.id.main_item_title);
            publishYear = (TextView) itemView.findViewById(R.id.main_item_publishYear);
            author = (TextView) itemView.findViewById(R.id.main_item_author);
            artist = (TextView) itemView.findViewById(R.id.main_item_artist);

            parentlayout = (ConstraintLayout) itemView.findViewById(R.id.item_layout);
        }

         */
    }
}
