package app.ciserver;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class GitServiceTest {

	private final CommandService commandService = mock(CommandService.class);
	private final GitService gitService = new GitService(commandService);

	@Test
	void testCloneError() {
		final String expected = "git clone a/b http://source";
		when(commandService.execute(expected)).thenReturn(1);
		assertThrows(RuntimeException.class, () -> gitService.clone(Path.of("a", "b"), "http://source"));
	}

	@Test
	void testCloneNoError() {
		final String expected = "git clone a/b http://sourc";
		when(commandService.execute(expected)).thenReturn(0);
		gitService.clone(Path.of("a", "b"), "http://source");
		verify(commandService).execute(expected);
	}
}
