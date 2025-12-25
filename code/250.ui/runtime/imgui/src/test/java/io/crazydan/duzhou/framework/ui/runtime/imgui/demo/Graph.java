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

import java.util.HashMap;
import java.util.Map;

/** source from https://github.com/SpaiR/imgui-java/tree/main/example/src/main/java */
public final class Graph {
    public final Map<Integer, GraphNode> nodes = new HashMap<>();
    public int nextNodeId = 1;
    public int nextPinId = 100;

    public Graph() {
        final GraphNode first = createGraphNode();
        final GraphNode second = createGraphNode();
        first.outputNodeId = second.nodeId;
    }

    public GraphNode createGraphNode() {
        final GraphNode node = new GraphNode(this.nextNodeId++, this.nextPinId++, this.nextPinId++);
        this.nodes.put(node.nodeId, node);
        return node;
    }

    public GraphNode findByInput(final long inputPinId) {
        for (GraphNode node : this.nodes.values()) {
            if (node.getInputPinId() == inputPinId) {
                return node;
            }
        }
        return null;
    }

    public GraphNode findByOutput(final long outputPinId) {
        for (GraphNode node : this.nodes.values()) {
            if (node.getOutputPinId() == outputPinId) {
                return node;
            }
        }
        return null;
    }

    public static final class GraphNode {
        public final int nodeId;
        public final int inputPinId;
        public final int outputPinId;

        public int outputNodeId = -1;

        public GraphNode(final int nodeId, final int inputPinId, final int outputPintId) {
            this.nodeId = nodeId;
            this.inputPinId = inputPinId;
            this.outputPinId = outputPintId;
        }

        public int getInputPinId() {
            return this.inputPinId;
        }

        public int getOutputPinId() {
            return this.outputPinId;
        }

        public String getName() {
            return "Node " + (char) (64 + this.nodeId);
        }
    }
}
