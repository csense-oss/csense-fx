package csense.javafx.diagram

import csense.kotlin.Function0
import javafx.scene.CacheHint
import javafx.scene.Cursor
import javafx.scene.Group
import javafx.scene.Node
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Pane

/**
 * This Base class contains all the necessary parts resembling a diagram control.
 * Also known as a Node editor
 * or graph editor
 */
class BaseDiagram<NodeType> : Group() where NodeType : Node,
                                            NodeType : BaseDiagramNode {

    private var lastSelectedNodes: MutableList<NodeType> = mutableListOf()

    private var onNodesSelectedCallback: Function0<List<NodeType>>? = null

    private val nodeContainer: Pane = Pane()


    fun setOnNodesSelected(callback: Function0<List<NodeType>>) {
        onNodesSelectedCallback = callback
    }

    fun clearOnNodesSelected() {
        onNodesSelectedCallback = null
    }

    fun addNode(node: NodeType) {
        nodeContainer.children.add(node)
        node.isCache = true
        node.cacheHint = CacheHint.SPEED
        node.apply {
            var x = 0.0
            var y = 0.0
            setOnMousePressed { mouse: MouseEvent ->
                x = layoutX - mouse.sceneX
                y = layoutY - mouse.sceneY
                cursor = Cursor.MOVE
            }
            setOnMouseReleased { mouse: MouseEvent ->
                cursor = Cursor.HAND
                x = 0.0
                y = 0.0
            }
            setOnMouseDragged { mouse: MouseEvent ->
                layoutX = mouse.sceneX + x
                layoutY = mouse.sceneY + y
            }
            setOnMouseEntered { mouse: MouseEvent ->
                cursor = Cursor.HAND
            }

        }
        node.setOnMouseClicked {
            //TODO selection
//            if (node != lastSelectedNode) {
//                lastSelectedNode = node
//                onNodeSelectedCallback?.invoke(node)
//            }
        }
    }

}
