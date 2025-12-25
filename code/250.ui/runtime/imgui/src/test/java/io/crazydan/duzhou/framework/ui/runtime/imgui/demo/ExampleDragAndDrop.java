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

import java.util.concurrent.atomic.AtomicInteger;

import imgui.ImGui;
import imgui.flag.ImGuiDragDropFlags;
import imgui.flag.ImGuiInputTextFlags;
import imgui.flag.ImGuiWindowFlags;
import imgui.type.ImBoolean;
import imgui.type.ImInt;
import imgui.type.ImString;

/** source from https://github.com/SpaiR/imgui-java/tree/main/example/src/main/java */
public class ExampleDragAndDrop {
    private static final String PAYLOAD_TYPE = "PAYLOAD";

    private static final StringPayload STRING_PAYLOAD = new StringPayload();
    private static final IntegerPayload INTEGER_PAYLOAD = new IntegerPayload();

    private static final AtomicInteger CLASS_SPECIFIC_PAYLOAD = new AtomicInteger(23);

    private static String data = "No Data...";

    public static void show(final ImBoolean showDragNDropWindow) {
        if (ImGui.begin("Drag'N'Drop Demo", showDragNDropWindow, ImGuiWindowFlags.AlwaysAutoResize)) {
            ImGui.text("Drag from here:");

            ImGui.inputText("String payload", STRING_PAYLOAD.input, ImGuiInputTextFlags.CallbackResize);
            setupPayload(STRING_PAYLOAD);

            ImGui.inputInt("Integer payload", INTEGER_PAYLOAD.input);
            setupPayload(INTEGER_PAYLOAD);

            ImGui.button("Class specific payload");
            setupClassSpecificPayload();

            ImGui.separator();

            ImGui.text("Drop here any:");
            ImGui.button(data, 100, 50);
            setupTarget();

            ImGui.separator();

            ImGui.text("Drop here string payload only");
            ImGui.button(data, 100, 50);
            setupStringPayloadTarget();

            ImGui.separator();

            ImGui.text("Drop here class specific payload only");
            ImGui.button(data, 100, 50);
            setupClassSpecificPayloadTarget();
        }
        ImGui.end();
    }

    private static void setupPayload(final Payload<?> payload) {
        if (ImGui.beginDragDropSource(ImGuiDragDropFlags.SourceAllowNullID)) {
            ImGui.setDragDropPayload(PAYLOAD_TYPE, payload);
            ImGui.text("Dragging: " + payload.getData());
            ImGui.endDragDropSource();
        }
    }

    private static void setupClassSpecificPayload() {
        if (ImGui.beginDragDropSource()) {
            ImGui.setDragDropPayload(CLASS_SPECIFIC_PAYLOAD);
            ImGui.text("Dragging class specific payload");
            ImGui.endDragDropSource();
        }
    }

    private static void setupTarget() {
        if (ImGui.beginDragDropTarget()) {
            Payload<?> payload = ImGui.acceptDragDropPayload(PAYLOAD_TYPE);
            if (payload != null) {
                data = String.valueOf(payload.getData());
            }
            ImGui.endDragDropTarget();
        }
    }

    /**
     * Type safe example. ImGui will show that it can accept payload, but payload itself will be null.
     */
    private static void setupStringPayloadTarget() {
        if (ImGui.beginDragDropTarget()) {
            StringPayload payload = ImGui.acceptDragDropPayload(PAYLOAD_TYPE, StringPayload.class);
            if (payload != null) {
                data = payload.getData();
            }
            ImGui.endDragDropTarget();
        }
    }

    /**
     * Class specific example. We can bind our payload to a specific class, so it will be 100% type safe.
     */
    private static void setupClassSpecificPayloadTarget() {
        if (ImGui.beginDragDropTarget()) {
            AtomicInteger payload = ImGui.acceptDragDropPayload(AtomicInteger.class);
            if (payload != null) {
                data = String.valueOf(payload.get());
            }
            ImGui.endDragDropTarget();
        }
    }

    private interface Payload<T> {
        T getData();
    }

    private static final class StringPayload implements Payload<String> {
        ImString input = new ImString("You can drag inputs as well");

        @Override
        public String getData() {
            return this.input.get();
        }
    }

    private static final class IntegerPayload implements Payload<Integer> {
        ImInt input = new ImInt();

        @Override
        public Integer getData() {
            return this.input.get();
        }
    }
}
