package com.example.simplebank.mainpage;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplebank.R;
import com.example.simplebank.User;

import java.util.ArrayList;

public class recyclerListAdapter extends RecyclerView.Adapter<recyclerListAdapter.ViewHolder> {

    //data
    private Context context;
    private ArrayList<User> localDataSet;

    public recyclerListAdapter(Context context, ArrayList<User> localDataSet) {
        this.context = context;
        this.localDataSet = localDataSet;
    }

    @NonNull
    @Override
    public recyclerListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("mytag", "onBindViewHolder: called.");
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerListAdapter.ViewHolder holder, int position) {
        holder.name.setText(localDataSet.get(position).getUsername());
        holder.email.setText(localDataSet.get(position).getEmail());
        holder.balance.setText(String.valueOf(localDataSet.get(position).getCurrentBalance()));

        holder.parentLayout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "the " + localDataSet.get(holder.getAdapterPosition()).getUsername(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView email;
        TextView balance;

        TableLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.user_list_item_name);
            email = (TextView) itemView.findViewById(R.id.user_list_item_email);
            balance = (TextView) itemView.findViewById(R.id.user_list_item_balance);

            parentLayout = (TableLayout) itemView.findViewById(R.id.user_list_item_layout);
        }

    }
}
