package io.zenbydef.usertracker.security.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize(value = "hasAuthority('user.list.read')")
public @interface UserListReadPermission {
}
