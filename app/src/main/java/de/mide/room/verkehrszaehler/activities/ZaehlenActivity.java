package de.mide.room.verkehrszaehler.activities;

import de.mide.room.verkehrszaehler.R;
import de.mide.room.verkehrszaehler.db.MeineDatenbank;
import de.mide.room.verkehrszaehler.db.VerkehrszaehlerDao;
import de.mide.room.verkehrszaehler.helfer.MeinCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

/**
 * Activity, um eigentliche Zählung durchzuführen, also einzelne Zähler auf Button-Betätigung
 * hin zu erhöhen. Hierzu werden alle Zähler ein einer Liste angezeigt.
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

        setTitle( R.string.activity_title_zaehlen );

        MeineDatenbank     datenbank = MeineDatenbank.getSingletonInstance(this);
        VerkehrszaehlerDao dao       = datenbank.verkehrszaehlerDao();
        Cursor             cursor    = dao.getCursorFuerListe();

        MeinCursorAdapter cursorAdapter = new MeinCursorAdapter(this, cursor, 0); // flags=0

        ListView listView = findViewById(R.id.zaehlerListView);
        listView.setAdapter(cursorAdapter);
    }

    /**
     * Event-Handler für Button um zurück zur Hauptseite zu kommen.
     *
     * @param view  Button, der Event ausgelöst hat.
     */
    public void onButtonZurueckMainActivity(View view) {

        finish();
    }

}