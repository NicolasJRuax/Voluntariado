package com.myproyect.voluntariado.Voluntario;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myproyect.voluntariado.R;
import com.myproyect.voluntariado.Voluntario.VolunteerOpportunity;

import java.util.List;

public class VolunteerAdapter extends RecyclerView.Adapter<VolunteerAdapter.ViewHolder> {

    private List<VolunteerOpportunity> opportunities;

    public VolunteerAdapter(List<VolunteerOpportunity> opportunities) {
        this.opportunities = opportunities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_volunteer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VolunteerOpportunity opportunity = opportunities.get(position);
        holder.name.setText(opportunity.getName());
        holder.description.setText(opportunity.getDescription());
    }

    @Override
    public int getItemCount() {
        return opportunities.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.volunteer_name);
            description = itemView.findViewById(R.id.volunteer_description);
        }
    }
}
