package dto;
import java.util.HashMap;
import java.util.Map;
 
public enum MemberType {
    GOLD(1, "Gold Member", ""),
    SILVER(2, "Silver Member", ""),
    COPPER(3, "Copper Member", ""),
    NORMAL(4, "Normal Member", "");
    
    private int type;
    private String text;
    private String description;

    private static Map<Integer, MemberType> TypeToText;
 
    private MemberType(int code, String label, String description) {
        this.type = code;
        this.text = label;
        this.description = description;
    }
 
    @SuppressWarnings("unused")
	public static MemberType getType(int i) {
        if (TypeToText == null) {
            initMapping();
        }
        MemberType result = null;
        for (MemberType s : values()) {
            result = TypeToText.get(i);
        }
        return result;
    }
 
    private static void initMapping() {
        TypeToText = new HashMap<Integer, MemberType>();
        for (MemberType s : values()) {
            TypeToText.put(s.type, s);
        }
    }
 
    public int getType() {
        return type;
    }
 
    public String getLabel() {
        return text;
    }
 
    public String getDescription() {
        return description;
    }
}