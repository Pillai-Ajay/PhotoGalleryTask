package com.example.photogallerytask;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class PhotoGalleryAdapter extends RecyclerView.Adapter<PhotoGalleryAdapter.MyViewholder>{

    Context context;
    ArrayList<String> imgUrl;

    public PhotoGalleryAdapter(ArrayList<String> imgUrl, Context context) {
        this.context = context;
        this.imgUrl = imgUrl;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_photo_gallery,parent,false);
        MyViewholder myViewholder = new MyViewholder(view);
        return myViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, final int position) {
        Glide.with(this.context)
                .load(imgUrl.get(position))
                .apply(new RequestOptions()
                .placeholder(ContextCompat.getDrawable(this.context, R.drawable.noimage)))
//                .apply(RequestOptions.placeholderOf(R.drawable.noimage))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivDisplayPhotos)
                ;

        holder.ivDisplayPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle bundle=new Bundle();
//                bundle.putInt("position",position);
//                ShowimageFragment fragment=new ShowimageFragment();
//                fragment.setArguments(bundle);
//                String tag = "my_fragment";
//                FragmentManager fragmentManager = ((BaseActivity) context).getSupportFragmentManager();
//
//                if(fragmentManager.findFragmentByTag(tag) == null) {
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.frame_container, fragment);
//                    fragmentTransaction.addToBackStack(null);
//                    fragmentTransaction.commit();
//                }


                Intent gridActivity = new Intent(context, ShowimageFragment.class);
                gridActivity.putExtra("position", position);
                context.startActivity(gridActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imgUrl.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder{
        ImageView ivDisplayPhotos;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            ivDisplayPhotos = (ImageView)itemView.findViewById(R.id.ivDisplayImage);
        }
    }
}
