package kpi.hlibsukhoruchkin.kursova.api;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kpi.hlibsukhoruchkin.kursova.sql.Connection;
import org.jooq.*;
import org.jooq.types.ULong;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import static kpi.hlibsukhoruchkin.kursova.jooq.Tables.CHANNELS;
import static org.jooq.impl.DSL.*;

@WebServlet(name = "maxChannelAPI", value = "/api/channels/max")
public class MaxChannels extends HttpServlet {
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
        Result<Record1<ULong>> result = connection
                .select(max(CHANNELS.CHANNELID))
                .from(CHANNELS)
                .fetch();
        String jsonResult = result.formatJSON(new JSONFormat().recordFormat(JSONFormat.RecordFormat.OBJECT));
        JSONObject jsonObject = new JSONObject(jsonResult);
        String records = jsonObject.getJSONArray("records").getJSONObject(0).get("max").toString();
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.println(records);
    }

    public void destroy() {
    }
}