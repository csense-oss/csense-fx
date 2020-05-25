package csense.javafx.widgets.list

import csense.javafx.extensions.parent.addToBack
import csense.javafx.extensions.parent.addToFrontF
import csense.javafx.extensions.scene.layout.*
import csense.javafx.styling.*
import csense.javafx.views.base.BaseView
import csense.javafx.views.base.InScope
import csense.kotlin.Function0
import csense.kotlin.ReceiverFunctionUnit
import csense.kotlin.annotations.inheritance.*
import csense.kotlin.extensions.collections.getSafe
import csense.kotlin.extensions.collections.list.removeAtOr
import csense.kotlin.extensions.collections.map.putSubList
import csense.kotlin.extensions.collections.setAll
import csense.kotlin.extensions.toUnit
import javafx.beans.property.SimpleObjectProperty
import javafx.geometry.Orientation
import javafx.scene.Group
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.*
import javafx.scene.control.skin.ListViewSkin
import javafx.scene.control.skin.VirtualFlow
import javafx.scene.layout.*
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

//TODO make me
abstract class BaseSimpleListView<RenderType : SimpleListViewRender<*>>(adapter: SimpleListViewAdapter) {

}

open class SimpleListView(adapter: SimpleListViewAdapter? = null) : ListView<InternalCellSimpleListViewRender<Pane>>() {
    val adapterProperty = SimpleObjectProperty<SimpleListViewAdapter>()
    
    var adapter: SimpleListViewAdapter?
        get() = adapterProperty.value
        set(value) {
            adapterProperty.value = value
            value?.registerToListView(this)
        }
    
    private val BindingCache = SimpleListViewBindingCache()
    
    init {
        setCellFactory {
            RenderListCell(BindingCache, this)
        }
//        setOnScroll { it ->
//            mySkin.myVirtualFlow?.let { flow: VirtualFlow<ListCell<InternalCellSimpleListViewRender<Parent>>> ->
//                val isAtLast = flow.lastVisibleCell == flow.getCell(items.size - 1)
//                println("is at last?: $isAtLast " + "position is at = ${flow.position}")
//            }
//        }
        this.adapter = adapter
    }
    
    override fun createDefaultSkin(): Skin<*> {
        return mySkin.apply {
            myVirtualFlow.myVbar.setOnScroll {
                println("vbar scroll")
            }
        }
    }
    
    /**
     * Linear index
     * @param index Int
     */
    fun getCell(index: Int): Cell<InternalCellSimpleListViewRender<Pane>> {
        return mySkin.myVirtualFlow.getCell(index)
    }
    
    private val mySkin by lazy {
        SimpleListViewSkin(this)
    }
}

inline fun simpleListView(configure: @InScope ReceiverFunctionUnit<SimpleListView> = {}): SimpleListView {
    contract {
        callsInPlace(configure, InvocationKind.EXACTLY_ONCE)
    }
    return SimpleListView().apply(configure)
}

inline fun Pane.simpleListView(configure: @InScope ReceiverFunctionUnit<SimpleListView> = {}): SimpleListView {
    contract {
        callsInPlace(configure, InvocationKind.EXACTLY_ONCE)
    }
    return addToFrontF(SimpleListView()).apply(configure)
}

class SimpleListViewBindingCache(val maxElementsCached: Int = 200) {
    private var currentAmountsOfElements: Int = 0
    private val cache: MutableMap<Int, MutableList<MyGroupNode>> = mutableMapOf()
    
    fun recycle(toRecycle: MyGroupNode) {
        if (currentAmountsOfElements >= maxElementsCached) {
            //cleanup the LRU
            return //do not recycle this .. SIMPLE bad way to limit. we should instead know which one is least accessed and remove most of the overflowing views from there
        }
        cache.putSubList(toRecycle.bindingClassTypeHashCode, toRecycle)
        currentAmountsOfElements += 1
    }
    
    fun getFromCacheOrNull(uiType: Int): MyGroupNode? {
        val lst = cache[uiType] ?: return null
        return lst.removeAtOr(lst.lastIndex, null)?.also { currentAmountsOfElements -= 1 }
    }

//    private class CachedNodes(val lastTakenFrom: Int, )
}

class SimpleListViewSkin(control: SimpleListView) : ListViewSkin<InternalCellSimpleListViewRender<Pane>>(control) {
    
    val myVirtualFlow: SimpleListViewVirtualFlow
        get() = virtualFlow as SimpleListViewVirtualFlow
    
    
    //TODO external "called from constructor", so we can find the "dreaded" null /bad initialization problems.
    @CalledFromConstructor
    override fun createVirtualFlow(): VirtualFlow<ListCell<InternalCellSimpleListViewRender<Pane>>> {
        
        return SimpleListViewVirtualFlow()
    }
    
}


class SimpleListViewVirtualFlow : VirtualFlow<ListCell<InternalCellSimpleListViewRender<Pane>>>() {
    val myVbar: ScrollBar
        get() = vbar
}

class InternalCellSimpleListViewRender<T : Pane>(
        val render: SimpleListViewRender<BaseView<T>>
) {
    var lastRenderedTo: BaseView<T>? = null
    fun renderTo(binding: BaseView<T>, cell: Cell<*>) {
        render.onRender(binding, cell)
        lastRenderedTo = binding
    }
    
    fun createNew(): BaseView<T> {
        return render.createUi()
    }
    
    fun createNewGroupNode(): MyGroupNode {
        return createNew().inMyGroupNode()
    }
    
    fun createNewRendered(cell: Cell<*>): MyGroupNode {
        return createNew().also {
            renderTo(it, cell)
        }.inMyGroupNode()
    }
    
    private fun BaseView<T>.inMyGroupNode(): MyGroupNode {
        return MyGroupNode(render.uiClassTypeHashCode, this)
    }
    
    fun tryRenderTo(graphic: Node?, bindingCache: SimpleListViewBindingCache, cell: Cell<*>): MyGroupNode {
        //is it either there or a valid type ?
        val graphicAsMyGroupNode = graphic as? MyGroupNode
                ?: return createNewRendered(cell)
        
        //can we reuse it ?
        val isBaseView = graphicAsMyGroupNode.bindingClassTypeHashCode == render.uiClassTypeHashCode
        return if (isBaseView) {
            val casted = graphicAsMyGroupNode.binding as BaseView<T>
            renderTo(casted, cell)
            graphicAsMyGroupNode
        } else {
            //use cache. 2 steps; first recycle the current graphics, and then either find one in cache of our type or create a new one.
            bindingCache.recycle(graphicAsMyGroupNode)
            val bindingToUse = bindingCache.getFromCacheOrNull(render.uiClassTypeHashCode) ?: createNewGroupNode()
            renderTo(bindingToUse.binding as BaseView<T>, cell)
            bindingToUse
        }
    }
    
}

class MyGroupNode(val bindingClassTypeHashCode: Int, val binding: BaseView<out Pane>) : Group() {
    init {
        addToBack(binding.root)
    }
}

open class RenderListCell<T : Pane>(val bindingCache: SimpleListViewBindingCache, listView: SimpleListView) : ListCell<InternalCellSimpleListViewRender<T>>() {
    //TODO where the hell is this even coming from ?
    private val innerPadding = 2
    init {
        contentDisplay = ContentDisplay.GRAPHIC_ONLY
        background = null
        border = null
        opaqueInsets = null
        setPadding(0)
        //make the cells fill the width of the listview.
        if (listView.orientation == Orientation.HORIZONTAL) {
            prefHeightProperty.bind(listView.heightProperty.subtract(innerPadding))
        } else {
            prefWidthProperty.bind(listView.widthProperty.subtract(innerPadding))
        }
//        maxWidth = Control.USE_PREF_SIZE
//        maxHeight = Control.USE_PREF_SIZE
    }
    
    override fun updateItem(newRender: InternalCellSimpleListViewRender<T>?, empty: Boolean) {
        text = null
        if (newRender == null || empty) {
            graphic = null
            return
        }
        val myG = newRender.tryRenderTo(graphic, bindingCache, this)
        graphic = myG
        
        if (listView.orientation == Orientation.HORIZONTAL) {
            myG.binding.root.prefWidthProperty.bind(prefWidthProperty.subtract(innerPadding))
            myG.binding.root.prefHeightProperty.bind(listView.heightProperty.subtract(innerPadding))
        } else {
            myG.binding.root.prefHeightProperty.bind(prefHeightProperty.subtract(innerPadding))
            myG.binding.root.prefWidthProperty.bind(listView.widthProperty.subtract(innerPadding))
        }
    }
    
}

abstract class SimpleListViewRender<Ui : BaseView<*>>(
        val uiClass: Class<Ui>
) {
    
    val uiClassTypeHashCode: Int = uiClass.hashCode()
    
    abstract fun createUi(): Ui
    
    /**
     * get called when we are to render to the given Ui
     * @param renderTo Ui
     */
    abstract fun onRender(renderTo: Ui)
    
    /**
     * delegate the UI rendering to the onRender, and provides a hook point for "handling" the
     * real cell in case that is really needed.
     * @param renderTo Ui
     * @param cell Cell<*>
     */
    @SuperCallRequired
    open fun onRender(renderTo: Ui, cell: Cell<*>) {
        onRender(renderTo)
    }
}

abstract class SimpleDelegatingListViewRender<Ui : BaseView<*>>(val other: SimpleListViewRender<Ui>) : SimpleListViewRender<Ui>(other.uiClass) {
    override fun createUi(): Ui {
        return other.createUi()
    }
    
    override fun onRender(renderTo: Ui) {
        other.onRender(renderTo)
    }
    
    override fun onRender(renderTo: Ui, cell: Cell<*>) {
        other.onRender(renderTo, cell)
    }
}


data class SimpleListViewAdapterUpdate(
        val inserted: List<SimpleListViewRender<*>>,
        val updated: List<SimpleListViewRender<*>>,
        val removed: List<SimpleListViewRender<*>>)

typealias SimpleListViewAdapterItem = SimpleListViewRender<out BaseView<Parent>>

class SimpleListViewAdapter {
    private var listview: SimpleListView? = null
    private val onDataChangedListeners: MutableList<Function0<SimpleListViewAdapterUpdate>> = mutableListOf()
    private var realItemCount: Int = 0
    private val data: HashMap<Int, MutableList<SimpleListViewAdapterItem>> = hashMapOf()
    
    fun setSection(section: Int, items: List<SimpleListViewAdapterItem>) {
        val list = data[section] ?: mutableListOf<SimpleListViewAdapterItem>().also {
            data[section] = it
        }
        realItemCount += (items.size - list.size)
        list.setAll(items)
        updateListViewItems()
    }
    
    /**
     *
     */
    fun clearAll() {
        //compute delta
        data.clear()
    }
    
    fun removeSection(section: Int) {
    
    }
    
    fun addItems(section: Int, items: List<SimpleListViewAdapterItem>) {
    
    }
    
    fun registerOnChangeListener(listener: Function0<SimpleListViewAdapterUpdate>) {
        onDataChangedListeners += listener
    }
    
    fun unregisterOnChangeListener(listener: Function0<SimpleListViewAdapterUpdate>) {
        onDataChangedListeners -= listener
    }
    
    fun registerToListView(listView: SimpleListView) {
        this.listview = listView
//        listView.
    }
    
    private fun updateListViewItems() = listview?.let {
        val keys = data.keys
        val result = mutableListOf<InternalCellSimpleListViewRender<Pane>>()
        keys.forEach {
            data[it]?.let { list ->
                result += list.map {
                    InternalCellSimpleListViewRender(it as SimpleListViewRender<BaseView<Pane>>)
                }
            }
        }
        it.items.setAll(result)
    }.toUnit()
    
    operator fun get(section: Int, index: Int): SimpleListViewAdapterItem? {
        return data[section]?.getOrNull(index)
    }
    
    fun addItem(section: Int, listOf: SimpleListViewAdapterItem) {
    
    }
    
    val totalItemCount: Int
        get() = realItemCount
}


