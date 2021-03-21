package de.mide.room.verkehrszaehler.db;

import android.database.Cursor;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;

/**
 * Interface aus dem das DAO erzeugt wird (DAO: Data Access Object), siehe auch die Doku
 * <a href="https://developer.android.com/training/data-storage/room/accessing-data">hier</a>.
 * DAO enthält Methoden für CRUD-Operationen auf (einer) DB-Tabelle(n); einige Methoden
 * sind deshalb mit SQL-Statements in Form von Annotationen versehen.
 * <br><br>
 *
 * This file is licensed under the terms of the BSD 3-Clause License.
 */
@Dao
public interface VerkehrszaehlerDao {

    /**
     * Neuer Verkehrszähler in Tabelle einfügen.
     *
     * @param neuerZaehler  Neuer Zähler für bestimmte Fahrzeugart, z.B. "Fahrräder".
     */
    @Insert
    public void insertVerkehrzaehler(ZaehlerEntity neuerZaehler);

    /**
     * Query für Listendarstellung; liefert ein {@link Cursor}-Objekt zurück.
     *
     * @return  Cursor-Objekt, das die Liste aller Zähler repräsentiert.
     *          Einträge sind aufsteigend nach Zählername sortiert.
     */
    @Query("SELECT * FROM ZaehlerEntity ORDER BY zaehler_name ASC")
    public Cursor getCursorFuerListe();

    /**
     * Zähler für bestimmte Fahrzeug um +1 erhöhen.
     *
     * @param zaehlerName  Name des Zählers, der erhöht werden soll.
     *
     * @return Anzahl der Zeilen, die in der Tabelle geändert wurden; sollte immer <code>1</code> sein.
     */
    @Query("UPDATE ZaehlerEntity SET zaehler_wert = zaehler_wert+1 WHERE zaehler_name like :zaehlerName")
    public int inkrementZaehler(String zaehlerName);

}
