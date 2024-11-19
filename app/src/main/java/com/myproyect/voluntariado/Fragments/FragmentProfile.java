package com.myproyect.voluntariado.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.myproyect.voluntariado.R;

public class FragmentProfile extends Fragment {

    private static final String PREFS_NAME = "ProfilePrefs";
    private static final String KEY_NAME = "name";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_SKILLS = "skills";
    private static final String KEY_INTERESTS = "interests";

    private TextView profileName, profileLocation, profileSkills, profileInterests;
    private LinearLayout formEditProfile;
    private EditText editName, editLocation, editSkills, editInterests;
    private Button btnEditProfile, btnSaveProfile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Inicializar vistas
        profileName = view.findViewById(R.id.profile_name);
        profileLocation = view.findViewById(R.id.profile_location);
        profileSkills = view.findViewById(R.id.profile_skills);
        profileInterests = view.findViewById(R.id.profile_interests);

        formEditProfile = view.findViewById(R.id.form_edit_profile);
        editName = view.findViewById(R.id.edit_name);
        editLocation = view.findViewById(R.id.edit_location);
        editSkills = view.findViewById(R.id.edit_skills);
        editInterests = view.findViewById(R.id.edit_interests);

        btnEditProfile = view.findViewById(R.id.btn_edit_profile);
        btnSaveProfile = view.findViewById(R.id.btn_save_profile);

        // Cargar datos guardados
        loadProfileData();

        // Configurar el botón de edición
        btnEditProfile.setOnClickListener(v -> {
            formEditProfile.setVisibility(View.VISIBLE);
            btnEditProfile.setVisibility(View.GONE);
        });

        // Configurar el botón de guardar
        btnSaveProfile.setOnClickListener(v -> saveProfileData());

        return view;
    }

    /**
     * Carga los datos guardados en SharedPreferences y actualiza las vistas.
     */
    private void loadProfileData() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME, "Nombre Apellido");
        String location = sharedPreferences.getString(KEY_LOCATION, "Ciudad, País");
        String skills = sharedPreferences.getString(KEY_SKILLS, "Habilidades: Programación, Diseño");
        String interests = sharedPreferences.getString(KEY_INTERESTS, "Intereses: Medio ambiente, Educación");

        profileName.setText(name);
        profileLocation.setText(location);
        profileSkills.setText("Habilidades: " + skills);
        profileInterests.setText("Intereses: " + interests);
    }

    /**
     * Guarda los datos del perfil en SharedPreferences y actualiza las vistas.
     */
    private void saveProfileData() {
        String name = editName.getText().toString();
        String location = editLocation.getText().toString();
        String skills = editSkills.getText().toString();
        String interests = editInterests.getText().toString();

        // Validar entradas
        if (name.isEmpty() || location.isEmpty()) {
            return;
        }

        // Guardar datos en SharedPreferences
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_LOCATION, location);
        editor.putString(KEY_SKILLS, skills);
        editor.putString(KEY_INTERESTS, interests);
        editor.apply();

        // Actualizar vistas
        profileName.setText(name);
        profileLocation.setText(location);
        profileSkills.setText("Habilidades: " + skills);
        profileInterests.setText("Intereses: " + interests);

        // Ocultar el formulario
        formEditProfile.setVisibility(View.GONE);
        btnEditProfile.setVisibility(View.VISIBLE);
    }
}
