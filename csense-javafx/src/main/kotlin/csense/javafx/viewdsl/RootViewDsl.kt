@file:Suppress("NOTHING_TO_INLINE")

package csense.javafx.viewdsl

import javafx.scene.Group
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
inline fun accordion(
    crossinline action: @InViewDsl ScopedViewDsl<Accordion>
): Accordion {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return Accordion().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun anchorPane(
    crossinline action: @InViewDsl ScopedViewDsl<AnchorPane>
): AnchorPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return AnchorPane().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun borderPane(
    crossinline action: @InViewDsl ScopedViewDsl<BorderPane>
): BorderPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return BorderPane().apply(action)
}


/**
 * Like the general constructor DSL, but also adds the newly created button to the children (at the end)
 * @receiver Pane
 * @param text String
 * @param action Button.() -> Unit
 * @return Button
 */
@UseExperimental(ExperimentalContracts::class)
inline fun button(
    text: String,
    crossinline action: @InViewDsl ScopedViewDsl<Button>
): Button {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return Button(text).apply(action)
}

inline fun button(text: String): Button {
    return Button(text)
}


@UseExperimental(ExperimentalContracts::class)
inline fun buttonBar(
    crossinline action: @InViewDsl ScopedViewDsl<ButtonBar>
): ButtonBar {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return ButtonBar().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun checkBox(
    crossinline action: @InViewDsl ScopedViewDsl<CheckBox>
): CheckBox {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return CheckBox().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun <T> choiceBox(
    crossinline action: @InViewDsl ScopedViewDsl<ChoiceBox<T>>
): ChoiceBox<T> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return ChoiceBox<T>().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun colorPicker(
    crossinline action: @InViewDsl ScopedViewDsl<ColorPicker>
): ColorPicker {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return ColorPicker().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun <T> comboBox(
    crossinline action: @InViewDsl ScopedViewDsl<ComboBox<T>>
): ComboBox<T> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return ComboBox<T>().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun datePicker(
    crossinline action: @InViewDsl ScopedViewDsl<DatePicker>
): DatePicker {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return DatePicker().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun flowPane(
    crossinline action: @InViewDsl ScopedViewDsl<FlowPane>
): FlowPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return FlowPane().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun gridPane(
    crossinline action: @InViewDsl ScopedViewDsl<GridPane>
): GridPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return GridPane().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun group(
    crossinline action: @InViewDsl ScopedViewDsl<Group>
): Group {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return Group().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun hBox(
    crossinline action: @InViewDsl ScopedViewDsl<HBox>
): HBox {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return HBox().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun htmlEditor(
    crossinline action: @InViewDsl ScopedViewDsl<HTMLEditor>
): HTMLEditor {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return HTMLEditor().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun hyperlink(
    crossinline action: @InViewDsl ScopedViewDsl<Hyperlink>
): Hyperlink {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return Hyperlink().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun imageView(
    crossinline action: @InViewDsl ScopedViewDsl<ImageView>
): ImageView {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return ImageView().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun label(
    crossinline action: @InViewDsl ScopedViewDsl<Label>
): Label {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return Label().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun <T> listView(
    crossinline action: @InViewDsl ScopedViewDsl<ListView<T>>
): ListView<T> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return ListView<T>().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun mediaView(
    crossinline action: @InViewDsl ScopedViewDsl<MediaView>
): MediaView {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return MediaView().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun menuBar(
    crossinline action: @InViewDsl ScopedViewDsl<MenuBar>
): MenuBar {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return MenuBar().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun meshView(
    crossinline action: @InViewDsl ScopedViewDsl<MeshView>
): MeshView {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return MeshView().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun pagination(
    crossinline action: @InViewDsl ScopedViewDsl<Pagination>
): Pagination {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return Pagination().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun passwordField(
    crossinline action: @InViewDsl ScopedViewDsl<PasswordField>
): PasswordField {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return PasswordField().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun progressBar(
    crossinline action: @InViewDsl ScopedViewDsl<ProgressBar>
): ProgressBar {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return ProgressBar().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun progressIndicator(
    crossinline action: @InViewDsl ScopedViewDsl<ProgressIndicator>
): ProgressIndicator {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return ProgressIndicator().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun radioButton(
    crossinline action: @InViewDsl ScopedViewDsl<RadioButton>
): RadioButton {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return RadioButton().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun scrollPane(
    crossinline action: @InViewDsl ScopedViewDsl<ScrollPane>
): ScrollPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return ScrollPane().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun separator(
    crossinline action: @InViewDsl ScopedViewDsl<Separator>
): Separator {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return Separator().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun slider(
    crossinline action: @InViewDsl ScopedViewDsl<Slider>
): Slider {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return Slider().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun <T> spinner(
    crossinline action: @InViewDsl ScopedViewDsl<Spinner<T>>
): Spinner<T> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return Spinner<T>().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun splitPane(
    crossinline action: @InViewDsl ScopedViewDsl<SplitPane>
): SplitPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return SplitPane().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun stackPane(
    crossinline action: @InViewDsl ScopedViewDsl<StackPane>
): StackPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return StackPane().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun <T> tableView(
    crossinline action: @InViewDsl ScopedViewDsl<TableView<T>>
): TableView<T> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return TableView<T>().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun tabPane(
    crossinline action: @InViewDsl ScopedViewDsl<TabPane>
): TabPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return TabPane().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun text(
    crossinline action: @InViewDsl ScopedViewDsl<Text>
): Text {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return Text().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun textArea(
    crossinline action: @InViewDsl ScopedViewDsl<TextArea>
): TextArea {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return TextArea().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun textField(
    crossinline action: @InViewDsl ScopedViewDsl<TextField>
): TextField {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return TextField().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun textFlow(
    crossinline action: @InViewDsl ScopedViewDsl<TextFlow>
): TextFlow {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return TextFlow().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun tilePane(
    crossinline action: @InViewDsl ScopedViewDsl<TilePane>
): TilePane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return TilePane().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun titledPane(
    crossinline action: @InViewDsl ScopedViewDsl<TitledPane>
): TitledPane {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return TitledPane().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun toggleButton(
    crossinline action: @InViewDsl ScopedViewDsl<ToggleButton>
): ToggleButton {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return ToggleButton().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun toolBar(
    crossinline action: @InViewDsl ScopedViewDsl<ToolBar>
): ToolBar {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return ToolBar().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun <T> treeTableView(
    crossinline action: @InViewDsl ScopedViewDsl<TreeTableView<T>>
): TreeTableView<T> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return TreeTableView<T>().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun <T> treeView(
    crossinline action: @InViewDsl ScopedViewDsl<TreeView<T>>
): TreeView<T> {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return TreeView<T>().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun vBox(
    crossinline action: ScopedViewDsl<VBox>
): VBox {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return VBox().apply(action)
}


@UseExperimental(ExperimentalContracts::class)
inline fun webView(
    crossinline action: @InViewDsl ScopedViewDsl<WebView>
): WebView {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return WebView().apply(action)
}


