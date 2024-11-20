package com.myproyect.voluntariado.Voluntario;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myproyect.voluntariado.R;

import java.util.List;

public class VolunteerAdapter extends RecyclerView.Adapter<VolunteerAdapter.ViewHolder> {

    private List<VolunteerOpportunity> opportunities;
    private OnOpportunityClickListener clickListener;
    private List<String> userSkills = List.of("Trabajo en equipo", "Interés ambiental"); // Simula habilidades del usuario

    public VolunteerAdapter(List<VolunteerOpportunity> opportunities, OnOpportunityClickListener clickListener) {
        this.opportunities = opportunities;
        this.clickListener = clickListener;
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

        // Configura los textos
        holder.name.setText(opportunity.getName());
        holder.location.setText(String.format("%s, %s", opportunity.getCity(), opportunity.getCountry()));
        holder.description.setText(opportunity.getDescription());
        holder.schedule.setText(String.format("Horario: %s", opportunity.getSchedule()));

        // Resalta habilidades del usuario que coincidan
        StringBuilder skills = new StringBuilder("Habilidades: ");
        for (String skill : opportunity.getSkills()) {
            if (userSkills.contains(skill)) {
                skills.append("<b><font color='#FF0000'>").append(skill).append("</font></b>, ");
            } else {
                skills.append(skill).append(", ");
            }
        }
        // Elimina la última coma y espacio
        if (skills.length() > 2) {
            skills.setLength(skills.length() - 2);
        }
        holder.skills.setText(android.text.Html.fromHtml(skills.toString()));

        // Maneja clics en el botón de registro
        holder.registerButton.setOnClickListener(v -> {
            // Muestra mensaje de registro al usuario
            Toast.makeText(v.getContext(), "Registrado en " + opportunity.getName(), Toast.LENGTH_SHORT).show();

            // Logea el registro en Logcat
            System.out.println("Usuario registrado en: " + opportunity.getName());
        });

        // Maneja clics en el elemento de la lista
        holder.itemView.setOnClickListener(v -> clickListener.onOpportunityClick(opportunity));
    }

    @Override
    public int getItemCount() {
        return opportunities.size();
    }

    public void updateData(List<VolunteerOpportunity> newOpportunities) {
        this.opportunities = newOpportunities;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, location, description, schedule, skills;
        Button registerButton; // Botón para registrarse

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.volunteer_name);
            location = itemView.findViewById(R.id.volunteer_location);
            description = itemView.findViewById(R.id.volunteer_description);
            schedule = itemView.findViewById(R.id.volunteer_schedule);
            skills = itemView.findViewById(R.id.volunteer_skills);
            registerButton = itemView.findViewById(R.id.register_button); // Referencia al botón
        }
    }

    public interface OnOpportunityClickListener {
        void onOpportunityClick(VolunteerOpportunity opportunity);
    }
}
