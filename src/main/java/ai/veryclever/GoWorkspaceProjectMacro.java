package ai.veryclever;

import com.intellij.ide.macro.Macro;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GoWorkspaceProjectMacro extends Macro {

    @NotNull
    @Override
    public String getName() {
        return "CurrentGoWorkspaceProjectPath";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Returns the current go workspace project path";
    }

    @Nullable
    @Override
    public String expand(@NotNull DataContext dataContext) {
        Project project = CommonDataKeys.PROJECT.getData(dataContext);
        VirtualFile file = CommonDataKeys.VIRTUAL_FILE.getData(dataContext);

        if (project == null || file == null) {
            return null;
        }

        String projectPath = project.getBasePath();
        String relativeFilePath = file.getPath().substring(projectPath.length());

        // Extract the first directory from the relative file path
        String[] pathComponents = relativeFilePath.split("/");
        if (pathComponents.length > 1) {
            projectPath += "/" + pathComponents[1]; // Append the first directory to the project path
        }

        return projectPath;
    }
}
