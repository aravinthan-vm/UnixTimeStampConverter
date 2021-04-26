package servlet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONObject;

public class ConverterUtil {

	/* Getting All available Timezones */
	public static JSONArray getAllTimeZones() throws Exception {
		JSONArray response = new JSONArray();
		
		try {
			List<TimeZone> tzList = new ArrayList<>();
			for (String tz : TimeZone.getAvailableIDs()) {
				tzList.add(TimeZone.getTimeZone(tz));
			}
			
			/* Comparator to sort the TimeZone by offset and by name */
			Collections.sort(tzList, new Comparator<TimeZone>() {
				@Override
				public int compare(TimeZone tz1, TimeZone tz2) {
					return (tz1.getRawOffset() - tz2.getRawOffset() >= 0)
							? (tz1.getRawOffset() == tz2.getRawOffset()) ? tz1.getID().compareTo(tz2.getID()) : 1
							: -1;
				}
			});
			
			for (TimeZone tz : tzList) {
				JSONObject json = new JSONObject();
				json.put(ResponseConstants.TIMEZONE_ID, tz.getID());
				json.put(ResponseConstants.TIMEZONE_OFFSET, getOffset(tz.getRawOffset()));
				response.put(json);
			}
			return response;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public static String getDateFromTimeStamp(long timeStamp, String timeZoneId, String dateFormat) throws Exception {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			sdf.setTimeZone(TimeZone.getTimeZone(timeZoneId));
			return sdf.format(new Date(timeStamp));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	// Helper methods
	
	private static String getOffset(int rawOffset) {
		
        int offset = Math.abs(rawOffset);
        int temp = offset / (1000 * 60);
        
        String prefix = (rawOffset < 0) ? Charset.MINUS : Charset.PLUS;
        String hours = String.format("%02d", temp / 60);
        String mins = String.format("%02d", temp % 60);
        
        return ResponseConstants.UTC + Charset.SPACE + prefix + hours + ":" + mins;
	}
	
}
