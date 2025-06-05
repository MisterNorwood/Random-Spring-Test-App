package records;

import com.example.AWPRSpring.model.User;

import java.util.UUID;

public record UserResponse(
        UUID id, String name, String email, short age, int score, User.Sex sex
) {
}
