/*
 * This file is generated by jOOQ.
 */
package kpi.hlibsukhoruchkin.kursova.jooq.tables.records;


import kpi.hlibsukhoruchkin.kursova.jooq.tables.Favourites;

import org.jooq.Record2;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.ULong;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FavouritesRecord extends UpdatableRecordImpl<FavouritesRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>kursova.favourites.UserID</code>.
     */
    public void setUserid(ULong value) {
        set(0, value);
    }

    /**
     * Getter for <code>kursova.favourites.UserID</code>.
     */
    public ULong getUserid() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>kursova.favourites.ShowID</code>.
     */
    public void setShowid(ULong value) {
        set(1, value);
    }

    /**
     * Getter for <code>kursova.favourites.ShowID</code>.
     */
    public ULong getShowid() {
        return (ULong) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<ULong, ULong> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached FavouritesRecord
     */
    public FavouritesRecord() {
        super(Favourites.FAVOURITES);
    }

    /**
     * Create a detached, initialised FavouritesRecord
     */
    public FavouritesRecord(ULong userid, ULong showid) {
        super(Favourites.FAVOURITES);

        setUserid(userid);
        setShowid(showid);
        resetChangedOnNotNull();
    }
}
