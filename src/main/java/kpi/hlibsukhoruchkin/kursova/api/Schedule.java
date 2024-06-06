package kpi.hlibsukhoruchkin.kursova.api;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import static kpi.hlibsukhoruchkin.kursova.jooq.Tables.*;

import jakarta.servlet.http.Cookie;
import kpi.hlibsukhoruchkin.kursova.jooq.tables.records.ScheduleRecord;
import kpi.hlibsukhoruchkin.kursova.sql.Connection;
import org.jooq.*;
import org.jooq.types.ULong;
import org.json.*;


@WebServlet(name = "scheduleAPI", value = "/api/schedule")
public class Schedule extends HttpServlet {
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
        String channels = request.getParameter("channels");
        if (channels == null || !channels.matches("\\d+(,\\d+)*")) {
            channels = "1,2,3,4,5,6,7";
        }
        String startDayParam = request.getParameter("startDay");
        String endDayParam = request.getParameter("endDay");
        LocalDate startDate;
        LocalDate endDate;

        try {
            if (startDayParam == null || startDayParam.trim().isEmpty()) {
                startDate = LocalDate.now();
            } else {
                startDate = LocalDate.parse(startDayParam, DateTimeFormatter.ISO_LOCAL_DATE);
            }
        } catch (Exception e) {
            startDate = LocalDate.now();
        }

        try {
            if (endDayParam == null || endDayParam.trim().isEmpty()) {
                endDate = startDate;
            } else {
                endDate = LocalDate.parse(endDayParam, DateTimeFormatter.ISO_LOCAL_DATE);
            }
        } catch (Exception e) {
            endDate = startDate;
        }

        LocalDateTime startOfDay = startDate.atStartOfDay();
        LocalDateTime endOfDay = endDate.atTime(23, 59, 59);
        List<ULong> channelIds = Arrays.stream(channels.split(","))
                .map(String::trim)
                .map(ULong::valueOf)
                .collect(Collectors.toList());
        Result<Record3<String, LocalDateTime, ULong>> result = connection
                .select(SHOWS.SHOWNAME, SCHEDULE.SHOWTIME, CHANNELS.CHANNELID)
                .from(SCHEDULE)
                .leftJoin(SHOWS)
                .on(SCHEDULE.SHOWID.eq(SHOWS.SHOWID))
                .leftJoin(CHANNELS)
                .on(CHANNELS.CHANNELID.eq(SHOWS.CHANNELID))
                .where(SCHEDULE.SHOWTIME.between(startOfDay, endOfDay))
                .and(SHOWS.CHANNELID.in(channelIds))
                .and(CHANNELS.ACTIVE.eq((byte) 1))
                .and(SHOWS.ACTIVE.eq((byte) 1))
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
        kpi.hlibsukhoruchkin.kursova.User user = new kpi.hlibsukhoruchkin.kursova.User();
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
            result.put("success", false);
            result.put("error", "You must login first!");
            result.put("errorCode", HttpServletResponse.SC_UNAUTHORIZED);
            response.setStatus(result.getInt("errorCode"));
            out.println(result);
            return;
        }
        user.getUserByCookie(userSessionCookie);
        if (!user.checkIfAdmin()) {
            JSONObject result = new JSONObject();
            result.put("success", false);
            result.put("error", "You do not have permission to do that!");
            result.put("errorCode", HttpServletResponse.SC_UNAUTHORIZED);
            response.setStatus(result.getInt("errorCode"));
            out.println(result);
            return;
        }

        String jsonBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        ULong showID;
        LocalDateTime showTime;
        try {
            JSONObject jsonObject = new JSONObject(jsonBody);
            showID = ULong.valueOf(jsonObject.getBigInteger("showID"));
            showTime = LocalDateTime.parse(jsonObject.getString("showTime"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (JSONException | NullPointerException e) {
            JSONObject result = new JSONObject();
            result.put("success", false);
            result.put("error", "Specify the show ID and the show time in the format 'YYYY-MM-DD HH:MM'!");
            result.put("errorCode", HttpServletResponse.SC_BAD_REQUEST);
            response.setStatus(result.getInt("errorCode"));
            out.println(result);
            return;
        }

        ScheduleRecord schedule = connection.newRecord(SCHEDULE);
        schedule.setShowid(showID);
        schedule.setShowtime(showTime);
        schedule.store();

        JSONObject result = new JSONObject();
        result.put("success", true);
        out.println(result);
    }

    public void destroy() {
    }
}