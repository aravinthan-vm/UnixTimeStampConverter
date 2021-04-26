package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(urlPatterns = {"/unixConverter"})
public class UnixTimeStampConverter extends HttpServlet {

	private static final long serialVersionUID = -1368485891448365460L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		JSONObject response = new JSONObject();
		
		try {
			JSONObject data = new JSONObject();
			JSONObject reqObject = getRequestObject(req.getInputStream());
			
			/** @param mode specifies the operation to be performed */
			String mode = reqObject.getString(RequestConstants.MODE);
			data.put(RequestConstants.MODE, mode);
			
			switch (mode) {

			case RequestConstants.GET_DATE :
				long timeStamp = Long.parseLong(reqObject.getString(RequestConstants.TIMESTAMP));
				String timeZoneId = reqObject.getString(RequestConstants.TIMEZONE);
				String dateFormat = reqObject.getString(RequestConstants.DATE_FORMAT);
				
				String dateStr = ConverterUtil.getDateFromTimeStamp(timeStamp, timeZoneId, dateFormat);
				data.put(ResponseConstants.DATE, dateStr);
				break;
			
			case RequestConstants.GET_TIMEZONES :
				JSONArray timeZones = ConverterUtil.getAllTimeZones();
				data.put(ResponseConstants.TIMEZONES, timeZones);
				break;
				
			default :
				response.put(ResponseConstants.ERROR, ErrorMessage.INVALID_MODE);
				break;
			}
			
			response.put(ResponseConstants.DATA, data);
		} catch(Exception e) {
			
			log(e.getMessage());
			e.printStackTrace();
			
			response.put(ResponseConstants.ERROR, ErrorMessage.UNKNOWN_ERROR);
			response.put(ResponseConstants.DESCRIPTION, e.getMessage());
		}
		
		res.setContentType("application/json");
		res.getWriter().print(response);;
	}
	
	private JSONObject getRequestObject(ServletInputStream inputStream) throws Exception {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			
			String line;
			StringBuilder builder = new StringBuilder();
			while((line = reader.readLine()) != null) {
				builder.append(line);
			}
			
			return new JSONObject(builder.toString());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
