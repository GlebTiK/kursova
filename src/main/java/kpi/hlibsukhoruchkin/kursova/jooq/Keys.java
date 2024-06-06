/*
 * This file is generated by jOOQ.
 */
package kpi.hlibsukhoruchkin.kursova.jooq;


import kpi.hlibsukhoruchkin.kursova.jooq.tables.Channels;
import kpi.hlibsukhoruchkin.kursova.jooq.tables.Favourites;
import kpi.hlibsukhoruchkin.kursova.jooq.tables.Roles;
import kpi.hlibsukhoruchkin.kursova.jooq.tables.Schedule;
import kpi.hlibsukhoruchkin.kursova.jooq.tables.Sessions;
import kpi.hlibsukhoruchkin.kursova.jooq.tables.Shows;
import kpi.hlibsukhoruchkin.kursova.jooq.tables.Users;
import kpi.hlibsukhoruchkin.kursova.jooq.tables.records.ChannelsRecord;
import kpi.hlibsukhoruchkin.kursova.jooq.tables.records.FavouritesRecord;
import kpi.hlibsukhoruchkin.kursova.jooq.tables.records.RolesRecord;
import kpi.hlibsukhoruchkin.kursova.jooq.tables.records.ScheduleRecord;
import kpi.hlibsukhoruchkin.kursova.jooq.tables.records.SessionsRecord;
import kpi.hlibsukhoruchkin.kursova.jooq.tables.records.ShowsRecord;
import kpi.hlibsukhoruchkin.kursova.jooq.tables.records.UsersRecord;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * kursova.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<ChannelsRecord> KEY_CHANNELS_CHANNELS_UNIQUE = Internal.createUniqueKey(Channels.CHANNELS, DSL.name("KEY_channels_channels_unique"), new TableField[] { Channels.CHANNELS.CHANNELNAME }, true);
    public static final UniqueKey<ChannelsRecord> KEY_CHANNELS_PRIMARY = Internal.createUniqueKey(Channels.CHANNELS, DSL.name("KEY_channels_PRIMARY"), new TableField[] { Channels.CHANNELS.CHANNELID }, true);
    public static final UniqueKey<FavouritesRecord> KEY_FAVOURITES_PRIMARY = Internal.createUniqueKey(Favourites.FAVOURITES, DSL.name("KEY_favourites_PRIMARY"), new TableField[] { Favourites.FAVOURITES.USERID, Favourites.FAVOURITES.SHOWID }, true);
    public static final UniqueKey<RolesRecord> KEY_ROLES_PRIMARY = Internal.createUniqueKey(Roles.ROLES, DSL.name("KEY_roles_PRIMARY"), new TableField[] { Roles.ROLES.ROLEID }, true);
    public static final UniqueKey<RolesRecord> KEY_ROLES_ROLES_UNIQUE = Internal.createUniqueKey(Roles.ROLES, DSL.name("KEY_roles_roles_unique"), new TableField[] { Roles.ROLES.ROLENAME }, true);
    public static final UniqueKey<ScheduleRecord> KEY_SCHEDULE_PRIMARY = Internal.createUniqueKey(Schedule.SCHEDULE, DSL.name("KEY_schedule_PRIMARY"), new TableField[] { Schedule.SCHEDULE.SHOWTIME, Schedule.SCHEDULE.SHOWID }, true);
    public static final UniqueKey<SessionsRecord> KEY_SESSIONS_PRIMARY = Internal.createUniqueKey(Sessions.SESSIONS, DSL.name("KEY_sessions_PRIMARY"), new TableField[] { Sessions.SESSIONS.TOKEN }, true);
    public static final UniqueKey<ShowsRecord> KEY_SHOWS_PRIMARY = Internal.createUniqueKey(Shows.SHOWS, DSL.name("KEY_shows_PRIMARY"), new TableField[] { Shows.SHOWS.SHOWID }, true);
    public static final UniqueKey<ShowsRecord> KEY_SHOWS_SHOWS_UNIQUE = Internal.createUniqueKey(Shows.SHOWS, DSL.name("KEY_shows_shows_unique"), new TableField[] { Shows.SHOWS.SHOWNAME }, true);
    public static final UniqueKey<UsersRecord> KEY_USERS_PRIMARY = Internal.createUniqueKey(Users.USERS, DSL.name("KEY_users_PRIMARY"), new TableField[] { Users.USERS.USERID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<FavouritesRecord, ShowsRecord> FAVOURITES_SHOWS_FK = Internal.createForeignKey(Favourites.FAVOURITES, DSL.name("favourites_shows_FK"), new TableField[] { Favourites.FAVOURITES.SHOWID }, Keys.KEY_SHOWS_PRIMARY, new TableField[] { Shows.SHOWS.SHOWID }, true);
    public static final ForeignKey<FavouritesRecord, UsersRecord> FAVOURITES_USERS_FK = Internal.createForeignKey(Favourites.FAVOURITES, DSL.name("favourites_users_FK"), new TableField[] { Favourites.FAVOURITES.USERID }, Keys.KEY_USERS_PRIMARY, new TableField[] { Users.USERS.USERID }, true);
    public static final ForeignKey<ScheduleRecord, ShowsRecord> SCHEDULE_SHOWS_FK = Internal.createForeignKey(Schedule.SCHEDULE, DSL.name("schedule_shows_FK"), new TableField[] { Schedule.SCHEDULE.SHOWID }, Keys.KEY_SHOWS_PRIMARY, new TableField[] { Shows.SHOWS.SHOWID }, true);
    public static final ForeignKey<SessionsRecord, UsersRecord> SESSIONS_USERS_FK = Internal.createForeignKey(Sessions.SESSIONS, DSL.name("sessions_users_FK"), new TableField[] { Sessions.SESSIONS.USERID }, Keys.KEY_USERS_PRIMARY, new TableField[] { Users.USERS.USERID }, true);
    public static final ForeignKey<ShowsRecord, ChannelsRecord> SHOWS_CHANNELS_FK = Internal.createForeignKey(Shows.SHOWS, DSL.name("shows_channels_FK"), new TableField[] { Shows.SHOWS.CHANNELID }, Keys.KEY_CHANNELS_PRIMARY, new TableField[] { Channels.CHANNELS.CHANNELID }, true);
    public static final ForeignKey<UsersRecord, RolesRecord> USERS_ROLES_FK = Internal.createForeignKey(Users.USERS, DSL.name("users_roles_FK"), new TableField[] { Users.USERS.ROLEID }, Keys.KEY_ROLES_PRIMARY, new TableField[] { Roles.ROLES.ROLEID }, true);
}
