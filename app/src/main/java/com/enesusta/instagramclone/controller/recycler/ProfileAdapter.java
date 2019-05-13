package com.enesusta.instagramclone.controller.recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.enesusta.instagramclone.R;
import com.enesusta.instagramclone.controller.annotations.Metadata;
import com.enesusta.instagramclone.controller.enums.Priority;
import com.enesusta.instagramclone.controller.enums.Type;
import com.enesusta.instagramclone.model.Upload;
import com.enesusta.instagramclone.model.User;
import com.squareup.picasso.Picasso;

import java.util.List;

import lombok.NoArgsConstructor;

/*

MIT License

Copyright (c) 2019 Enes Usta

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

 */

@Metadata(
        priority = Priority.HIGH,
        type = Type.CONTROLLER,
        author = "Enes Usta",
        lastModified = "13/05/2019"
)


@NoArgsConstructor
public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileAdapterHolder> {

    private List<Upload> uploads;
    private Context context;

    public ProfileAdapter(List<Upload> users, Context context) {
        super();
        this.uploads = users;
        this.context = context;
    }


    @NonNull
    @Override
    public ProfileAdapterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.profile_stream, viewGroup, false);
        return new ProfileAdapterHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapterHolder profileAdapterHolder, int i) {

        Upload upload = uploads.get(i);

        Picasso.get()
                .load(upload.getUploadImageUrl())
                .fit()
                .centerCrop()
                .into(profileAdapterHolder.imageView);


    }


    @Override
    public int getItemCount() {
        return uploads.size();
    }

    public class ProfileAdapterHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public ProfileAdapterHolder(@NonNull View itemView) {

            super(itemView);
            imageView = itemView.findViewById(R.id.profile_stream_content_photo);

        }

    }


}
