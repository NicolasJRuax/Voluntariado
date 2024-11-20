package com.myproyect.voluntariado.Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.recyclerview.widget.DividerItemDecoration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myproyect.voluntariado.R;
import com.myproyect.voluntariado.Voluntario.VolunteerAdapter;
import com.myproyect.voluntariado.Voluntario.VolunteerOpportunity;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearch extends Fragment {

    private RecyclerView recyclerView;
    private VolunteerAdapter adapter;
    private List<VolunteerOpportunity> opportunities;
    private List<VolunteerOpportunity> filteredOpportunities;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // Inicializa RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(divider);
        // Inicializa las listas
        opportunities = getVolunteerOpportunities();
        filteredOpportunities = new ArrayList<>(opportunities);

        // Configura el adaptador
        adapter = new VolunteerAdapter(filteredOpportunities, this::onOpportunityClick);
        recyclerView.setAdapter(adapter);

        // Simula la ubicación para pruebas
        simulateLocation();

        return view;
    }

    /**
     * Maneja el clic en una oportunidad para mostrar más detalles.
     */
    private void onOpportunityClick(VolunteerOpportunity opportunity) {
        // Simula abrir un fragmento o mostrar un diálogo con más detalles
        Toast.makeText(getContext(), "Detalles de: " + opportunity.getName(), Toast.LENGTH_SHORT).show();
        // Aquí puedes abrir un nuevo fragmento o diálogo con más detalles
    }

    /**
     * Simula la ubicación del usuario para pruebas.
     */
    private void simulateLocation() {
        Location simulatedLocation = new Location("dummyprovider");
        simulatedLocation.setLatitude(40.4167); // Latitud de Madrid
        simulatedLocation.setLongitude(-3.7033); // Longitud de Madrid
        sortOpportunitiesByDistance(simulatedLocation);
    }

    /**
     * Devuelve una lista de oportunidades de voluntariado.
     */
    /**
     * Devuelve una lista de oportunidades de voluntariado con detalles adicionales.
     */
    private List<VolunteerOpportunity> getVolunteerOpportunities() {
        List<VolunteerOpportunity> opportunities = new ArrayList<>();
        opportunities.add(new VolunteerOpportunity(
                "Reforestación en Madrid",
                "Plantación de árboles en áreas deforestadas.",
                "España",
                "Madrid",
                40.4167,
                -3.7033,
                "8:00 AM - 1:00 PM",
                List.of("Trabajo en equipo", "Interés ambiental")));
        opportunities.add(new VolunteerOpportunity(
                "Clases para niños",
                "Enseñanza básica en comunidades rurales.",
                "Argentina",
                "Buenos Aires",
                -34.6037,
                -58.3816,
                "10:00 AM - 2:00 PM",
                List.of("Paciencia", "Habilidad para enseñar")));
        opportunities.add(new VolunteerOpportunity(
                "Limpieza de playas",
                "Recogida de basura en playas locales.",
                "España",
                "Barcelona",
                41.3879,
                2.16992,
                "9:00 AM - 12:00 PM",
                List.of("Trabajo físico", "Conservación")));
        opportunities.add(new VolunteerOpportunity(
                "Centro comunitario",
                "Ayuda en actividades comunitarias.",
                "Colombia",
                "Bogotá",
                4.7110,
                -74.0721,
                "3:00 PM - 6:00 PM",
                List.of("Habilidades interpersonales", "Organización")));
        opportunities.add(new VolunteerOpportunity(
                "Educación ambiental",
                "Promoción de prácticas sostenibles.",
                "Chile",
                "Santiago",
                -33.4489,
                -70.6693,
                "8:00 AM - 11:00 AM",
                List.of("Conocimientos ambientales", "Habilidades de comunicación")));
        opportunities.add(new VolunteerOpportunity(
                "Asistencia en orfanatos",
                "Ayuda en actividades diarias en orfanatos.",
                "India",
                "Nueva Delhi",
                28.6139,
                77.2090,
                "10:00 AM - 4:00 PM",
                List.of("Empatía", "Trabajo en equipo")));
        opportunities.add(new VolunteerOpportunity(
                "Restauración histórica",
                "Reparación de sitios históricos.",
                "Italia",
                "Roma",
                41.9028,
                12.4964,
                "7:00 AM - 12:00 PM",
                List.of("Atención al detalle", "Historia del arte")));
        return opportunities;
    }


    /**
     * Ordena las oportunidades por proximidad a la ubicación del usuario.
     */
    private void sortOpportunitiesByDistance(Location userLocation) {
        if (opportunities == null || opportunities.isEmpty()) {
            return;
        }
        opportunities.sort((o1, o2) -> {
            float[] results1 = new float[1];
            float[] results2 = new float[1];
            Location.distanceBetween(userLocation.getLatitude(), userLocation.getLongitude(), o1.getLatitude(), o1.getLongitude(), results1);
            Location.distanceBetween(userLocation.getLatitude(), userLocation.getLongitude(), o2.getLatitude(), o2.getLongitude(), results2);
            return Float.compare(results1[0], results2[0]);
        });

        filteredOpportunities.clear();
        filteredOpportunities.addAll(opportunities);
        adapter.updateData(filteredOpportunities);
    }
}
