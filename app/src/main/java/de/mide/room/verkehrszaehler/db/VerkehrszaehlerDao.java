package de.mide.room.verkehrszaehler.db;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;

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

}
