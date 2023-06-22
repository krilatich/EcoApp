package com.startup.feature.map.ui

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.startup.feature.map.R
import com.startup.feature.map.databinding.FragmentMapBinding
import com.startup.feature.map.presentation.MapState
import com.startup.feature.map.presentation.MapViewModel
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun MapScreen() {
    val viewModel: MapViewModel = koinViewModel()
    val context = LocalContext.current
    if (ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) ==
        PackageManager.PERMISSION_GRANTED &&
        ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) ==
        PackageManager.PERMISSION_GRANTED
    ) {
        MapKitFactory.initialize(context)
        MapKitFactory.getInstance().onStart()
        viewModel.getMarkers()

    } else {
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {

                MapKitFactory.initialize(context)
                MapKitFactory.getInstance().onStart()
                viewModel.getMarkers()
            }

        }
    }
    val list = listOf<Pair<Double,Double>>(Pair(56.46966,84.972215),
        Pair(56.470343,84.962615),
        Pair(56.464024,84.94913),
        Pair(56.499145,84.962958),
        Pair(56.4709,84.947588),
        Pair(56.463588,84.957074),
        Pair(56.479405,84.956315),
        Pair(56.462506,84.966333),
        Pair(56.489774,84.965247),
        Pair(56.496668,84.945857),
        Pair(56.494124,84.949415),
        Pair(56.467857,84.974591),
        Pair(56.493143,84.9485),
        Pair(56.470452,84.979854),
        Pair(56.498831,84.942594))


    val uiState by viewModel.uiState.collectAsState(initial = MapState.Content(emptyList()))

    // val binding = FragmentMapBinding.inflate(LayoutInflater.from(LocalContext.current))
    AndroidViewBinding(factory = { layoutInflater: LayoutInflater, viewGroup: ViewGroup, b: Boolean ->
        FragmentMapBinding.inflate(
            LayoutInflater.from(context)
        )
    }, update = {
        mapview.map.move(
            CameraPosition(Point(56.470601, 84.937085), 15.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 0F),
            null
        )
        val imageProvider = ImageProvider.fromResource(context, R.drawable.icon)
        val placemarkTapListener = MapObjectTapListener { _, _ ->
//		Toast.makeText(
//			this.context,
//			"Tapped the point (${point.longitude}, ${point.latitude})", Toast.LENGTH_SHORT
//		).show()
            true
        }
       list.forEach {
            Log.d("marker1", "created")
            val placemark = mapview.map.mapObjects.addPlacemark(
                Point(it.first.toDouble(), it.second.toDouble()), imageProvider
            )
            placemark.addTapListener(placemarkTapListener)
        }
        viewModel.uiState.onEach {

        }.launchIn(GlobalScope)

    }
    )
}

