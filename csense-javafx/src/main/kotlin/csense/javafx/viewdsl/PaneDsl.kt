@file:Suppress("NOTHING_TO_INLINE", "UNUSED")
@file:InUiContext

package csense.javafx.viewdsl

import csense.kotlin.annotations.threading.InUi
import csense.javafx.extensions.parent.addToFront
import csense.javafx.widgets.SpaceView
import csense.kotlin.EmptyFunctionResult
import csense.kotlin.annotations.threading.InUiContext
import javafx.scene.Group
import javafx.scene.Node
import javafx.scene.control.*
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.scene.media.MediaView
import javafx.scene.shape.MeshView
import javafx.scene.text.Text
import javafx.scene.text.TextFlow
import javafx.scene.web.HTMLEditor
import javafx.scene.web.WebView
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract


@InUi
inline fun <T : Node> Pane.createAndAdd(@InUi create: EmptyFunctionResult<T>): T {
    contract {
        callsInPlace(create, InvocationKind.EXACTLY_ONCE)
    }
    return create().apply(::addToFront)
}

@InUi
inline fun Pane.accordion(
        @InUi crossinline action: ScopedViewDsl<Accordion>
): Accordion {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        Accordion().apply(action)
    }
}

@InUi
inline fun Pane.anchorPane(
        @InUi crossinline action: ScopedViewDsl<AnchorPane>
): AnchorPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        AnchorPane().apply(action)
    }
}

@InUi
inline fun Pane.borderPane(
        @InUi crossinline action: ScopedViewDsl<BorderPane>
): BorderPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        BorderPane().apply(action)
    }
}


@InUi
inline fun Pane.button(text: String): Button = createAndAdd { Button(text) }

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
        @InUi crossinline action: ScopedViewDsl<Button>
): Button {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd { Button(text).apply(action) }
}

@InUi
inline fun Pane.buttonBar(
        @InUi crossinline action: ScopedViewDsl<ButtonBar>
): ButtonBar {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        ButtonBar().apply(action)
    }
}

@InUi
inline fun Pane.checkBox(
        @InUi crossinline action: ScopedViewDsl<CheckBox>
): CheckBox {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        CheckBox().apply(action)
    }
}

@InUi
inline fun <T> Pane.choiceBox(
        @InUi crossinline action: ScopedViewDsl<ChoiceBox<T>>
): ChoiceBox<T> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        ChoiceBox<T>().apply(action)
    }
}

@InUi
inline fun Pane.colorPicker(
        @InUi crossinline action: ScopedViewDsl<ColorPicker>
): ColorPicker {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        ColorPicker().apply(action)
    }
}

@InUi
inline fun <T> Pane.comboBox(
        @InUi crossinline action: ScopedViewDsl<ComboBox<T>>
): ComboBox<T> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        ComboBox<T>().apply(action)
    }
}

@InUi
inline fun Pane.datePicker(
        @InUi crossinline action: ScopedViewDsl<DatePicker>
): DatePicker {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        DatePicker().apply(action)
    }
}

@InUi
inline fun Pane.flowPane(
        @InUi crossinline action: ScopedViewDsl<FlowPane>
): FlowPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        FlowPane().apply(action)
    }
}

@InUi
inline fun Pane.gridPane(
        @InUi crossinline action: ScopedViewDsl<GridPane>
): GridPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        GridPane().apply(action)
    }
}

@InUi
inline fun Pane.group(
        @InUi crossinline action: ScopedViewDsl<Group>
): Group {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        Group().apply(action)
    }
}

@InUi
inline fun Pane.hBox(
        @InUi crossinline action: ScopedViewDsl<HBox>
): HBox {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        HBox().apply(action)
    }
}

@InUi
inline fun Pane.htmlEditor(
        crossinline action: ScopedViewDsl<HTMLEditor>
): HTMLEditor {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        HTMLEditor().apply(action)
    }
}

@InUi
inline fun Pane.hyperlink(
        crossinline action: ScopedViewDsl<Hyperlink>
): Hyperlink {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        Hyperlink().apply(action)
    }
}

@InUi
inline fun Pane.imageView(
        @InUi crossinline action: ScopedViewDsl<ImageView>
): ImageView {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        ImageView().apply(action)
    }
}

@InUi
inline fun Pane.label(
        @InUi crossinline action: ScopedViewDsl<Label>
): Label {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        Label().apply(action)
    }
}

@InUi
inline fun <T> Pane.listView(
        @InUi crossinline action: ScopedViewDsl<ListView<T>>
): ListView<T> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        ListView<T>().apply(action)
    }
}

@InUi
inline fun Pane.mediaView(
        @InUi crossinline action: ScopedViewDsl<MediaView>
): MediaView {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        MediaView().apply(action)
    }
}

@InUi
inline fun Pane.menuBar(
        @InUi crossinline action: ScopedViewDsl<MenuBar>
): MenuBar {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        MenuBar().apply(action)
    }
}

@InUi
inline fun Pane.meshView(
        @InUi crossinline action: ScopedViewDsl<MeshView>
): MeshView {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        MeshView().apply(action)
    }
}

@InUi
inline fun Pane.pagination(
        @InUi crossinline action: ScopedViewDsl<Pagination>
): Pagination {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        Pagination().apply(action)
    }
}

@InUi
inline fun Pane.passwordField(
        @InUi crossinline action: ScopedViewDsl<PasswordField>
): PasswordField {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        PasswordField().apply(action)
    }
}

@InUi
inline fun Pane.progressBar(
        @InUi crossinline action: ScopedViewDsl<ProgressBar>
): ProgressBar {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        ProgressBar().apply(action)
    }
}

@InUi
inline fun Pane.progressIndicator(
        @InUi crossinline action: ScopedViewDsl<ProgressIndicator>
): ProgressIndicator {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        ProgressIndicator().apply(action)
    }
}

@InUi
inline fun Pane.radioButton(
        @InUi crossinline action: ScopedViewDsl<RadioButton>
): RadioButton {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        RadioButton().apply(action)
    }
}

@InUi
inline fun Pane.scrollPane(
        @InUi crossinline action: ScopedViewDsl<ScrollPane>
): ScrollPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        ScrollPane().apply(action)
    }
}

@InUi
inline fun Pane.separator(
        @InUi crossinline action: ScopedViewDsl<Separator>
): Separator {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        Separator().apply(action)
    }
}

@InUi
inline fun Pane.slider(
        @InUi crossinline action: ScopedViewDsl<Slider>
): Slider {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        Slider().apply(action)
    }
}

@InUi
inline fun <T> Pane.spinner(
        @InUi crossinline action: ScopedViewDsl<Spinner<T>>
): Spinner<T> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        Spinner<T>().apply(action)
    }
}

@InUi
inline fun Pane.splitPane(
        @InUi crossinline action: ScopedViewDsl<SplitPane>
): SplitPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        SplitPane().apply(action)
    }
}

@InUi
inline fun Pane.stackPane(
        @InUi crossinline action: ScopedViewDsl<StackPane>
): StackPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        StackPane().apply(action)
    }
}

@InUi
inline fun <T> Pane.tableView(
        @InUi crossinline action: ScopedViewDsl<TableView<T>>
): TableView<T> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        TableView<T>().apply(action)
    }
}

@InUi
inline fun Pane.tabPane(
        @InUi crossinline action: ScopedViewDsl<TabPane>
): TabPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        TabPane().apply(action)
    }
}

@InUi
inline fun Pane.text(
        crossinline action: ScopedViewDsl<Text>
): Text {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        Text().apply(action)
    }
}

@InUi
inline fun Pane.textArea(
        crossinline action: ScopedViewDsl<TextArea>
): TextArea {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        TextArea().apply(action)
    }
}

@InUi
inline fun Pane.textField(
        crossinline action: ScopedViewDsl<TextField>
): TextField {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        TextField().apply(action)
    }
}

@InUi
inline fun Pane.textFlow(
        crossinline action: ScopedViewDsl<TextFlow>
): TextFlow {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        TextFlow().apply(action)
    }
}

@InUi
inline fun Pane.tilePane(
        crossinline action: ScopedViewDsl<TilePane>
): TilePane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        TilePane().apply(action)
    }
}

@InUi
inline fun Pane.titledPane(
        crossinline action: ScopedViewDsl<TitledPane>
): TitledPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        TitledPane().apply(action)
    }
}

@InUi
inline fun Pane.toggleButton(
        crossinline action: ScopedViewDsl<ToggleButton>
): ToggleButton {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        ToggleButton().apply(action)
    }
}

@InUi
inline fun Pane.toolBar(
        crossinline action: ScopedViewDsl<ToolBar>
): ToolBar {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        ToolBar().apply(action)
    }
}

@InUi
inline fun <T> Pane.treeTableView(
        crossinline action: ScopedViewDsl<TreeTableView<T>>
): TreeTableView<T> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        TreeTableView<T>().apply(action)
    }
}

@InUi
inline fun <T> Pane.treeView(
        crossinline action: ScopedViewDsl<TreeView<T>>
): TreeView<T> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        TreeView<T>().apply(action)
    }
}

@InUi
inline fun Pane.vBox(
        crossinline action: ScopedViewDsl<VBox>
): VBox {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        VBox().apply(action)
    }
}

@InUi
inline fun Pane.webView(
        crossinline action: ScopedViewDsl<WebView>
): WebView {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        WebView().apply(action)
    }
}

@InUi
inline fun Pane.space(
        @InUi spacing: Double
) {
    addToFront(SpaceView(spacing))
}

