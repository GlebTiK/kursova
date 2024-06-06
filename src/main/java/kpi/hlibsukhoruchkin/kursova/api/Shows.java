package kpi.hlibsukhoruchkin.kursova.api;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kpi.hlibsukhoruchkin.kursova.User;
import kpi.hlibsukhoruchkin.kursova.jooq.tables.records.ChannelsRecord;
import kpi.hlibsukhoruchkin.kursova.jooq.tables.records.ShowsRecord;
import kpi.hlibsukhoruchkin.kursova.sql.Connection;
import org.jooq.*;
import org.jooq.types.ULong;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Objects;
import java.util.stream.Collectors;

import static kpi.hlibsukhoruchkin.kursova.jooq.Tables.CHANNELS;
import static kpi.hlibsukhoruchkin.kursova.jooq.Tables.SHOWS;


@WebServlet(name = "showsAPI", value = "/api/shows")
public class Shows extends HttpServlet {
    private DSLContext connection;
    @Override
    public void init() {
        try {
            connection = Connection.getInstance().getDSLContext();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Result<Record3<String, ULong, ULong>> result = connection
                .select(SHOWS.SHOWNAME, SHOWS.SHOWID, SHOWS.CHANNELID)
                .from(SHOWS)
                .orderBy(SHOWS.SHOWID)
                .fetch();
        String jsonResult = result.formatJSON(new JSONFormat().recordFormat(JSONFormat.RecordFormat.OBJECT));
        JSONObject jsonObject = new JSONObject(jsonResult);
        String records = jsonObject.getJSONArray("records").toString();
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println(records);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        User user = new User();
        Cookie[] cookies = request.getCookies();
        Cookie userSessionCookie = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("session_token".equals(cookie.getName())) {
                    userSessionCookie = cookie;
                    break;
                }
            }
        }
        if (Objects.isNull(userSessionCookie)) {
            JSONObject result = new JSONObject();
            result.put("success",false);
            result.put("error","You must login first!");
            result.put("errorCode",HttpServletResponse.SC_UNAUTHORIZED);
            response.setStatus(result.getInt("errorCode"));
            out.println(result);
            return;
        }
        user.getUserByCookie(userSessionCookie);
        if (!user.checkIfAdmin()) {
            JSONObject result = new JSONObject();
            result.put("success",false);
            result.put("error","You do not have permission to do that!");
            result.put("errorCode",HttpServletResponse.SC_UNAUTHORIZED);
            response.setStatus(result.getInt("errorCode"));
            out.println(result);
            return;
        }

        String jsonBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        String showName;
        ULong channelID;
        try {
            JSONObject jsonObject = new JSONObject(jsonBody);
            showName = jsonObject.getString("showName");
            channelID = ULong.valueOf(jsonObject.getBigInteger("channelID"));
        } catch (JSONException e) {
            JSONObject result = new JSONObject();
            result.put("success",false);
            result.put("error","Specify the show name and the channel ID!");
            result.put("errorCode",HttpServletResponse.SC_BAD_REQUEST);
            response.setStatus(result.getInt("errorCode"));
            out.println(result);
            return;
        }
        ShowsRecord show = connection.newRecord(SHOWS);
        show.setShowname(showName);
        show.setChannelid(channelID);
        show.store();

        JSONObject result = new JSONObject();
        result.put("success", true);
        out.println(result);
    }
    public void destroy() {
    }
}