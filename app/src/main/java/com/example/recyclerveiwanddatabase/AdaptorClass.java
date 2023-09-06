package com.example.recyclerveiwanddatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recyclerveiwanddatabase.databinding.ViewItemsBinding;

import java.util.ArrayList;

public class AdaptorClass extends RecyclerView.Adapter<AdaptorClass.viewHolder> {
    Context  context;
    ArrayList<ModelClass> list;

    public AdaptorClass(Context context, ArrayList<ModelClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.binnding.textName.setText(list.get(position).getName());
        holder.binnding.textNumber.setText(list.get(position).getNumber());

        String imageLink=null;
        imageLink=String.valueOf(list.get(position).getImage());
        Glide.with(context).load(imageLink).into(holder.binnding.img);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ViewItemsBinding binnding;
        public viewHolder(@NonNull View itemView) {

            super(itemView);
            binnding=ViewItemsBinding.bind(itemView);
        }
    }
}
