package kpi.hlibsukhoruchkin.kursova;

//import at.favre.lib.crypto.bcrypt.*; // Does NOT work even though I downloaded it and everything ;-;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kpi.hlibsukhoruchkin.kursova.jooq.tables.Roles;
import kpi.hlibsukhoruchkin.kursova.jooq.tables.records.SessionsRecord;
import kpi.hlibsukhoruchkin.kursova.jooq.tables.records.UsersRecord;
import kpi.hlibsukhoruchkin.kursova.sql.Connection;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Objects;
import java.util.stream.Collectors;

import static kpi.hlibsukhoruchkin.kursova.jooq.Tables.*;

public class User {
    private static final SecureRandom secureRandom = new SecureRandom();
    public static final int cookieTime = 60 * 60 * 24;
    private UsersRecord usersRecord;
    private String generateRandomToken(int length) {
        byte[] randomBytes = new byte[length * 4];
        secureRandom.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes).substring(0, length);
    }
    private final DSLContext connection;
    public User() {
        try {
            connection = Connection.getInstance().getDSLContext();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public String username;
    public String password;
    public JSONObject validateRequest(HttpServletRequest request) throws IOException {
        JSONObject result = new JSONObject();
        String jsonBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        try {
            JSONObject jsonObject = new JSONObject(jsonBody);
            this.username = jsonObject.getString("username");
            this.password = jsonObject.getString("password");
        } catch (JSONException e) {
            result.put("success",false);
            result.put("error","Specify login or password.");
            result.put("errorCode",HttpServletResponse.SC_BAD_REQUEST);
            return result;
        }

        if (this.username == null || this.password == null) {
            result.put("success",false);
            result.put("error","Specify login or password.");
            result.put("errorCode",HttpServletResponse.SC_BAD_REQUEST);
            return result;
        }
        result.put("success",true);
        return result;
    }
    public UsersRecord findUser() {
        if (Objects.isNull(usersRecord)) usersRecord = connection.selectFrom(USERS)
                .where(USERS.LOGIN.eq(this.username))
                .fetchOne();
        return usersRecord;
    }

    public JSONObject register() {
        JSONObject result = new JSONObject();
        if (!Objects.isNull(this.findUser())) {
            result.put("success",false);
            result.put("error","Username already exists.");
            result.put("errorCode",HttpServletResponse.SC_UNAUTHORIZED);
            return result;
        }

        String passwordHash = password;

        UsersRecord newUser = connection.newRecord(USERS);
        newUser.setLogin(this.username);
        newUser.setPasswordhash(passwordHash);
        newUser.store();
        result.put("success",true);
        return result;
    }
    public Cookie generateCookie() {
        String token = generateRandomToken(64);

        while (true) {
            SessionsRecord existingSession = connection.selectFrom(SESSIONS)
                    .where(SESSIONS.TOKEN.eq(token))
                    .fetchOne();
            if (existingSession == null) {
                break;
            } else {
                token = generateRandomToken(64);
            }
        }

        SessionsRecord sessionRecord = connection.newRecord(SESSIONS);
        sessionRecord.setUserid(this.findUser().getUserid());
        sessionRecord.setToken(token);
        sessionRecord.setExpiredate(LocalDateTime.ofInstant(Instant.now(), ZoneId.of("Europe/Kyiv")).plusSeconds(cookieTime));
        sessionRecord.store();

        Cookie cookie = new Cookie("session_token", token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(cookieTime);

        return cookie;
    }
    public JSONObject login() {
        JSONObject result = new JSONObject();

        if (Objects.isNull(this.findUser())) {
            result.put("success",false);
            result.put("error","Wrong login or password!");
            result.put("errorCode",HttpServletResponse.SC_UNAUTHORIZED);
            return result;
        }

        if (!password.equals(this.findUser().getPasswordhash())) {
            result.put("success",false);
            result.put("error","Wrong login or password!");
            result.put("errorCode",HttpServletResponse.SC_UNAUTHORIZED);
            return result;
        }

        result.put("success",true);
        return result;
    }
    public void getUserByCookie(Cookie cookie) {
        this.username = Objects.requireNonNull(connection.select(USERS.LOGIN)
                .from(SESSIONS)
                .leftJoin(USERS)
                .on(SESSIONS.USERID.eq(USERS.USERID))
                .where(SESSIONS.TOKEN.eq(cookie.getValue()))
                .and(SESSIONS.EXPIREDATE.ge(LocalDateTime.ofInstant(Instant.now(), ZoneId.of("Europe/Kyiv"))))
                .fetchOne()).getValue(USERS.LOGIN);
    }
    public boolean checkIfAdmin() {
        if (Objects.isNull(this.username) || this.username.isEmpty()) {
            return false;
        }
        String roleName = Objects.requireNonNull(connection.select(ROLES.ROLENAME)
                .from(USERS)
                .leftJoin(ROLES)
                .on(ROLES.ROLEID.eq(USERS.ROLEID))
                .where(USERS.LOGIN.eq(this.username))
                .fetchOne()).getValue(ROLES.ROLENAME);
        return roleName.equals("admin");
    }
}
