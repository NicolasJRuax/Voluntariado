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
        holder.location.setText(opportunity.getCity() + ", " + opportunity.getCountry());
        holder.description.setText(opportunity.getDescription());

        // Manejar la expansión de los detalles
        holder.itemView.setOnClickListener(v -> {
            if (holder.description.getVisibility() == View.GONE) {
                holder.description.setVisibility(View.VISIBLE);
            } else {
                holder.description.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return opportunities.size();
    }

    /**
     * Actualiza la lista de datos y notifica al adaptador.
     */
    public void updateData(List<VolunteerOpportunity> newOpportunities) {
        this.opportunities = newOpportunities;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, location, description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.volunteer_name);
            location = itemView.findViewById(R.id.volunteer_location);
            description = itemView.findViewById(R.id.volunteer_description);
        }
    }
}
