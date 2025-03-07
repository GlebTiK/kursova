/*
 * This file is generated by jOOQ.
 */
package kpi.hlibsukhoruchkin.kursova.jooq.tables;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import kpi.hlibsukhoruchkin.kursova.jooq.Keys;
import kpi.hlibsukhoruchkin.kursova.jooq.Kursova;
import kpi.hlibsukhoruchkin.kursova.jooq.tables.Shows.ShowsPath;
import kpi.hlibsukhoruchkin.kursova.jooq.tables.records.ChannelsRecord;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.InverseForeignKey;
import org.jooq.Name;
import org.jooq.Path;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Record;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.jooq.types.ULong;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Channels extends TableImpl<ChannelsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>kursova.channels</code>
     */
    public static final Channels CHANNELS = new Channels();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ChannelsRecord> getRecordType() {
        return ChannelsRecord.class;
    }

    /**
     * The column <code>kursova.channels.ChannelID</code>.
     */
    public final TableField<ChannelsRecord, ULong> CHANNELID = createField(DSL.name("ChannelID"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "");

    /**
     * The column <code>kursova.channels.ChannelName</code>.
     */
    public final TableField<ChannelsRecord, String> CHANNELNAME = createField(DSL.name("ChannelName"), SQLDataType.VARCHAR(20).nullable(false), this, "");

    /**
     * The column <code>kursova.channels.Active</code>.
     */
    public final TableField<ChannelsRecord, Byte> ACTIVE = createField(DSL.name("Active"), SQLDataType.TINYINT.nullable(false).defaultValue(DSL.inline("1", SQLDataType.TINYINT)), this, "");

    private Channels(Name alias, Table<ChannelsRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Channels(Name alias, Table<ChannelsRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>kursova.channels</code> table reference
     */
    public Channels(String alias) {
        this(DSL.name(alias), CHANNELS);
    }

    /**
     * Create an aliased <code>kursova.channels</code> table reference
     */
    public Channels(Name alias) {
        this(alias, CHANNELS);
    }

    /**
     * Create a <code>kursova.channels</code> table reference
     */
    public Channels() {
        this(DSL.name("channels"), null);
    }

    public <O extends Record> Channels(Table<O> path, ForeignKey<O, ChannelsRecord> childPath, InverseForeignKey<O, ChannelsRecord> parentPath) {
        super(path, childPath, parentPath, CHANNELS);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class ChannelsPath extends Channels implements Path<ChannelsRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> ChannelsPath(Table<O> path, ForeignKey<O, ChannelsRecord> childPath, InverseForeignKey<O, ChannelsRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private ChannelsPath(Name alias, Table<ChannelsRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public ChannelsPath as(String alias) {
            return new ChannelsPath(DSL.name(alias), this);
        }

        @Override
        public ChannelsPath as(Name alias) {
            return new ChannelsPath(alias, this);
        }

        @Override
        public ChannelsPath as(Table<?> alias) {
            return new ChannelsPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Kursova.KURSOVA;
    }

    @Override
    public Identity<ChannelsRecord, ULong> getIdentity() {
        return (Identity<ChannelsRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<ChannelsRecord> getPrimaryKey() {
        return Keys.KEY_CHANNELS_PRIMARY;
    }

    @Override
    public List<UniqueKey<ChannelsRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.KEY_CHANNELS_CHANNELS_UNIQUE);
    }

    private transient ShowsPath _shows;

    /**
     * Get the implicit to-many join path to the <code>kursova.shows</code>
     * table
     */
    public ShowsPath shows() {
        if (_shows == null)
            _shows = new ShowsPath(this, null, Keys.SHOWS_CHANNELS_FK.getInverseKey());

        return _shows;
    }

    @Override
    public Channels as(String alias) {
        return new Channels(DSL.name(alias), this);
    }

    @Override
    public Channels as(Name alias) {
        return new Channels(alias, this);
    }

    @Override
    public Channels as(Table<?> alias) {
        return new Channels(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Channels rename(String name) {
        return new Channels(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Channels rename(Name name) {
        return new Channels(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Channels rename(Table<?> name) {
        return new Channels(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Channels where(Condition condition) {
        return new Channels(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Channels where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Channels where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Channels where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Channels where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Channels where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Channels where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Channels where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Channels whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Channels whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
