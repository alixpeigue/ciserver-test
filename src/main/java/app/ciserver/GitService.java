package app.ciserver;

import java.nio.file.Path;

public class GitService {
	CommandService commandService;

	public GitService(CommandService commandService) {
		this.commandService = commandService;
	}

	public void clone(Path destination, String source) {
		int returnCode = commandService.execute("git clone " + destination.toString() + " " + source.toString());
		if (returnCode != 0) {
			throw new RuntimeException("Error cloning " + source + " to " + destination);
		}
	}
}
