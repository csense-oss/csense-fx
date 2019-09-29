package csense.javafx.extensions.parent

import csense.javafx.annotations.*
import javafx.scene.Node
import javafx.scene.Parent


/**
 * Gets the children of this parent , as a truly unmodifiable list (in kotlin)
 * Since the list is "unmodifiable" that means that adding listeners ect would never get called... so short
 * an readonly list is a readonly list, no matter what.
 */
val Parent.childrenSafe: List<Node>
    @InUI
    get() = childrenUnmodifiable