package de.mide.room.verkehrszaehler.activities;

import de.mide.room.verkehrszaehler.R;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * Activity, um eigentliche Zählung durchzuführen, also einzelne Zähler auf Button-Betätigung
 * hin zu erhöhen.
 * <br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class ZaehlenActivity extends AppCompatActivity {

    /**
     * Lifecycle-Methode, initialisiert Activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zaehlen);
    }

}