package com.example.android.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(@NonNull Context context, @NonNull List<News> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        News current = getItem(position);

        TextView title = listItemView.findViewById(R.id.title);

        title.setText(String.valueOf(current.getMtitle()));

        TextView section = listItemView.findViewById(R.id.section);

        section.setText(current.getMsection());

        TextView date = listItemView.findViewById(R.id.date);

        date.setText(String.valueOf(new java.util.Date(current.getMdate())));


        return listItemView;
    }
}