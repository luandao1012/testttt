package com.anondev.testtt;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.anondev.testtt.databinding.ItemBinding;
import com.anondev.testtt.databinding.LayoutDialogBinding;

import java.util.ArrayList;
import java.util.List;

public class MyWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MyWidgetFactory(this.getApplicationContext(), intent);
    }

    class MyWidgetFactory implements RemoteViewsFactory {
        private List<String> list = new ArrayList<>();
        Context mContext;

        public MyWidgetFactory(Context mContext, Intent intent) {
            this.mContext = mContext;
        }

        @Override
        public void onCreate() {
            initData();
        }

        @Override
        public void onDataSetChanged() {
            initData();
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            // ItemBinding itemBinding = ItemBinding.inflate(LayoutInflater.from(mContext), null, false);
            RemoteViews view = new RemoteViews(mContext.getPackageName(), R.layout.item);
            view.setTextViewText(R.id.tv, list.get(position));
            return view;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        private void initData() {
            list.clear();
            for (int i = 1; i <= 15; i++) {
                list.add("ListView item " + i);
            }

        }
    }
}
