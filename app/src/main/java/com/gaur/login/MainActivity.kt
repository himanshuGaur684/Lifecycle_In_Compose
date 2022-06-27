package com.gaur.login

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.gaur.login.ui.theme.LoginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}


@Composable
fun MainScreen() {
    ComposableLifecycle{source,event->
        when(event){
            Lifecycle.Event.ON_CREATE->{ Log.d("TAG", "MainScreen: onCreate")}
            Lifecycle.Event.ON_START->{Log.d("TAG", "MainScreen: ON_START")}
            Lifecycle.Event.ON_RESUME->{Log.d("TAG", "MainScreen: ON_RESUME")}
            Lifecycle.Event.ON_PAUSE->{Log.d("TAG", "MainScreen: ON_PAUSE")}
            Lifecycle.Event.ON_STOP->{Log.d("TAG", "MainScreen: ON_STOP")}
            Lifecycle.Event.ON_DESTROY->{Log.d("TAG", "MainScreen: ON_DESTROY")}
        }


    }


}

@Composable
fun ComposableLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onEvent:(LifecycleOwner,Lifecycle.Event) ->Unit
) {

    DisposableEffect(lifecycleOwner){
        val observer = LifecycleEventObserver{source,event->
            onEvent(source,event)
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }


}










