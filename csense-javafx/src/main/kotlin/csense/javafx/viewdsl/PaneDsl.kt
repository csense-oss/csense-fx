@file:Suppress("NOTHING_TO_INLINE", "UNUSED")
@file:InUiContext

package csense.javafx.viewdsl

import csense.javafx.extensions.parent.*
import csense.javafx.widgets.*
import csense.kotlin.annotations.threading.*
import javafx.scene.*
import javafx.scene.control.*
import javafx.scene.image.*
import javafx.scene.layout.*
import javafx.scene.media.*
import javafx.scene.shape.*
import javafx.scene.text.*
import javafx.scene.web.*
import kotlin.contracts.*

@InUi
inline fun Pane.accordion(
        @InUi action: ScopedViewDsl<Accordion>
): Accordion {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(Accordion())
            .apply(action)

}

@InUi
inline fun Pane.anchorPane(
        @InUi action: ScopedViewDsl<AnchorPane>
): AnchorPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            AnchorPane()).apply(action)
}

@InUi
inline fun Pane.borderPane(
        @InUi action: ScopedViewDsl<BorderPane>
): BorderPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            BorderPane()).apply(action)
}

@InUi
inline fun Pane.button(text: String): Button = addToFrontF(Button(text))

/**
 * Like the general constructor DSL, but also adds the newly created button to the children (at the end)
 * @receiver Pane
 * @param text String
 * @param action Button.() -> Unit
 * @return Button
 */
@InUi
inline fun Pane.button(
        text: String,
        @InUi action: ScopedViewDsl<Button>
): Button {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(Button(text)).apply(action)
}


@InUi
inline fun Pane.buttonBar(
        @InUi action: ScopedViewDsl<ButtonBar>
): ButtonBar {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            ButtonBar()).apply(action)
}

@InUi
inline fun Pane.checkBox(
        @InUi action: ScopedViewDsl<CheckBox>
): CheckBox {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            CheckBox()).apply(action)
}

@InUi
inline fun <T> Pane.choiceBox(
        @InUi action: ScopedViewDsl<ChoiceBox<T>>
): ChoiceBox<T> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            ChoiceBox<T>()).apply(action)
}

@InUi
inline fun Pane.colorPicker(
        @InUi action: ScopedViewDsl<ColorPicker>
): ColorPicker {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            ColorPicker()).apply(action)
}

@InUi
inline fun <T> Pane.comboBox(
        @InUi action: ScopedViewDsl<ComboBox<T>>
): ComboBox<T> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            ComboBox<T>()).apply(action)
}

@InUi
inline fun Pane.datePicker(
        @InUi action: ScopedViewDsl<DatePicker>
): DatePicker {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            DatePicker()).apply(action)
}

@InUi
inline fun Pane.flowPane(
        @InUi action: ScopedViewDsl<FlowPane>
): FlowPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            FlowPane()).apply(action)
}

@InUi
inline fun Pane.gridPane(
        @InUi action: ScopedViewDsl<GridPane>
): GridPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            GridPane()).apply(action)
}

@InUi
inline fun Pane.group(
        @InUi action: ScopedViewDsl<Group>
): Group {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            Group()).apply(action)
}

@InUi
inline fun Pane.hBox(
        @InUi action: ScopedViewDsl<HBox>
): HBox {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            HBox()).apply(action)
}

@InUi
inline fun Pane.htmlEditor(
        action: ScopedViewDsl<HTMLEditor>
): HTMLEditor {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            HTMLEditor()).apply(action)
}

@InUi
inline fun Pane.hyperlink(
        action: ScopedViewDsl<Hyperlink>
): Hyperlink {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            Hyperlink()).apply(action)
}

@InUi
inline fun Pane.imageView(
        @InUi action: ScopedViewDsl<ImageView>
): ImageView {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            ImageView()).apply(action)
}

@InUi
inline fun Pane.label(
        @InUi action: ScopedViewDsl<Label>
): Label {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            Label()).apply(action)
}

@InUi
inline fun <T> Pane.listView(
        @InUi action: ScopedViewDsl<ListView<T>>
): ListView<T> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            ListView<T>()).apply(action)
}

@InUi
inline fun Pane.mediaView(
        @InUi action: ScopedViewDsl<MediaView>
): MediaView {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            MediaView()).apply(action)
}

@InUi
inline fun Pane.menuBar(
        @InUi action: ScopedViewDsl<MenuBar>
): MenuBar {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            MenuBar()).apply(action)
}

@InUi
inline fun Pane.meshView(
        @InUi action: ScopedViewDsl<MeshView>
): MeshView {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            MeshView()).apply(action)
}

@InUi
inline fun Pane.pagination(
        @InUi action: ScopedViewDsl<Pagination>
): Pagination {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            Pagination()).apply(action)
}

@InUi
inline fun Pane.passwordField(
        @InUi action: ScopedViewDsl<PasswordField>
): PasswordField {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            PasswordField()).apply(action)
}

@InUi
inline fun Pane.progressBar(
        @InUi action: ScopedViewDsl<ProgressBar>
): ProgressBar {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            ProgressBar()).apply(action)
}

@InUi
inline fun Pane.progressIndicator(
        @InUi action: ScopedViewDsl<ProgressIndicator>
): ProgressIndicator {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            ProgressIndicator()).apply(action)
}

@InUi
inline fun Pane.radioButton(
        @InUi action: ScopedViewDsl<RadioButton>
): RadioButton {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            RadioButton()).apply(action)
}

@InUi
inline fun Pane.scrollPane(
        @InUi action: ScopedViewDsl<ScrollPane>
): ScrollPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            ScrollPane()).apply(action)
}

@InUi
inline fun Pane.separator(
        @InUi action: ScopedViewDsl<Separator>
): Separator {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            Separator()).apply(action)
}

@InUi
inline fun Pane.slider(
        @InUi action: ScopedViewDsl<Slider>
): Slider {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            Slider()).apply(action)
}

@InUi
inline fun <T> Pane.spinner(
        @InUi action: ScopedViewDsl<Spinner<T>>
): Spinner<T> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            Spinner<T>()).apply(action)
}

@InUi
inline fun Pane.splitPane(
        @InUi action: ScopedViewDsl<SplitPane>
): SplitPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            SplitPane()).apply(action)
}

@InUi
inline fun Pane.stackPane(
        @InUi action: ScopedViewDsl<StackPane>
): StackPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            StackPane()).apply(action)
}

@InUi
inline fun <T> Pane.tableView(
        @InUi action: ScopedViewDsl<TableView<T>>
): TableView<T> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            TableView<T>()).apply(action)
}

@InUi
inline fun Pane.tabPane(
        @InUi action: ScopedViewDsl<TabPane>
): TabPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            TabPane()).apply(action)
}

@InUi
inline fun Pane.text(
        action: ScopedViewDsl<Text>
): Text {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            Text()).apply(action)
}

@InUi
inline fun Pane.textArea(
        action: ScopedViewDsl<TextArea>
): TextArea {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            TextArea()).apply(action)
}

@InUi
inline fun Pane.textField(
        action: ScopedViewDsl<TextField>
): TextField {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            TextField()).apply(action)
}

@InUi
inline fun Pane.textFlow(
        action: ScopedViewDsl<TextFlow>
): TextFlow {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            TextFlow()).apply(action)
}

@InUi
inline fun Pane.tilePane(
        action: ScopedViewDsl<TilePane>
): TilePane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            TilePane()).apply(action)
}

@InUi
inline fun Pane.titledPane(
        action: ScopedViewDsl<TitledPane>
): TitledPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            TitledPane()).apply(action)
}

@InUi
inline fun Pane.toggleButton(
        action: ScopedViewDsl<ToggleButton>
): ToggleButton {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            ToggleButton()).apply(action)
}

@InUi
inline fun Pane.toolBar(
        action: ScopedViewDsl<ToolBar>
): ToolBar {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            ToolBar()).apply(action)
}

@InUi
inline fun <T> Pane.treeTableView(
        action: ScopedViewDsl<TreeTableView<T>>
): TreeTableView<T> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            TreeTableView<T>()).apply(action)
}

@InUi
inline fun <T> Pane.treeView(
        action: ScopedViewDsl<TreeView<T>>
): TreeView<T> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            TreeView<T>()).apply(action)
}

@InUi
inline fun Pane.vBox(
        action: ScopedViewDsl<VBox>
): VBox {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            VBox()).apply(action)
}

@InUi
inline fun Pane.webView(
        action: ScopedViewDsl<WebView>
): WebView {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(
            WebView()).apply(action)
}

@InUi
inline fun Pane.space(
        @InUi spacing: Double
) {
    addToFront(SpaceView(spacing))
}

