# Bouncing
The concept is that doing work in a UI context is quite difficult to do safely.

With the introduction of coroutines from kotlin, this became even more "difficult" in a sense, 
since now you can strife to write your async code in a synchronise fashion, with the benifit of it "just working";
However as is the case with UI's, they are generally unpredictable.

To make this more apparent lets conceptualize the following scenario:

```kotlin

fun CoroutineScope.fetchAndShow() = launch(Dispatchers.Main){
    val frombackground = MyApiService.fetch() //suspendable call
    if(fromBackground.isOk()){
        updateUI(fromBackground)
    } else {
        logError(fromBackground)
    }
}


``` 

So the great thing about coroutines here is that we have a nice flow.
The equally bad thing is that we have the same issue that callbacks also have: 

`is the ui really accessible at the time we are done in our computation ?`

To emphasize this even further, we are to image all accesses to a "dead" UI is consider a crash or alike (this does not nessaryly happen for all UI toolkits and not always for JavaFx)

This leaves us with the following scenarios; 

* The golden, the ui is there and no matter what branch, all is well
* The fallen, the ui is there when executing, but gone before the fetch is done

The second scenario will then crash; not because of the threading (since coroutines will make sure we are on the main thread)
but the fact that the actions happen on a UI lifecycle that is not tied to the resumption / activation of the suspention point. (that is , when we get back to this function after a suspension point).
Now the first argument is to simply tie the coroutine scope to the UI lifecycle; which solves most of the problem (as long as it is not a NonCancelableContext), 
but even so, if we have say a "pause" lifecycle this will STILL cause issues (as an example, see android).

So to solve this, we are to try and limit the accesibility to the dangerous UI while keeping the niceness of both structured coroutines, and while making background work a brezze.
To some extend we are also to try and limit the possibility of cross thread access to the UI; 
while its not possible in Kotlin to limit something like
```kotlin
fun breakingUI(){
    val someUIElement: Label = myView
    launch(Dispatchers.Default){
        val awesomeText = doSomeHeavyComputation()
        someUIElement.text = awesomeText // <- this  
    }
}
```
(which would require some sort of language feature to limit assignments / escaping of variables)
we can at least cheat a bit with the use of the DSL marker, to avoid accidental accesses, and to a large degree make it impossible to access even though allowed from the language; 

This is the Bouncing UI / background concept; 

We will be starting in the UI , eg "onReady".
then we want to do some "interresting" non ui work, and are +- forced to do something like so

```kotlin
//imagine we have a view with a property called "label" as a Label
//this will be in a property called binding (however invisible to this viewcontroller class).


fun onReady(){
    inBackground(){
        val awesomeText:String = doSomeHeavyComputation() //suspendable
        //now trying to access the binding, will cause 2 issues to kick in
        // 1 is that the DSL marker prohibits indirect access to other levels 
        // and since we in the background implicit reciever have a field named
        // binding, with a deprecation tag, (set to error)
        //we effectively limited the ability to access the outer receviver; which there are no autocomplete for , and no direct indication of.
        //thus to access it we either have to store it in a variable (bad)
        //or even worse, to explicitly state that we want the implicit receiver property(just as bad)
        //the "right" way would be to do the following
        inUI(awesomeText){
            binding.label.text = awesomeText
        }
    }
}

```
(see the BaseView classes for this implementation)



