package com.myproyect.voluntariado.Widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.myproyect.voluntariado.R;
import com.myproyect.voluntariado.Voluntario.VolunteerOpportunity;

import java.util.ArrayList;
import java.util.List;

public class VolunteerWidget extends AppWidgetProvider {

    // Simulación de oportunidades para el widget
    private List<VolunteerOpportunity> getTopOpportunities() {
        List<VolunteerOpportunity> opportunities = new ArrayList<>();
        opportunities.add(new VolunteerOpportunity("Reforestación", "Plantación de árboles", "España", "Madrid", 40.4167, -3.7033, "8:00 AM - 1:00 PM", List.of("Trabajo en equipo")));
        opportunities.add(new VolunteerOpportunity("Clases para niños", "Enseñanza básica", "Argentina", "Buenos Aires", -34.6037, -58.3816, "10:00 AM - 2:00 PM", List.of("Paciencia")));
        opportunities.add(new VolunteerOpportunity("Limpieza de playas", "Recogida de basura", "España", "Barcelona", 41.3879, 2.16992, "9:00 AM - 12:00 PM", List.of("Conservación")));
        return opportunities;
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        List<VolunteerOpportunity> topOpportunities = getTopOpportunities();

        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

            // Configura el contenido del widget
            StringBuilder widgetContent = new StringBuilder();
            for (VolunteerOpportunity opportunity : topOpportunities) {
                widgetContent.append(opportunity.getName()).append(" - ").append(opportunity.getCity()).append("\n");
            }

            views.setTextViewText(R.id.widget_content, widgetContent.toString().trim());

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}
