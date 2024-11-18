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

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
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

    private FusedLocationProviderClient locationProvider;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // Inicializar elementos del diseño
        recyclerView = view.findViewById(R.id.recycler_view);
        searchView = view.findViewById(R.id.search_view);

        // Configurar RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        opportunities = getVolunteerOpportunities();
        filteredOpportunities = new ArrayList<>(opportunities);
        adapter = new VolunteerAdapter(filteredOpportunities);
        recyclerView.setAdapter(adapter);

        // Configurar SearchView para filtrar por nombre
        setupSearchView();

        // Configurar API de ubicación
        locationProvider = LocationServices.getFusedLocationProviderClient(requireActivity());
        getUserLocation();

        return view;
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
        filteredOpportunities.clear();
        for (VolunteerOpportunity opportunity : opportunities) {
            if (opportunity.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredOpportunities.add(opportunity);
            }
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * Devuelve una lista de oportunidades de voluntariado.
     */
    private List<VolunteerOpportunity> getVolunteerOpportunities() {
        List<VolunteerOpportunity> opportunities = new ArrayList<>();
        opportunities.add(new VolunteerOpportunity("Reforestación", "Plantación de árboles en áreas deforestadas."));
        opportunities.add(new VolunteerOpportunity("Clases para niños", "Enseñanza básica en comunidades rurales."));
        opportunities.add(new VolunteerOpportunity("Limpieza de playas", "Recogida de basura en playas locales."));
        opportunities.add(new VolunteerOpportunity("Centro comunitario", "Ayuda en actividades comunitarias."));
        return opportunities;
    }

    /**
     * Obtiene la ubicación actual del usuario y muestra sugerencias de oportunidades cercanas.
     */
    private void getUserLocation() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }

        locationProvider.getLastLocation().addOnSuccessListener(requireActivity(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    // Aquí puedes implementar la lógica para filtrar oportunidades según la ubicación
                    Toast.makeText(getContext(), "Ubicación obtenida: " + location.getLatitude() + ", " + location.getLongitude(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "No se pudo obtener la ubicación.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getUserLocation();
        } else {
            Toast.makeText(getContext(), "Permiso de ubicación denegado.", Toast.LENGTH_SHORT).show();
        }
    }
}
