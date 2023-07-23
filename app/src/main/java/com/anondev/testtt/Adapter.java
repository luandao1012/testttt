package com.anondev.testtt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anondev.testtt.databinding.ItemBinding;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ItemViewHolder> {
    public List<String> list;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        public ItemViewHolder(@NonNull ItemBinding binding) {
            super(binding.getRoot());
        }
    }
}
