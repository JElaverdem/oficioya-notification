package oficioya_notification.service;

import java.util.EnumSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import oficioya_notification.model.Rol;

@Service
public class AuthService {
    private static final Set<Rol> PUBLIC_ROLES =
            EnumSet.of(Rol.TRABAJADOR, Rol.CONTRATANTE);

}
