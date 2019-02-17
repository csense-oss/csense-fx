@file:Suppress("NOTHING_TO_INLINE", "UNUSED")

package csense.javafx.viewdsl

import csense.javafx.extensions.parent.addToFront
import csense.kotlin.EmptyFunctionResult
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
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@UseExperimental(ExperimentalContracts::class)
inline fun <T : Node> Pane.createAndAdd(create: EmptyFunctionResult<T>): T {
    contract {
        callsInPlace(create, InvocationKind.EXACTLY_ONCE)
    }
    return create().apply(::addToFront)
}

@UseExperimental(ExperimentalContracts::class)
inline fun Pane.accordion(
    crossinline action: ScopedViewDsl<Accordion>
): Accordion {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        Accordion().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
inline fun Pane.anchorPane(
    crossinline action: ScopedViewDsl<AnchorPane>
): AnchorPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        AnchorPane().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
inline fun Pane.borderPane(
    crossinline action: ScopedViewDsl<BorderPane>
): BorderPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        BorderPane().apply(action)
    }
}


@UseExperimental(ExperimentalContracts::class)
inline fun Pane.button(text: String): Button = createAndAdd { Button(text) }

/**
 * Like the general constructor DSL, but also adds the newly created button to the children (at the end)
 * @receiver Pane
 * @param text String
 * @param action Button.() -> Unit
 * @return Button
 */
@UseExperimental(ExperimentalContracts::class)
inline fun Pane.button(
    text: String,
    crossinline action: ScopedViewDsl<Button>
): Button {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd { Button(text).apply(action) }
}

@UseExperimental(ExperimentalContracts::class)
inline fun Pane.buttonBar(
    crossinline action: ScopedViewDsl<ButtonBar>
): ButtonBar {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        ButtonBar().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
inline fun Pane.checkBox(
    crossinline action: ScopedViewDsl<CheckBox>
): CheckBox {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        CheckBox().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
inline fun <T> Pane.choiceBox(
    crossinline action: ScopedViewDsl<ChoiceBox<T>>
): ChoiceBox<T> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        ChoiceBox<T>().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
inline fun Pane.colorPicker(
    crossinline action: ScopedViewDsl<ColorPicker>
): ColorPicker {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        ColorPicker().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
inline fun <T> Pane.comboBox(
    crossinline action: ScopedViewDsl<ComboBox<T>>
): ComboBox<T> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        ComboBox<T>().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
inline fun Pane.datePicker(
    crossinline action: ScopedViewDsl<DatePicker>
): DatePicker {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        DatePicker().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
inline fun Pane.flowPane(
    crossinline action: ScopedViewDsl<FlowPane>
): FlowPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        FlowPane().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
inline fun Pane.gridPane(
    crossinline action: ScopedViewDsl<GridPane>
): GridPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        GridPane().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
inline fun Pane.group(
    crossinline action: ScopedViewDsl<Group>
): Group {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        Group().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
inline fun Pane.hBox(
    crossinline action: ScopedViewDsl<HBox>
): HBox {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        HBox().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
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

@UseExperimental(ExperimentalContracts::class)
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

@UseExperimental(ExperimentalContracts::class)
inline fun Pane.imageView(
    crossinline action: ScopedViewDsl<ImageView>
): ImageView {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        ImageView().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
inline fun Pane.label(
    crossinline action: ScopedViewDsl<Label>
): Label {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        Label().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
inline fun <T> Pane.listView(
    crossinline action: ScopedViewDsl<ListView<T>>
): ListView<T> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        ListView<T>().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
inline fun Pane.mediaView(
    crossinline action: ScopedViewDsl<MediaView>
): MediaView {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        MediaView().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
inline fun Pane.menuBar(
    crossinline action: ScopedViewDsl<MenuBar>
): MenuBar {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        MenuBar().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
inline fun Pane.meshView(
    crossinline action: ScopedViewDsl<MeshView>
): MeshView {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        MeshView().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
inline fun Pane.pagination(
    crossinline action: ScopedViewDsl<Pagination>
): Pagination {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        Pagination().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
inline fun Pane.passwordField(
    crossinline action: ScopedViewDsl<PasswordField>
): PasswordField {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        PasswordField().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
inline fun Pane.progressBar(
    crossinline action: ScopedViewDsl<ProgressBar>
): ProgressBar {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        ProgressBar().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
inline fun Pane.progressIndicator(
    crossinline action: ScopedViewDsl<ProgressIndicator>
): ProgressIndicator {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        ProgressIndicator().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
inline fun Pane.radioButton(
    crossinline action: ScopedViewDsl<RadioButton>
): RadioButton {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        RadioButton().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
inline fun Pane.scrollPane(
    crossinline action: ScopedViewDsl<ScrollPane>
): ScrollPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        ScrollPane().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
inline fun Pane.separator(
    crossinline action: ScopedViewDsl<Separator>
): Separator {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        Separator().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
inline fun Pane.slider(
    crossinline action: ScopedViewDsl<Slider>
): Slider {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        Slider().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
inline fun <T> Pane.spinner(
    crossinline action: ScopedViewDsl<Spinner<T>>
): Spinner<T> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        Spinner<T>().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
inline fun Pane.splitPane(
    crossinline action: ScopedViewDsl<SplitPane>
): SplitPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        SplitPane().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
inline fun Pane.stackPane(
    crossinline action: ScopedViewDsl<StackPane>
): StackPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        StackPane().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
inline fun <T> Pane.tableView(
    crossinline action: ScopedViewDsl<TableView<T>>
): TableView<T> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        TableView<T>().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
inline fun Pane.tabPane(
    crossinline action: ScopedViewDsl<TabPane>
): TabPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return createAndAdd {
        TabPane().apply(action)
    }
}

@UseExperimental(ExperimentalContracts::class)
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

@UseExperimental(ExperimentalContracts::class)
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

@UseExperimental(ExperimentalContracts::class)
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

@UseExperimental(ExperimentalContracts::class)
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

@UseExperimental(ExperimentalContracts::class)
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

@UseExperimental(ExperimentalContracts::class)
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

@UseExperimental(ExperimentalContracts::class)
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

@UseExperimental(ExperimentalContracts::class)
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

@UseExperimental(ExperimentalContracts::class)
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

@UseExperimental(ExperimentalContracts::class)
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

@UseExperimental(ExperimentalContracts::class)
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

@UseExperimental(ExperimentalContracts::class)
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

