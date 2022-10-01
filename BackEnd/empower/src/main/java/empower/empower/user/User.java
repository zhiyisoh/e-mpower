package empower.empower.user;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
// @Getter
// @Setter
// @ToString
// @AllArgsConstructor
// @NoArgsConstructor
// @EqualsAndHashCode
public class User {
    private @Id @GeneratedValue (strategy = GenerationType.IDENTITY) Long id;

    @NotNull(message="Password should not be null")
    @Size(min=6, max=200, message="Password should be at least 6 characters long")
    private String username;

    @NotNull(message="Password should not be null")
    @Size(min=8, max=200, message="Password should be at least 8 characters long")
    private String password;

    @NotNull(message="Email should not be null")
    @Size(min=10, max=200, message="Email should be at least 10 characters long")
    private String email;
}
