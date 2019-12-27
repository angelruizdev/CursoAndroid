package com.example.angelruiz.cursoandroid.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angelruiz.cursoandroid.Arrays.ArrayCommentsRxApiRest;
import com.example.angelruiz.cursoandroid.Arrays.ArrayPostsRxApiRest;
import com.example.angelruiz.cursoandroid.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterRxPostComentApiRest extends RecyclerView.Adapter<AdapterRxPostComentApiRest.ViewHolderPostCommentApiRest> {

    //arrayList for save the data of tha api rest(response)
    private List<ArrayPostsRxApiRest> arrayPostsRxApiRest = new ArrayList<>();
    private List<ArrayCommentsRxApiRest> arrayCommentsRxApiRest = new ArrayList<>();

    //builder
    public AdapterRxPostComentApiRest(){

    }

    //geter and seter
    public List<ArrayPostsRxApiRest> getArrayPostsRxApiRest() {
        return arrayPostsRxApiRest;
    }

    public void setArrayPostsRxApiRest(List<ArrayPostsRxApiRest> arrayPostsRxApiRest) {
        this.arrayPostsRxApiRest = arrayPostsRxApiRest;
        notifyDataSetChanged();
    }

    public List<ArrayCommentsRxApiRest> getArrayCommentsRxApiRest() {
        return arrayCommentsRxApiRest;
    }

    public void setArrayCommentsRxApiRest(List<ArrayCommentsRxApiRest> arrayCommentsRxApiRest) {
        this.arrayCommentsRxApiRest = arrayCommentsRxApiRest;
        notifyDataSetChanged();
    }

    //update the posts already transformed
    public void updatePosts(ArrayPostsRxApiRest post){
        arrayPostsRxApiRest.set(arrayPostsRxApiRest.indexOf(post), post);
        notifyItemChanged(arrayPostsRxApiRest.indexOf(post));
    }

    //inflate the view to the rv(items)
    @NonNull
    @Override
    public ViewHolderPostCommentApiRest onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_rv_rxjava_apirest, parent, false);
        return new ViewHolderPostCommentApiRest(view);
    }

    //create the views(components) of the inflate view
    public class ViewHolderPostCommentApiRest extends RecyclerView.ViewHolder {
        TextView tvShowPosts, tvNumComments;
        ProgressBar pbLoadComments;

        public ViewHolderPostCommentApiRest(@NonNull View itemView) {
            super(itemView);
            tvShowPosts = itemView.findViewById(R.id.tv_show_posts);
            tvNumComments = itemView.findViewById(R.id.tv_num_coments);
            pbLoadComments = itemView.findViewById(R.id.pb_load_comments);
        }
    }

    //show the data of the service api rest through the arrayList
    @Override
    public void onBindViewHolder(@NonNull ViewHolderPostCommentApiRest holder, int position) {

        if (arrayPostsRxApiRest != null) {
            holder.tvShowPosts.setText(arrayPostsRxApiRest.get(position).getTitle());
            //holder.tvNumComments.setText(String.valueOf(arrayCommentsRxApiRest.get(position).getId()));

        }else if(3 > 5){
            //holder.tvNumComments.setVisibility(View.GONE);
            holder.pbLoadComments.setVisibility(View.VISIBLE);
        }else {
            holder.pbLoadComments.setVisibility(View.GONE);
            //holder.tvNumComments.setText(String.valueOf());
        }
    }

    //return the size of the arrayList posts
    @Override
    public int getItemCount() {
        return arrayPostsRxApiRest.size();
    }
}
