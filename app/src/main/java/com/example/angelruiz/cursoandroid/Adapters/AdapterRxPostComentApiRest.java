package com.example.angelruiz.cursoandroid.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angelruiz.cursoandroid.R;

public class AdapterRxPostComentApiRest extends RecyclerView.Adapter<AdapterRxPostComentApiRest.ViewHolderPostCommentApiRest> {

    private AdapterRxPostComentApiRest(){

    }

    @NonNull
    @Override
    public ViewHolderPostCommentApiRest onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_rx_java_api_rest, parent, false);
        return new ViewHolderPostCommentApiRest(view);
    }

    public class ViewHolderPostCommentApiRest extends RecyclerView.ViewHolder {
        private TextView tvShowPosts, tvNumComments;
        private ProgressBar pbLoadComments;

        public ViewHolderPostCommentApiRest(@NonNull View itemView) {
            super(itemView);
            tvShowPosts = itemView.findViewById(R.id.tv_show_posts);
            tvNumComments = itemView.findViewById(R.id.tv_num_coments);
            pbLoadComments = itemView.findViewById(R.id.pb_load_comments);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPostCommentApiRest holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
