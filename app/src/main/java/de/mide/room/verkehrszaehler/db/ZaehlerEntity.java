package de.mide.room.verkehrszaehler.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity für Tabelle mit Zählern. Eine Zeile in dieser Tabelle repräsentiert eine
 * zu zählende Fahrzeugart.
 */
@Entity
public class ZaehlerEntity {

    /** Eindeutiger Primärschlüssel. */
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int zaehlerid;

    /** Name des Zählers, also Fahrzeugart, z.B. "PKW" oder "LKW". */
    @NonNull
    public String zaehlerName;

    /** Aktuelle Anzahl gezählter Fahrzeuge. */
    public int zaehlerWert;
}
