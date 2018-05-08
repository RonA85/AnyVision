package com.example.mac.anyvision.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mac.anyvision.R;
import com.example.mac.anyvision.model.FeedItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import at.blogc.android.views.ExpandableTextView;

/**
 *
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {
    private List<FeedItem> items;
    private Context context;

    public FeedAdapter(Context context, List<FeedItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final ViewGroup.LayoutParams params = holder.feedLayout.getLayoutParams();
        final FeedItem item = items.get(position);
        holder.title.setText(item.getTitle());
        holder.date.setText(String.format("Date: %s", item.getDate()));
        holder.desc.setText(Html.fromHtml(item.getDescription()));
        holder.desc.setMovementMethod(new ScrollingMovementMethod());
        // set animation duration via code, but preferable in your layout files by using the animation_duration attribute
        holder.desc.setAnimationDuration(750L);
        // set interpolators for both expanding and collapsing animations
        holder.desc.setInterpolator(new OvershootInterpolator());
        holder.buttonToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.buttonToggle.setText(holder.desc.isExpanded() ? R.string.expand : R.string.collapse);
                holder.desc.toggle();
                params.height = holder.desc.isExpanded() ? 400 : 1200;
                holder.feedLayout.setLayoutParams(params);
            }
        });

        Picasso.with(context)
                .load(item.getEnclosure().getImage())
                .into(holder.image);

        holder.feedLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = item.getLink();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout feedLayout;
        public TextView title, date;
        public ExpandableTextView desc;
        public Button buttonToggle;
        public ImageView image;

        public ViewHolder(View v) {
            super(v);
            feedLayout = v.findViewById(R.id.feed_layout);
            title = v.findViewById(R.id.tv_title);
            date = v.findViewById(R.id.tv_date);
            desc = v.findViewById(R.id.expandableTextView);
            buttonToggle = v.findViewById(R.id.button_toggle);
            image = v.findViewById(R.id.iv_image);

        }

    }
}
