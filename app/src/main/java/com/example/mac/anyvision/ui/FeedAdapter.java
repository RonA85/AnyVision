package com.example.mac.anyvision.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mac.anyvision.R;
import com.example.mac.anyvision.model.FeedItem;

import java.util.List;

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
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        final FeedItem item = items.get(position);
        holder.title.setText(item.getTitle());
        holder.date.setText(item.getDate());
        holder.desc.setText(item.getDescription());
//        Glide.with(context)
//                .load(item.getImageFeed().getImage())
//                .asBitmap()
//                .fitCenter()
//               // .placeholder(R.drawable.movie_icon_96)
//                .into(holder.image);

        holder.card.setOnClickListener(new View.OnClickListener() {
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
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public CardView card;
        public TextView title,date,desc;
        public ImageView image;

        public ViewHolder(View v) {
            super(v);
            card =  v.findViewById(R.id.card_feed);
            title = v.findViewById(R.id.tv_title);
            date = v.findViewById(R.id.tv_date);
            desc = v.findViewById(R.id.tv_desc);
            image = v.findViewById(R.id.iv_image);

        }


    }
}
