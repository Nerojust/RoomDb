package com.company.appintegration.roomDB;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.company.appintegration.R;

import java.util.ArrayList;

public class EmployeeAdapter extends ListAdapter<EmployeeModel, EmployeeAdapter.ViewHolder> {

    // creating a variable for on item click listener.
    private OnItemClickListener listener;
    private ArrayList<EmployeeModel> myEmployeeModel;

    // creating a constructor class for our adapter class.
    public EmployeeAdapter(ArrayList<EmployeeModel> myEmployeeModel, Context context) {
        super(DIFF_CALLBACK);
        this.myEmployeeModel = myEmployeeModel;
    }

    // creating a call back for item of recycler view.
    private static final DiffUtil.ItemCallback<EmployeeModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<EmployeeModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull EmployeeModel oldItem, @NonNull EmployeeModel newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull EmployeeModel oldItem, @NonNull EmployeeModel newItem) {
            // below line is to check the employee full name, job description and year of entry.
            return oldItem.getEmployeeFullNames().equals(newItem.getEmployeeFullNames()) &&
                    oldItem.getJobDescription().equals(newItem.getJobDescription()) &&
                    oldItem.getYearOfEntry().equals(newItem.getYearOfEntry());
        }
    };

    @NonNull
    @Override
    public EmployeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myLayoutInflater = LayoutInflater.from(parent.getContext());
        View myView = myLayoutInflater.inflate(R.layout.employee_rv_item, parent, false);
        return new ViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.ViewHolder holder, int position) {
        EmployeeModel myModel = getEmployeeAt(position);
        holder.employeeFullName.setText(myModel.getEmployeeFullNames());
        holder.jobDescription.setText(myModel.getJobDescription());
        holder.yearOfEntry.setText(myModel.getYearOfEntry());

    }

    // method for filtering our recyclerview items.
    public void filterList(ArrayList<EmployeeModel> filterlist) {
        // below line is to add our filtered
        // list in our course array list.
        myEmployeeModel = filterlist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    // creating a method to get course modal for a specific position.
    public EmployeeModel getEmployeeAt(int position) {
        return getItem(position);
    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        if(myEmployeeModel == null)
            return 0;
        return myEmployeeModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // view holder class to create a variable for each view.
        TextView employeeFullName, jobDescription, yearOfEntry;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            employeeFullName = itemView.findViewById(R.id.tv_employee_full_name);
            jobDescription = itemView.findViewById(R.id.tv_job_description);
            yearOfEntry = itemView.findViewById(R.id.tv_year_of_entry);

            // adding on click listener for each item of recycler view.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // inside on click listener we are passing
                    // position to our item of recycler view.
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(EmployeeModel model);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
