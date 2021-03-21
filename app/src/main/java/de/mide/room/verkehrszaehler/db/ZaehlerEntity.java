package de.mide.room.verkehrszaehler.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity für Tabelle mit Zählern. Eine Zeile in dieser Tabelle repräsentiert eine
 * zu zählende Fahrzeugart.
 */
@Entity
public class ZaehlerEntity {

    /**
     * Eindeutiger Schlüssel eines Zähler, wird automatisch befüllt; Spaltenname <code>_id</code>
     * gewählt, weil die für die Listendarstellung verwendete Klasse <code>CursorAdapter</code>
     * dies so erwartet.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    @NonNull
    public int id;

    /** Name des Zählers, also Fahrzeugart wie "PKW" oder "LKW". */
    @NonNull
    @ColumnInfo(name = "zaehler_name")
    public String zaehlerName;

    /** Aktuelle Anzahl gezählter Fahrzeuge. */
    @ColumnInfo(name = "zaehler_wert", defaultValue = "0")
    public int zaehlerWert;

}
