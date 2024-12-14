package org.example.exceptions;

import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * This component is created conditionally based on the application properties configuration.
 *
 * This bean is specifically tied to the homework task "ThisIsMyFirstConditionalBean",
 * which is activated when the corresponding property is set to true.
 */
@Getter
@Component
public class CustomLoginErrorHandler {

    private boolean blockLogin;

    public void setBlockLogin(boolean blockLogin) {
        this.blockLogin = blockLogin;
    }

    /**
     * Returns an error message based on the block login status.
     *
     * @return an error message if login is blocked, an empty string if not
     */
    public String getErrorMessage() {
        return blockLogin ? "The system is blocked for maintenance!" : "";
    }
}
