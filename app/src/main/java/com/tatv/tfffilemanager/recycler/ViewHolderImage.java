package com.tatv.tfffilemanager.recycler;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.core.content.ContextCompat;
import androidx.palette.graphics.Palette;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.tatv.tfffilemanager.R;
import com.tatv.tfffilemanager.utils.FileUtils;
import com.tatv.tfffilemanager.utils.PreferenceUtils;

import java.io.File;

import static com.tatv.tfffilemanager.utils.FileUtils.getColorResource;
import static com.tatv.tfffilemanager.utils.FileUtils.getName;

final class ViewHolderImage extends ViewHolder {

    private TextView name;

    private TextView date;

    ViewHolderImage(Context context, OnItemClickListener listener, View view) {
        super(context, listener, view);
    }

    @Override
    protected void loadIcon() {

        image = (ImageView) itemView.findViewById(R.id.list_item_image);
    }

    @Override
    protected void loadName() {

        name = (TextView) itemView.findViewById(R.id.list_item_name);
    }

    @Override
    protected void loadInfo() {

        date = (TextView) itemView.findViewById(R.id.list_item_date);
    }

    @Override
    protected void bindIcon(File file, Boolean selected) {

        final int color = ContextCompat.getColor(context, getColorResource(file));
        Glide.with(context).asBitmap().load(file).fitCenter().into(new BitmapImageViewTarget(image));
    }

    @Override
    protected void bindName(File file) {

        boolean extension = PreferenceUtils.getBoolean(context, "pref_extension", true);

        name.setText(extension ? getName(file) : file.getName());
    }

    @Override
    protected void bindInfo(File file) {

        if (date == null) return;

        date.setText(FileUtils.getLastModified(file));
    }
}