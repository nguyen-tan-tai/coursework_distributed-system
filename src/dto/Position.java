package dto;
import java.util.HashMap;
import java.util.Map;
 
public enum Position {
    STAFF(1, "Staff", ""),
    LOCAL_MANAGER(2, "Local Manager", ""),
    NATIONAL_MANAGER(3, "National Manager", "");
 
    private int position;
    private String text;
    private String description;

    private static Map<Integer, Position> positionToText;
 
    private Position(int code, String label, String description) {
        this.position = code;
        this.text = label;
        this.description = description;
    }
 
    @SuppressWarnings("unused")
	public static Position getPosition(int i) {
        if (positionToText == null) {
            initMapping();
        }
        Position result = null;
        for (Position s : values()) {
            result = positionToText.get(i);
        }
        return result;
    }
 
    private static void initMapping() {
        positionToText = new HashMap<Integer, Position>();
        for (Position s : values()) {
            positionToText.put(s.position, s);
        }
    }
 
    public int getCode() {
        return position;
    }
 
    public String getLabel() {
        return text;
    }
 
    public String getDescription() {
        return description;
    }
}