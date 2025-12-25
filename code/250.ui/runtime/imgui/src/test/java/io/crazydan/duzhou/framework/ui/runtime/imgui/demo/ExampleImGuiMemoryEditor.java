/*
 * 渡舟平台 - 致力于构建自运维、自监控、可演化的应用生产平台
 * Copyright (C) 2025 Crazydan Studio <https://studio.crazydan.org>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.
 * If not, see <https://www.gnu.org/licenses/lgpl-3.0.en.html#license-text>.
 */

package io.crazydan.duzhou.framework.ui.runtime.imgui.demo;

import java.awt.*;
import java.net.URI;
import java.nio.ByteBuffer;

import imgui.extension.memedit.MemoryEditor;
import imgui.flag.ImGuiCond;
import imgui.internal.ImGui;
import imgui.type.ImBoolean;
import org.lwjgl.system.MemoryUtil;

/** source from https://github.com/SpaiR/imgui-java/tree/main/example/src/main/java */
public class ExampleImGuiMemoryEditor {
    private static final String URL = "https://github.com/ocornut/imgui_club";
    private static final ByteBuffer demoData;
    private static final MemoryEditor memoryEditor;
    private static final ImBoolean showingMemoryEditor = new ImBoolean(false);

    static {
        demoData = MemoryUtil.memASCII("Welcome to the demo for Dear ImGui Memory Editor."
                                       + "\n The git repo is located at "
                                       + URL
                                       + "."
                                       + "You can use this memory editor to view the raw memory values at some pointer location.");

        memoryEditor = new MemoryEditor();
    }

    public static void show(ImBoolean showImGuiMemoryEditor) {
        ImGui.setNextWindowSize(400, 200, ImGuiCond.Once);
        ImGui.setNextWindowPos(ImGui.getMainViewport().getPosX() + 100,
                               ImGui.getMainViewport().getPosY() + 100,
                               ImGuiCond.Once);
        if (ImGui.begin("ImGuiMemoryEditor Demo", showImGuiMemoryEditor)) {
            ImGui.text("This a demo for ImGui MemoryEditor");

            ImGui.alignTextToFramePadding();
            ImGui.text("Repo:");
            ImGui.sameLine();
            if (ImGui.button(URL)) {
                try {
                    Desktop.getDesktop().browse(new URI(URL));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            ImGui.checkbox("Memory Editor", showingMemoryEditor);
            if (showingMemoryEditor.get()) {
                memoryEditor.drawWindow("Example Data", MemoryUtil.memAddress(demoData), demoData.capacity());
            }
        }
        ImGui.end();
    }
}
