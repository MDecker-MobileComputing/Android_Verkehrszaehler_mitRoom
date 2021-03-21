package de.mide.room.verkehrszaehler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import de.mide.room.verkehrszaehler.activities.ZaehlenActivity;
import de.mide.room.verkehrszaehler.activities.ZaehlerAnlegenActivity;

/**
 * Haupt-Activity der Verkehrszähler-App, die Room für die Persistenz verwendet.
 * <br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Event-Handler für Button "Fahrzeuge zählen".
     *
     * @param view  Button, der das Event ausgelöst hat.
     */
    public void onButtonZaehlen(View view) {

        Intent intent = new Intent(this, ZaehlenActivity.class);
        startActivity(intent);
    }

    /**
     * Event-Handler für Button "Zähler anlegen".
     *
     * @param view  Button, der das Event ausgelöst hat.
     */
    public void onButtonZaehlerAnlegen(View view) {

        Intent intent = new Intent(this, ZaehlerAnlegenActivity.class);
        startActivity(intent);
    }

    /**
     * Event-Handler für Button "Zähler löschen".
     *
     * @param view  Button, der das Event ausgelöst hat.
     */
    public void onButtonZaehlerLoeschen(View view) {

    }

}