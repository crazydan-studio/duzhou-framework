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

import imgui.extension.implot.ImPlot;
import imgui.extension.implot.ImPlotPoint;
import imgui.flag.ImGuiCond;
import imgui.internal.ImGui;
import imgui.type.ImBoolean;

/** source from https://github.com/SpaiR/imgui-java/tree/main/example/src/main/java */
public class ExampleImPlot {
    private static final String URL = "https://github.com/epezent/implot/tree/555ff68";
    private static final ImBoolean showDemo = new ImBoolean(false);

    private static final int[] xs = { 0, 1, 2, 3, 4, 5 };
    private static final int[] ys = { 0, 1, 2, 3, 4, 5 };
    private static final int[] ys1 = { 0, 0, 1, 2, 3, 4 };
    private static final int[] ys2 = { 1, 2, 3, 4, 5, 6 };

    static {
        ImPlot.createContext();
    }

    public static void show(ImBoolean showImPlotWindow) {
        ImGui.setNextWindowSize(500, 400, ImGuiCond.Once);
        ImGui.setNextWindowPos(ImGui.getMainViewport().getPosX() + 100,
                               ImGui.getMainViewport().getPosY() + 100,
                               ImGuiCond.Once);
        if (ImGui.begin("ImPlot Demo", showImPlotWindow)) {
            ImGui.text("This a demo for ImPlot");

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

            ImGui.checkbox("Show ImPlot Built-In Demo", showDemo);

            if (showDemo.get()) {
                ImPlot.showDemoWindow(showDemo);
            } else {
                if (ImPlot.beginPlot("Example Plot")) {
                    ImPlot.plotShaded("Shaded", xs, ys1, ys2);
                    ImPlot.plotLine("Line", xs, ys);
                    ImPlot.plotBars("Bars", xs, ys);
                    ImPlot.endPlot();
                }

                if (ImPlot.beginPlot("Example Scatterplot")) {
                    ImPlot.plotScatter("Scatter", xs, ys);
                    ImPlot.endPlot();
                }

                if (ImPlot.beginPlot("Example Piechart")) {
                    ImPlot.plotPieChart(new String[] { "1", "2", "3", "4", "5", "6" }, xs, .5, .5, .4);
                    ImPlot.endPlot();
                }

                if (ImPlot.beginPlot("Example Heatmap")) {
                    ImPlot.plotHeatmap("Heatmap",
                                       new int[] { 1, 3, 6, 2, 8, 5, 4, 3 },
                                       2,
                                       4,
                                       0,
                                       0,
                                       "%d",
                                       new ImPlotPoint(0, 0),
                                       new ImPlotPoint(10, 10));
                    ImPlot.endPlot();
                }
            }
        }

        ImGui.end();
    }
}
