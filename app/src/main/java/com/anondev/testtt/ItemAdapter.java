package com.anondev.testtt;

import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anondev.testtt.databinding.SubItemBinding;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.SubViewHolder> {
    public List<String> list = new ArrayList<>();
    public int width = 0;

    @NonNull
    @Override
    public SubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubViewHolder(SubItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SubViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SubViewHolder extends RecyclerView.ViewHolder {
        private SubItemBinding binding;

        public SubViewHolder(@NonNull SubItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(int pos) {
            binding.tvSub.setText(list.get(pos));
            Log.d("test123", binding.getRoot().getWidth() + "");
            ObjectAnimator animator = ObjectAnimator.ofFloat(binding.getRoot(), "translationX", width - 200, 0f);
            animator.setDuration(500);
            //animator.setInterpolator(new OvershootInterpolator());
            animator.start();
        }
    }
}
