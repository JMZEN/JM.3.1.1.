package io.zenbydef.usertracker;

import io.zenbydef.usertracker.service.userdtoservice.UserDtoServiceImpl;
import io.zenbydef.usertracker.util.IdGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@DisplayName("IdGeneratorTest must")
public class IdGeneratorTest {
    @Mock
    IdGenerator idGenerator;

    @Test
    @DisplayName("generate UserId")
    final void testGenerateUserId() {

        String userId1 = idGenerator.generateUserId(6);
        String userId2 = idGenerator.generateUserId(6);
        String userId3 = "hello";

        assertNotNull(userId1);
//        assertNotNull(userId2);
//        assertEquals(6, userId1.length());
//        assertEquals(6, userId2.length());
    }
}
