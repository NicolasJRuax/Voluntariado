package com.myproyect.voluntariado.Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

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
    private SearchView searchView;
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

        // Inicializa las listas
        opportunities = getVolunteerOpportunities();
        filteredOpportunities = new ArrayList<>(opportunities);

        // Configura el adaptador
        adapter = new VolunteerAdapter(filteredOpportunities);
        recyclerView.setAdapter(adapter);

        // Configura SearchView
        searchView = view.findViewById(R.id.search_view);
        if (searchView == null) {
            System.out.println("Error: SearchView no se encontró en el diseño.");
        } else {
            setupSearchView();
        }

        // Configura ubicación fija para pruebas
        simulateLocation();

        return view;
    }

    /**
     * Simula la ubicación del usuario para pruebas.
     */
    private void simulateLocation() {
        Location simulatedLocation = new Location("dummyprovider");
        simulatedLocation.setLatitude(40.4167); // Latitud de Madrid
        simulatedLocation.setLongitude(-3.7033); // Longitud de Madrid
        System.out.println("Simulando ubicación: Latitud = " + simulatedLocation.getLatitude() + ", Longitud = " + simulatedLocation.getLongitude());
        sortOpportunitiesByDistance(simulatedLocation);
    }

    /**
     * Configura el SearchView para filtrar los resultados.
     */
    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterOpportunities(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterOpportunities(newText);
                return false;
            }
        });
    }

    /**
     * Filtra las oportunidades según el texto de búsqueda.
     */
    private void filterOpportunities(String query) {
        System.out.println("Filtrando oportunidades con query: " + query);
        filteredOpportunities.clear();
        for (VolunteerOpportunity opportunity : opportunities) {
            if (opportunity.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredOpportunities.add(opportunity);
            }
        }
        System.out.println("Resultados del filtro: " + filteredOpportunities.size() + " elementos encontrados.");
        adapter.updateData(filteredOpportunities); // Actualiza el adaptador con la lista filtrada
    }

    /**
     * Devuelve una lista de oportunidades de voluntariado.
     */
    private List<VolunteerOpportunity> getVolunteerOpportunities() {
        List<VolunteerOpportunity> opportunities = new ArrayList<>();
        opportunities.add(new VolunteerOpportunity("Reforestación", "Plantación de árboles en áreas deforestadas.", "México", "Ciudad de México", 19.4326, -99.1332));
        opportunities.add(new VolunteerOpportunity("Clases para niños", "Enseñanza básica en comunidades rurales.", "Argentina", "Buenos Aires", -34.6037, -58.3816));
        opportunities.add(new VolunteerOpportunity("Limpieza de playas", "Recogida de basura en playas locales.", "España", "Barcelona", 41.3879, 2.16992));
        opportunities.add(new VolunteerOpportunity("Centro comunitario", "Ayuda en actividades comunitarias.", "Colombia", "Bogotá", 4.7110, -74.0721));
        opportunities.add(new VolunteerOpportunity("Rescate animal", "Cuidado de animales rescatados.", "Costa Rica", "San José", 9.9281, -84.0907));
        opportunities.add(new VolunteerOpportunity("Educación ambiental", "Promoción de prácticas sostenibles.", "Chile", "Santiago", -33.4489, -70.6693));
        opportunities.add(new VolunteerOpportunity("Clases de inglés", "Enseñanza del idioma inglés a niños.", "Perú", "Lima", -12.0464, -77.0428));
        opportunities.add(new VolunteerOpportunity("Rehabilitación de parques", "Mantenimiento de áreas verdes.", "Estados Unidos", "San Francisco", 37.7749, -122.4194));
        opportunities.add(new VolunteerOpportunity("Ayuda en refugios", "Soporte en refugios para personas sin hogar.", "Reino Unido", "Londres", 51.5074, -0.1278));
        opportunities.add(new VolunteerOpportunity("Restauración histórica", "Reparación de sitios históricos.", "Italia", "Roma", 41.9028, 12.4964));
        opportunities.add(new VolunteerOpportunity("Conservación marina", "Protección de ecosistemas marinos.", "Australia", "Sídney", -33.8688, 151.2093));
        opportunities.add(new VolunteerOpportunity("Reforestación", "Plantación de árboles en áreas afectadas por incendios.", "Brasil", "Manaos", -3.1019, -60.0250));
        opportunities.add(new VolunteerOpportunity("Asistencia en orfanatos", "Ayuda en actividades diarias.", "India", "Nueva Delhi", 28.6139, 77.2090));
        opportunities.add(new VolunteerOpportunity("Educación en tecnología", "Capacitación en habilidades digitales.", "Canadá", "Toronto", 43.651070, -79.347015));
        opportunities.add(new VolunteerOpportunity("Programas de salud", "Asistencia médica en comunidades rurales.", "Sudáfrica", "Ciudad del Cabo", -33.9249, 18.4241));
        opportunities.add(new VolunteerOpportunity("Limpieza de ríos", "Eliminación de desechos de ríos.", "Alemania", "Berlín", 52.5200, 13.4050));
        opportunities.add(new VolunteerOpportunity("Conservación de bosques", "Protección de flora y fauna.", "Indonesia", "Yakarta", -6.2088, 106.8456));
        opportunities.add(new VolunteerOpportunity("Reparación de escuelas", "Mantenimiento y mejoras en escuelas rurales.", "Filipinas", "Manila", 14.5995, 120.9842));
        opportunities.add(new VolunteerOpportunity("Apoyo en festivales culturales", "Organización y soporte en eventos culturales.", "Japón", "Kioto", 35.0116, 135.7681));
        return opportunities;
    }

    /**
     * Ordena las oportunidades por proximidad a la ubicación del usuario.
     */
    private void sortOpportunitiesByDistance(Location userLocation) {
        if (opportunities == null || opportunities.isEmpty()) {
            System.out.println("No hay oportunidades para ordenar.");
            return;
        }

        System.out.println("Ordenando oportunidades por proximidad...");
        opportunities.sort((o1, o2) -> {
            float[] results1 = new float[1];
            float[] results2 = new float[1];
            Location.distanceBetween(userLocation.getLatitude(), userLocation.getLongitude(), o1.getLatitude(), o1.getLongitude(), results1);
            Location.distanceBetween(userLocation.getLatitude(), userLocation.getLongitude(), o2.getLatitude(), o2.getLongitude(), results2);

            System.out.println("Distancia de " + o1.getName() + ": " + results1[0] + " metros");
            System.out.println("Distancia de " + o2.getName() + ": " + results2[0] + " metros");

            return Float.compare(results1[0], results2[0]);
        });

        // Actualiza filteredOpportunities para reflejar los cambios
        filteredOpportunities.clear();
        filteredOpportunities.addAll(opportunities);

        System.out.println("Oportunidades ordenadas: " + filteredOpportunities);
        // Notifica al adaptador
        adapter.updateData(filteredOpportunities);
    }
}
