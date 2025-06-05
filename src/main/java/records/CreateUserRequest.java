package records;

import com.example.AWPRSpring.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.NotNull;

public record CreateUserRequest(@NotBlank String name,
                                @NotNull User.Sex sex,
                                @NotBlank @Email String email,
                                @Min(0) @Max(130) short age) {
}
