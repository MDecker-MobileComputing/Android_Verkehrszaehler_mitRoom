package de.mide.room.verkehrszaehler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import de.mide.room.verkehrszaehler.activities.ZaehlenActivity;
import de.mide.room.verkehrszaehler.activities.ZaehlerAnlegenActivity;
import de.mide.room.verkehrszaehler.db.MeineDatenbank;
import de.mide.room.verkehrszaehler.helfer.DialogHelfer;

/**
 * Haupt-Activity der Verkehrszähler-App, die Room für die Persistenz verwendet.
 * <br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Referenz auf Instanz von Unterklasse von <code>RoomDatabase</code>;
     * wird nicht nur holen von DAO-Referenz benötigt, sondern auch für
     * Aufruf Methode <code>clearAllTables()</code> um alle Tabelleneinträge
     * zu löschen.
     */
    private MeineDatenbank _datenbank = null;

    /**
     * Lifecycle-Methode, initialisiert Activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _datenbank = MeineDatenbank.getSingletonInstance(this);
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
     * Event-Handler für Button "Alle Zähler löschen". Es wird zunächst eine Sicherheitsfrage
     * angezeigt; die Löschung wird nur bei der Bejahung dieser Frage durchgeführt.
     *
     * @param view  Button, der das Event ausgelöst hat.
     */
    public void onButtonZaehlerLoeschen(View view) {

        OnClickListener onJaButtonListener = new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                _datenbank.clearAllTables();

                String titel     = getString(R.string.dialog_titel_erfolg  );
                String nachricht = getString(R.string.dialog_alle_geloescht);
                DialogHelfer.zeigeDialog(MainActivity.this, titel, nachricht);
            }
        };


        String titel = getString(R.string.dialog_titel_sicherheitsfrage);
        String frage = getString(R.string.dialog_alle_loeschen_fragen  );

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle(   titel );
        dialogBuilder.setMessage( frage );

        String jaStr        = getString(R.string.button_ja_alle_loeschen);
        String abbrechenStr = getString(R.string.button_abbrechen       );

        dialogBuilder.setPositiveButton(jaStr       , onJaButtonListener);
        dialogBuilder.setNegativeButton(abbrechenStr, null      );

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

}