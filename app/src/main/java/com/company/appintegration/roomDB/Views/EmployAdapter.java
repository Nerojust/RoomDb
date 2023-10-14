package com.company.appintegration.roomDB.Views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.company.appintegration.R;
import com.company.appintegration.roomDB.EmployeeModel;

import java.util.List;

public class EmployAdapter extends RecyclerView.Adapter<EmployAdapter.ViewHolder> {

    private List<EmployeeModel> myEmployeeModel;

    @NonNull
    @Override
    public EmployAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myLayoutInflater = LayoutInflater.from(parent.getContext());
        View myView = myLayoutInflater.inflate(R.layout.employee_rv_item, parent, false);
        return new ViewHolder(myView);

    }

    @Override
    public void onBindViewHolder(@NonNull EmployAdapter.ViewHolder holder, int position) {
        EmployeeModel myModel = myEmployeeModel.get(position);
        holder.employeeFullName.setText(myModel.getEmployeeFullNames());
        holder.jobDescription.setText(myModel.getJobDescription());
        holder.yearOfEntry.setText(myModel.getYearOfEntry());
    }

    @Override
    public int getItemCount() {
        if (myEmployeeModel == null)
            return 0;
        return myEmployeeModel.size();
    }

    public void setMyEmployeeModel(List<EmployeeModel> myEmployeeModel) {
        this.myEmployeeModel = myEmployeeModel;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView employeeFullName, jobDescription, yearOfEntry;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            employeeFullName = itemView.findViewById(R.id.tv_employee_full_name);
            jobDescription = itemView.findViewById(R.id.tv_job_description);
            yearOfEntry = itemView.findViewById(R.id.tv_year_of_entry);
        }
    }
}
