package csense.javafx.views.base

import csense.kotlin.annotations.threading.InBackground


//region Type aliases for the scoping (DSL) like state transistions / threading

//region InUI Scopes
typealias InUiUpdateEmptyScope<ViewBinding> = (@InScope InUiUpdateEmpty<ViewBinding>).() -> Unit

typealias InUiUpdateInputScope<ViewBinding, Input> = (@InScope InUiUpdateInput<ViewBinding, Input>).() -> Unit

typealias InUiUpdateInputOutputScope<ViewBinding, Input, Output> = (@InScope InUiUpdateInput<ViewBinding, Input>).() -> Output

typealias InUiUpdateOutputScope<ViewBinding, Output> = (@InScope InUiUpdateEmpty<ViewBinding>).() -> Output
//endregion

//region OnBackground scopes
typealias InBackgroundEmptyScope<ViewBinding> = suspend (@InScope InBackgroundEmpty<ViewBinding>).() -> Unit

typealias InBackgroundInputScope<ViewBinding, Input> = suspend (@InScope InBackgroundInput<ViewBinding, Input>).() -> Unit
typealias InBackgroundOutputScope<ViewBinding, Output> = suspend (@InScope InBackgroundEmpty<ViewBinding>).() -> Output
typealias InBackgroundInputOutputScope<ViewBinding, Input, Output> = suspend (@InScope InBackgroundInput<ViewBinding, Input>).() -> Output
//endregion


//endregion
