package com.example.casitapartyrentals.mainModule.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.casitapartyrentals.R;
import com.example.casitapartyrentals.common.pojo.Mueble;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import butterknife.BindView;

public class MuebleAdapter extends RecyclerView.Adapter<MuebleAdapter.ViewHolder>{
    private List<Mueble> muebles;
    private OnItemClickListener listener;
    private Context context;

    public MuebleAdapter(List<Mueble> muebles, OnItemClickListener listener) {
        this.muebles = muebles;
        this.listener = listener;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_image)
        ImageView card_image;
        @BindView(R.id.card_title)
        TextView card_title;
        @BindView(R.id.card_price)
        TextView card_price;
        @BindView(R.id.card_description)
        TextView card_description;
        @BindView(R.id.card_button)
        Button card_button;
        @BindView(R.id.til_quantity)
        TextInputLayout til_quantity;
        @BindView(R.id.ed_quantity)
        TextInputEditText ed_quantity;
        private View view;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
        }

        void setOnClickListener(Mueble mueble, OnItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //listener.OnItemClick(mueble);
                }
            });
        }
    }
    public void setUpMuebles(Mueble mueble){
        muebles.add(mueble);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mueble_item,parent,false);
        context=parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Mueble mueble= muebles.get(position);
        holder.setOnClickListener(mueble,listener);
        holder.card_title.setText(mueble.getName().toString());
        holder.card_description.setText(mueble.getDescription().toString());
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop();
        Glide.with(context)
                .load(mueble.getPhotoURL())
                .apply(options)
                .into(holder.card_image);
    }
    @Override
    public int getItemCount() {
        return muebles.size();
    }
}
