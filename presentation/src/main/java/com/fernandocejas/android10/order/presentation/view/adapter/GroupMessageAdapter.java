package com.fernandocejas.android10.order.presentation.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.fernandocejas.android10.R;
import com.fernandocejas.android10.order.presentation.model.GroupMessageModel;
import com.fernandocejas.android10.order.presentation.view.GroupMessageAdapterView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 *
 */

public class GroupMessageAdapter extends RecyclerView.Adapter<GroupMessageAdapter.GroupMessageViewHolder> implements GroupMessageAdapterView {

    public interface OnItemClickListener {
        void onItemClicked(GroupMessageModel groupMessageModel);
    }

    private List<GroupMessageModel> groupMessageModels;
    private LayoutInflater layoutInflater;

    private final Context context;

    private OnItemClickListener onItemClickListener;

    @Inject
    GroupMessageAdapter(Context context) {
        this.context = context;
        this.groupMessageModels = Collections.emptyList();
    }

    @Override
    public int getItemCount() {
        return (this.groupMessageModels != null) ? this.groupMessageModels.size() : 0;
    }

    @Override
    public GroupMessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (this.layoutInflater == null) {
            this.layoutInflater =
                    (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        final View view = this.layoutInflater.inflate(R.layout.row_group_message, parent, false);
        return new GroupMessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GroupMessageViewHolder holder, final int position) {
        final GroupMessageModel messageModel = this.groupMessageModels.get(position);
        //show avatar
        showAvatarInView(holder, messageModel.getAvatarBuyer());
        //show buyer name
        showBuyerNameInView(holder, messageModel.getNameBuyer());
        //show order id
        showOrderIdInView(holder, messageModel.getOrderId());
        //show time of last message
        showLastMessageTimeInView(holder, messageModel.getTimeLastMessage());
        //show content of last message
        showLastMessageContentInView(holder, messageModel.getLastMessage());
        //action events
        holder.itemView.setOnClickListener(v -> {
            if (GroupMessageAdapter.this.onItemClickListener != null) {
                GroupMessageAdapter.this.onItemClickListener.onItemClicked(messageModel);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setGroupMessageModels(Collection<GroupMessageModel> groupMessageModels) {
        this.validateCollection(groupMessageModels);
        this.groupMessageModels = (List<GroupMessageModel>) groupMessageModels;
        this.notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    private void validateCollection(Collection<GroupMessageModel> groupMessageModels) {
        if (groupMessageModels == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    @Override
    public void showAvatarInView(GroupMessageViewHolder viewHolder, String url) {
        loadImageFromUrl(context, viewHolder.imv_avatar, url, true, true);
    }

    @Override
    public void showLastMessageTimeInView(GroupMessageViewHolder viewHolder, String text) {
        viewHolder.tv_time_last_message.setText(text);
    }

    @Override
    public void showBuyerNameInView(GroupMessageViewHolder viewHolder, String text) {
        viewHolder.tv_buyer.setText(text);
    }

    @Override
    public void showOrderIdInView(GroupMessageViewHolder viewHolder, String text) {
        viewHolder.tv_order_id.setText(text);
    }

    @Override
    public void showLastMessageContentInView(GroupMessageViewHolder viewHolder, String text) {
        viewHolder.tv_last_message.setText(text);
    }

    private void loadImageFromUrl(Context context, ImageView view, String url, boolean isCircle, boolean hasDefault) {
        if (url == null || url.isEmpty()) {
            if (hasDefault) {
                //show default avatar if we don't have url to show
                Glide.with(context)
                        .load(R.drawable.default_avatar)
                        .asBitmap()
                        .into(new BitmapImageViewTarget(view) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                RoundedBitmapDrawable circularBitmapDrawable =
                                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                                circularBitmapDrawable.setCircular(true);
                                view.setImageDrawable(circularBitmapDrawable);
                            }
                        });
            }
            return;
        }
        if (isCircle) {
            Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .into(new BitmapImageViewTarget(view) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            view.setImageDrawable(circularBitmapDrawable);
                        }
                    });
        } else {
            Glide.with(context)
                    .load(url)
                    .into(view);
        }
    }

    public static class GroupMessageViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.imv_avatar)
        ImageView imv_avatar;
        @Bind(R.id.tv_time_last_message)
        TextView tv_time_last_message;
        @Bind(R.id.tv_buyer)
        TextView tv_buyer;
        @Bind(R.id.tv_order_id)
        TextView tv_order_id;
        @Bind(R.id.tv_last_message)
        TextView tv_last_message;

        GroupMessageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
