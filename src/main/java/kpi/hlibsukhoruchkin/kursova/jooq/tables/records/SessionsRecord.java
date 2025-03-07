/*
 * This file is generated by jOOQ.
 */
package kpi.hlibsukhoruchkin.kursova.jooq.tables.records;


import java.time.LocalDateTime;

import kpi.hlibsukhoruchkin.kursova.jooq.tables.Sessions;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.ULong;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SessionsRecord extends UpdatableRecordImpl<SessionsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>kursova.sessions.UserID</code>.
     */
    public void setUserid(ULong value) {
        set(0, value);
    }

    /**
     * Getter for <code>kursova.sessions.UserID</code>.
     */
    public ULong getUserid() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>kursova.sessions.Token</code>.
     */
    public void setToken(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>kursova.sessions.Token</code>.
     */
    public String getToken() {
        return (String) get(1);
    }

    /**
     * Setter for <code>kursova.sessions.ExpireDate</code>.
     */
    public void setExpiredate(LocalDateTime value) {
        set(2, value);
    }

    /**
     * Getter for <code>kursova.sessions.ExpireDate</code>.
     */
    public LocalDateTime getExpiredate() {
        return (LocalDateTime) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SessionsRecord
     */
    public SessionsRecord() {
        super(Sessions.SESSIONS);
    }

    /**
     * Create a detached, initialised SessionsRecord
     */
    public SessionsRecord(ULong userid, String token, LocalDateTime expiredate) {
        super(Sessions.SESSIONS);

        setUserid(userid);
        setToken(token);
        setExpiredate(expiredate);
        resetChangedOnNotNull();
    }
}
