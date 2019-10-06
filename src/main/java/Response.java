import java.util.HashMap;
import java.util.Map;

public class Response {

    private final Map<String, String> headers;
    private int status;
    private String body;
    private byte[] bodyAsBytes;

    public Response() {
        headers = new HashMap<>();
    }

    Response(int status) {
        this();
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setHeader(String key, String value) {
        headers.put(key, value);
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public byte[] getBodyAsBytes() {
        return bodyAsBytes;
    }

    public void setBodyAsBytes(byte[] bodyAsBytes) {
        this.bodyAsBytes = bodyAsBytes;
    }
}
