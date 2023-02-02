package activelearning.com.SHARED.custom;


public class Errors {

    private String errors;
    private String path;
    private String status;
    private String timestamp;

    public Errors(String errors, String path, String status, String timestamp) {
        this.errors = errors;
        this.path = path;
        this.status = status;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Errors{" +
                "errors='" + errors + '\'' +
                ", path='" + path + '\'' +
                ", status='" + status + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
