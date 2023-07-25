package com.anondev.testtt;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anondev.testtt.databinding.ItemBinding;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ItemViewHolder> {
    public List<Item> list;
    public ItemAdapter itemAdapter;
    private boolean isShow = false;

    public void setData(List<Item> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemViewHolder itemViewHolder = new ItemViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        itemViewHolder.bindListeners();
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private ItemBinding binding;

        public ItemViewHolder(@NonNull ItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(int pos) {
            binding.tv.setText(list.get(pos).text);
            ObjectAnimator scaleAnimator = ObjectAnimator.ofPropertyValuesHolder(
                    binding.getRoot(),
                    PropertyValuesHolder.ofFloat(View.SCALE_X, 0f, 1f),
                    PropertyValuesHolder.ofFloat(View.SCALE_Y, 0f, 1f)
            );
            scaleAnimator.setDuration(500);
            scaleAnimator.start();

            ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(
                    binding.getRoot(),
                    View.ALPHA,
                    0f,
                    1f
            );
            alphaAnimator.setDuration(500);
            alphaAnimator.start();
        }

        public void bindListeners() {
            binding.iv.setOnClickListener(view -> {
                if (!isShow) {
                    ObjectAnimator animator = ObjectAnimator.ofFloat(binding.iv, "rotation", 0f, 180f);
                    animator.setDuration(500);
                    // animator.setInterpolator(new AccelerateDecelerateInterpolator());
                    animator.start();
                    binding.recycler.setVisibility(View.VISIBLE);
                    itemAdapter = new ItemAdapter();
                    itemAdapter.width = binding.getRoot().getWidth();
                    itemAdapter.list = list.get(getAdapterPosition()).getList();
                    binding.recycler.setAdapter(itemAdapter);
                    isShow = true;
                } else {
                    ObjectAnimator animator = ObjectAnimator.ofFloat(binding.iv, "rotation", 180f, 0f);
                    animator.setDuration(500);
                    animator.setInterpolator(new AccelerateDecelerateInterpolator());
                    animator.start();
                    binding.recycler.setVisibility(View.GONE);
                    itemAdapter = new ItemAdapter();
                    itemAdapter.list = list.get(getAdapterPosition()).getList();
                    binding.recycler.setAdapter(itemAdapter);
                    isShow = false;
                }
            });
        }

        public View getView() {
            return binding.foreground;
        }
    }
}
