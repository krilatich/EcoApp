package com.startup.feature.map.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.startup.feature.map.R
import com.startup.feature.map.databinding.FragmentMapBinding
import com.startup.feature.map.presentation.MapState
import com.startup.feature.map.presentation.MapViewModel
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.runtime.image.ImageProvider
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment : Fragment() {

	private val binding by lazy { FragmentMapBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<MapViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		MapKitFactory.initialize(this.context)
		binding.mapview.map.move(
			CameraPosition(Point(56.470601, 84.937085), 15.0f, 0.0f, 0.0f),
			Animation(Animation.Type.SMOOTH, 0F),
			null
		)

		val imageProvider = ImageProvider.fromResource(this.context, R.drawable.icon)
		viewModel.uiState.onEach { state ->
			if (state is MapState.Content) {
				state.markers.forEach {
					val placemark = binding.mapview.map.mapObjects.addPlacemark(
						Point(it.yPos.toDouble(), it.xPos.toDouble()), imageProvider
					)
					placemark.addTapListener(placemarkTapListener)
				}
			}
		}.launchIn(viewLifecycleOwner.lifecycleScope)

		return binding.root
	}

	private val placemarkTapListener = MapObjectTapListener { _, point ->
//		Toast.makeText(
//			this.context,
//			"Tapped the point (${point.longitude}, ${point.latitude})", Toast.LENGTH_SHORT
//		).show()
		true
	}

	override fun onStart() {
		super.onStart()
		MapKitFactory.getInstance().onStart()
		binding.mapview.onStart()
	}

	override fun onStop() {
		binding.mapview.onStop()
		MapKitFactory.getInstance().onStop()
		super.onStop()
	}
}