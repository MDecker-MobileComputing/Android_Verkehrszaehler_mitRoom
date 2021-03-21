package de.mide.room.verkehrszaehler.helfer;

import android.view.View.OnClickListener;
import de.mide.room.verkehrszaehler.R;
import de.mide.room.verkehrszaehler.db.MeineDatenbank;
import de.mide.room.verkehrszaehler.db.VerkehrszaehlerDao;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Adapter-Objekt zur Verbindung zwischen Cursor-Objekt mit allen Zählern von der Datenbank
 * und Liste zur Darstellung.
 *
 * Siehe
 * <a href="https://coderwall.com/p/fmavhg/android-cursoradapter-with-custom-layout-and-how-to-use-it">hier</a>
 * für ein Beispiel zur Verwendung der Klasse {@link CursorAdapter}.
 * <br><br>
 *
 * This file is licensed under the terms of the BSD 3-Clause License.
 */
public class MeinCursorAdapter extends CursorAdapter {

    /** Wird benötigt, um Layout für einzelne Zeile "aufzublasen". */
    private LayoutInflater _inflater = null;

    /** Index der Spalte mit Name des Zählers; wird für Zugriff auf Cursor-Objekt benötigt. */
    private int _columnIndexZaehlerName = -1;

    /** Index der Spalte mit Wert des Zählers; wird für Zugriff auf Cursor-Objekt benötigt. */
    private int _columnIndexZaehlerWert = -1;

    /** Data Access Object (DAO) um Zähler in DB-Tabelle zu erhöhen. */
    private VerkehrszaehlerDao _dao = null;

    /**
     * Konstruktor, übergibt Argumente an Super-Konstruktor und holt {@link LayoutInflater}.
     *
     * @param context  Referenz auf Activity.
     *
     * @param cursor  Cursor, der darzustellende Datensätze repräsentiert.
     *
     * @param flags  Attribute, um Verhalten von Adapter zu steuern.
     */
    public MeinCursorAdapter(Context context, Cursor cursor, int flags) {

        super(context, cursor, flags);

        _columnIndexZaehlerName = cursor.getColumnIndex("zaehler_name");
        _columnIndexZaehlerWert = cursor.getColumnIndex("zaehler_wert");

        _inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

        MeineDatenbank datenbank = MeineDatenbank.getSingletonInstance(context);
        _dao = datenbank.verkehrszaehlerDao();
    }

    /**
     * Erzeugt View für eine Zeile durch "Aufblasen" des entsprechenden Layouts.
     *
     * @param context  Wird nicht benötigt.
     *
     * @param cursor  DB-Cursor, wird nicht benötigt.
     *
     * @param viewGroup  Parent-Layout.
     *
     * @return  Neues View für einen Listen-Eintrag ("Zeile").
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        return _inflater.inflate(R.layout.listview_zaehler, viewGroup, false); // attachToRoot=false
    }

    /**
     * Trägt Werte aus aktuellem Datensatz von {@code cursor} in {@code view} ein.
     *
     * @param view  View-Element für Listen-"Zeile", in das der aktuelle Wert von {@code cursor}
     *              einzutragen ist.
     *
     * @param context  Wird nicht benötigt.
     *
     * @param cursor  Cursor, aus dem ein Datensatz im UI-Element dargestellt werden soll.
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        String zaehlerName = cursor.getString( _columnIndexZaehlerName );
        int    zaehlerWert = cursor.getInt(    _columnIndexZaehlerWert );

        TextView nameTextView = view.findViewById(R.id.zaehlerName);
        TextView wertTextView = view.findViewById(R.id.zaehlerWert);

        nameTextView.setText(zaehlerName     );
        wertTextView.setText(zaehlerWert + "");

        Button plusEinsButton = view.findViewById(R.id.plusEinsButton);
        plusEinsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                _dao.inkrementZaehler(zaehlerName);

                // Zum Refreshen der Liste ( https://stackoverflow.com/a/35765328 )
                Cursor newCursor = _dao.getCursorFuerListe();
                changeCursor(newCursor);
            }
        });
    }

}
