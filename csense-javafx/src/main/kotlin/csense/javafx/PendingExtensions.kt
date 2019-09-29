package csense.javafx

//
//fun <ViewBinding : LoadViewAble<out Parent>,
//        ViewToShow,
//        Dout> BaseView<*, ViewBinding>.presentView(
//    viewToShow: ViewToShow,
//    configureStage: FunctionUnit<Stage>? = null,
//    uiAction: InUiUpdateInputScope<ViewBinding, Dout?>
//): Job where ViewToShow : BaseView<*, out LoadViewAble<out Parent>>, ViewToShow : OutputViewAble<Dout> =
//    backgroundToUi(computeAction = {
//        suspendCancellableCoroutine<Deferred<Dout?>> { continuation ->
//            viewToShow.start()
//            viewToShow.presentThisAsModal(null, configureStage)
//            viewToShow.inUi {
//                currentStage?.setOnHiding {
//                    continuation.resume(viewToShow.createResultAsync())
//                }
//            }
//        }.await()
//    }, uiAction = uiAction)
