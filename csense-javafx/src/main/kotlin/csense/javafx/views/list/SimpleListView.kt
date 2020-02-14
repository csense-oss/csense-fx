package csense.javafx.views.list

import csense.javafx.extensions.parent.addToBack
import csense.javafx.views.base.BaseView
import csense.kotlin.Function0
import csense.kotlin.extensions.collections.list.removeAtOr
import csense.kotlin.extensions.collections.map.putSubList
import csense.kotlin.extensions.collections.set
import csense.kotlin.extensions.ifNotNull
import csense.kotlin.extensions.runIfNotNull
import csense.kotlin.extensions.toUnit
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.Group
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.*
import javafx.scene.control.skin.ListViewSkin
import javafx.scene.control.skin.VirtualFlow
import kotlin.math.max


class SimpleListView : ListView<InternalCellSimpleListViewRender<Parent>>() {
    val adapterProperty = SimpleObjectProperty<SimpleListViewAdapter>()

    var adapter: SimpleListViewAdapter?
        get() = adapterProperty.value
        set(value) {
            adapterProperty.value = value
            value?.registerToListView(this)
        }

    private val BindingCache = SimpleListViewBindingCache()

    init {
        setCellFactory { RenderListCell(BindingCache) }
//        setOnScroll { it ->
//            mySkin.myVirtualFlow?.let { flow: VirtualFlow<ListCell<InternalCellSimpleListViewRender<Parent>>> ->
//                val isAtLast = flow.lastVisibleCell == flow.getCell(items.size - 1)
//                println("is at last?: $isAtLast " + "position is at = ${flow.position}")
//            }
//        }
    }

    override fun createDefaultSkin(): Skin<*> {
        return mySkin.apply {
            myVirtualFlow.myVbar.setOnScroll {
                println("vbar scroll")
            }
        }
    }

    private val mySkin by lazy {
        SimpleListViewSkin(this)
    }
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

class SimpleListViewSkin(control: SimpleListView) : ListViewSkin<InternalCellSimpleListViewRender<Parent>>(control) {

    val myVirtualFlow: SimpleListViewVirtualFlow
        get() = virtualFlow as SimpleListViewVirtualFlow


    //TODO "called from constructor", so we can find the "dreaded" null /bad initialization problems.
    override fun createVirtualFlow(): VirtualFlow<ListCell<InternalCellSimpleListViewRender<Parent>>> {
        return SimpleListViewVirtualFlow()
    }

}


class SimpleListViewVirtualFlow : VirtualFlow<ListCell<InternalCellSimpleListViewRender<Parent>>>() {
    val myVbar: ScrollBar
        get() = vbar
}

class InternalCellSimpleListViewRender<T : Parent>(
        val render: SimpleListViewRender<BaseView<T>>
) {
    fun renderTo(binding: BaseView<T>) {
        render.onRender(binding)
    }

    fun createNew(): BaseView<T> {
        println("creating new")
        return render.createUi()
    }

    fun createNewGroupNode(): MyGroupNode {
        return createNew().inMyGroupNode()
    }

    fun createNewRendered(): MyGroupNode {
        return createNew().also {
            renderTo(it)
        }.inMyGroupNode()
    }

    private fun BaseView<T>.inMyGroupNode(): MyGroupNode {
        return MyGroupNode(render.uiClassTypeHashCode, this)
    }

    fun tryRenderTo(graphic: Node?, bindingCache: SimpleListViewBindingCache): MyGroupNode {
        //is it either there or a valid type ?
        val graphicAsMyGroupNode = graphic as? MyGroupNode
                ?: return createNewRendered()

        //can we reuse it ?
        val isBaseView = graphicAsMyGroupNode.bindingClassTypeHashCode == render.uiClassTypeHashCode
        return if (isBaseView) {
            val casted = graphicAsMyGroupNode.binding as BaseView<T>
            renderTo(casted)
            graphicAsMyGroupNode
        } else {
            //use cache. 2 steps; first recycle the current graphics, and then either find one in cache of our type or create a new one.
            bindingCache.recycle(graphicAsMyGroupNode)
            val bindingToUse = bindingCache.getFromCacheOrNull(render.uiClassTypeHashCode) ?: createNewGroupNode()
            renderTo(bindingToUse.binding as BaseView<T>)
            bindingToUse
        }
    }

}

class MyGroupNode(val bindingClassTypeHashCode: Int, val binding: BaseView<*>) : Group() {
    init {
        addToBack(binding.root)
    }
}

open class RenderListCell<T : Parent>(val bindingCache: SimpleListViewBindingCache) : ListCell<InternalCellSimpleListViewRender<T>>() {
    init {
        contentDisplay = ContentDisplay.GRAPHIC_ONLY;
    }

    override fun updateItem(newRender: InternalCellSimpleListViewRender<T>?, empty: Boolean) {
        if (newRender == null || empty) {
            graphic = null
            return
        }
        graphic = newRender.tryRenderTo(graphic, bindingCache)
    }

}

abstract class SimpleListViewRender<Ui : BaseView<*>>(
        uiClass: Class<Ui>
) {

    internal val uiClassTypeHashCode: Int = uiClass.hashCode()

    abstract fun createUi(): Ui

    abstract fun onRender(renderTo: Ui)


}


data class SimpleListViewAdapterUpdate(
        val inserted: List<SimpleListViewRender<*>>,
        val updated: List<SimpleListViewRender<*>>,
        val removed: List<SimpleListViewRender<*>>)

class SimpleListViewAdapter {
    private var listview: SimpleListView? = null
    private val onDataChangedListeners: MutableList<Function0<SimpleListViewAdapterUpdate>> = mutableListOf()
    private var realItemCount: Int = 0
    private val data: HashMap<Int, MutableList<SimpleListViewRender<out BaseView<Parent>>>> = hashMapOf()

    fun setSection(section: Int, items: List<SimpleListViewRender<out BaseView<Parent>>>) {
        val list = data[section] ?: mutableListOf<SimpleListViewRender<out BaseView<Parent>>>().also {
            data[section] = it
        }
        realItemCount += (items.size - list.size)
        list.set(items)
        updateListViewItems()
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
        val result = mutableListOf<InternalCellSimpleListViewRender<Parent>>()
        keys.forEach {
            data[it]?.let { list ->
                result += list.map {
                    InternalCellSimpleListViewRender(it as SimpleListViewRender<BaseView<Parent>>)
                }
            }
        }
        it.items.setAll(result)
    }.toUnit()

    val totalItemCount: Int
        get() = realItemCount
}


