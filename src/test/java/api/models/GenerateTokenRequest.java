package api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenerateTokenRequest {

    private String userName;
    private String password;

    @Override
    public String toString() {
        return "GenerateTokenRequest(userName=" + userName + ", password=***)";
    }
}
