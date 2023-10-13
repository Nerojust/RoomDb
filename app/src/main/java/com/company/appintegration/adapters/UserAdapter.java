package com.company.appintegration.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.company.appintegration.R;
import com.company.appintegration.models.Result;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

      Context myContext;
    private List<Result> myBillerList;

    public UserAdapter(Context myContext, List<Result> myResult) {
        this.myContext = myContext;
        this.myBillerList = myResult;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myLayoutInflater = LayoutInflater.from(parent.getContext());
        View myView = myLayoutInflater.inflate(R.layout.user_layout, parent, false);
        return new ViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        Result myResultModel = myBillerList.get(position);

        holder.id.setText("" + myResultModel.getId());
        holder.name.setText(myResultModel.getName());
        holder.categoryId.setText("" + myResultModel.getCategoryId());

    }

    @Override
    public int getItemCount() {
        if(myBillerList == null) return 0;
        return myBillerList.size();
    }

    public void setResults(List<Result> myResult){
        this.myBillerList = myResult;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView id, name, categoryId;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.tv_id);
            name = itemView.findViewById(R.id.tv_name);
            categoryId = itemView.findViewById(R.id.tv_category_id);

        }
    }
}
