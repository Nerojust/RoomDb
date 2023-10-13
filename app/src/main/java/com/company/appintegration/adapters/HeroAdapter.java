package com.company.appintegration.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.company.appintegration.models.GameModel;
import com.company.appintegration.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.ViewHolder> {

    Context myContext;
    private List<GameModel> myGameModel;

    public HeroAdapter(Context myContext, List<GameModel> myGameModel) {
        this.myContext = myContext;
        this.myGameModel = myGameModel;
    }

    public void setHeros(List<GameModel> myGameModel){
        this.myGameModel = myGameModel;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HeroAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myInflater = LayoutInflater.from(parent.getContext());
        View myView  = myInflater.inflate(R.layout.game_layout, parent, false);
        return new ViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroAdapter.ViewHolder holder, int position) {

        GameModel myGameModelList = myGameModel.get(position);
        holder.name.setText(myGameModelList.getName());
        holder.realName.setText(myGameModelList.getRealname());
        holder.firstAppearance.setText(myGameModelList.getFirstappearance());
        holder.createdBy.setText(myGameModelList.getCreatedby());
        Picasso.get().load(myGameModelList.getImageurl()).into(holder.picture);
    }

    @Override
    public int getItemCount() {
        return myGameModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name, realName, firstAppearance, createdBy;
        private CircleImageView picture;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            picture = itemView.findViewById(R.id.img_picture);
            name = itemView.findViewById(R.id.tv_name);
            realName  = itemView.findViewById(R.id.tv_real_name);
            firstAppearance= itemView.findViewById(R.id.tv_first_appearance);
            createdBy = itemView.findViewById(R.id.tv_created_by);
        }
    }
}
