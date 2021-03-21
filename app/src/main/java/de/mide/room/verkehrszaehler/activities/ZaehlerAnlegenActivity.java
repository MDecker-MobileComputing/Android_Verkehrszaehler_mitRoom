package de.mide.room.verkehrszaehler.activities;

import de.mide.room.verkehrszaehler.R;
import de.mide.room.verkehrszaehler.helfer.DialogHelfer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Activity, um Zähler für verschiedene Fahrzeugarten anzulegen.
 * <br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class ZaehlerAnlegenActivity extends AppCompatActivity {

    /** Eingabefeld für Namen mit anzulegendem Zähler. */
    private EditText _neuerZaehlerEditText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zaehler_anlegen);

        _neuerZaehlerEditText = findViewById(R.id.neuerZaehlerName);
    }

    /**
     * Event-Handler für Button "Zähler anlegen"
     *
     * @param view  Button, der Event ausgelöst hat.
     */
    public void onAnlegenButton(View view) {

        String zaehlerName = _neuerZaehlerEditText.getText().toString().trim();

        if (zaehlerName.length() == 0) {

            String titel     = getString(R.string.dialog_titel_fehler);
            String nachricht = getString(R.string.dialog_zaehlername_leer);

            DialogHelfer.zeigeDialog(this, titel, nachricht );
            return;
        }

    }

    /**
     * Event-Handler für Button "Zurück zum Hauptmenü".
     *
     * @param view  Button, der Event ausgelöst hat.
     */
    public void onZurueck(View view) {

        finish();
    }

}