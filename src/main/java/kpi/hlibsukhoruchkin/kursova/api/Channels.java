package kpi.hlibsukhoruchkin.kursova.api;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kpi.hlibsukhoruchkin.kursova.User;
import kpi.hlibsukhoruchkin.kursova.jooq.tables.records.ChannelsRecord;
import kpi.hlibsukhoruchkin.kursova.sql.Connection;
import org.jooq.*;
import org.jooq.types.ULong;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static kpi.hlibsukhoruchkin.kursova.jooq.Tables.*;


@WebServlet(name = "channelsAPI", value = "/api/channels")
public class Channels extends HttpServlet {
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
        Result<Record2<String, ULong>> result = connection
                .select(CHANNELS.CHANNELNAME, CHANNELS.CHANNELID)
                .from(CHANNELS)
                .orderBy(CHANNELS.CHANNELID)
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
        kpi.hlibsukhoruchkin.kursova.User user = new User();
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
        String channelName;
        try {
            JSONObject jsonObject = new JSONObject(jsonBody);
            channelName = jsonObject.getString("channelName");
        } catch (JSONException e) {
            JSONObject result = new JSONObject();
            result.put("success",false);
            result.put("error","Specify the channel name!");
            result.put("errorCode",HttpServletResponse.SC_BAD_REQUEST);
            response.setStatus(result.getInt("errorCode"));
            out.println(result);
            return;
        }
        ChannelsRecord channel = connection.newRecord(CHANNELS);
        channel.setChannelname(channelName);
        channel.store();

        JSONObject result = new JSONObject();
        result.put("success", true);
        out.println(result);
    }
    public void destroy() {
    }
}