package de.mide.room.verkehrszaehler.activities;

import de.mide.room.verkehrszaehler.R;
import de.mide.room.verkehrszaehler.db.MeineDatenbank;
import de.mide.room.verkehrszaehler.db.VerkehrszaehlerDao;
import de.mide.room.verkehrszaehler.db.ZaehlerEntity;
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

    /** DAO für DB-Operationen. */
    private VerkehrszaehlerDao _dao = null;

    /**
     * Lifecycle-Methode, initialisiert Activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zaehler_anlegen);

        _neuerZaehlerEditText = findViewById(R.id.neuerZaehlerName);

        MeineDatenbank meineDatenbank = MeineDatenbank.getSingletonInstance(this);
        _dao = meineDatenbank.verkehrszaehlerDao();
    }

    /**
     * Event-Handler für Button "Zähler anlegen". Es wird zuerst überprüft, ob ein Name
     * für den neuen Zähler eingegeben ist.
     *
     * @param view  Button, der Event ausgelöst hat.
     */
    public void onAnlegenButton(View view) {

        String zaehlerName = _neuerZaehlerEditText.getText().toString().trim();

        if (zaehlerName.length() == 0) {

            String titel     = getString(R.string.dialog_titel_fehler);
            String nachricht = getString(R.string.dialog_zaehlername_leer);

            DialogHelfer.zeigeDialog(this, titel, nachricht );

        } else {

            ZaehlerEntity zaehlerEntity = new ZaehlerEntity();
            zaehlerEntity.zaehlerName = zaehlerName;
            zaehlerEntity.zaehlerWert = 0;
            _dao.insertVerkehrzaehler(zaehlerEntity);
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