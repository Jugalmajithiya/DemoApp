package com.example.demoapplication.ui.address

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Point
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.demoapplication.R
import com.example.demoapplication.databinding.FragmentAddNewAddressBinding
import com.example.demoapplication.instanceApp
import com.example.demoapplication.utils.Constant
import com.example.demoapplication.utils.Constant.LAT
import com.example.demoapplication.utils.Constant.LONG
import com.example.demoapplication.utils.Constant.MAP_VIEW_BUNDLE_KEY
import com.example.demoapplication.utils.base.BaseFragment
import com.example.demoapplication.utils.checkStringValue
import com.example.demoapplication.utils.replaceFragment
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.wrappers.InstantApps
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import java.io.IOException


class AddNewAddressFragment : BaseFragment(), OnMapReadyCallback,
    GoogleMap.OnCameraMoveStartedListener,
    GoogleMap.OnMarkerClickListener, GoogleMap.OnCameraIdleListener {
    lateinit var binding: FragmentAddNewAddressBinding
    private var mGoogleMap: GoogleMap? = null
    private  var currentLocation = LatLng(LAT,LONG)
    private var place: Place? = null

    override fun bind(container: ViewGroup?): View? {
        binding = FragmentAddNewAddressBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView() {
        binding.ivBack.setOnClickListener(this)
        binding.ivCurrentLocation.setOnClickListener(this)
        binding.tvSearch.setOnClickListener(this)
//        binding.mapView.onCreate(savedInstanceState)
        try {
            MapsInitializer.initialize(this.requireActivity())
        } catch (e: GooglePlayServicesNotAvailableException) {
            e.printStackTrace()
        }
        binding.mapView.getMapAsync(this)
        val REQUIRED_PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.INTERNET
        )
        requestPermissions(REQUIRED_PERMISSIONS, 0)
        cameraUpdate(LatLng(LAT, LONG))
        currentLocation()
    }

    private fun cameraUpdate(location: LatLng) {
        val cameraUpdateFactory = CameraUpdateFactory.newLatLng(location)
        mGoogleMap?.moveCamera(cameraUpdateFactory)
    }

    override fun onClickSafe(view: View) {
        when (view.id) {
            R.id.ivBack -> {
                requireActivity().onBackPressed()
            }

            R.id.ivCurrentLocation -> {
                currentLocation()
            }
            R.id.tvSearch -> {
                Places.initialize(instanceApp, Constant.MAP_KEY);
                val addressFields =
                    listOf(
                        Place.Field.ID,
                        Place.Field.NAME,
                        Place.Field.LAT_LNG,
                        Place.Field.ADDRESS
                    )
                val intent =
                    Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.FULLSCREEN,
                        addressFields
                    ).build(requireContext())
                startActivityForResult(intent, Constant.PLACE_PICKER_REQUEST)
//                requireActivity().replaceFragment(R.id.mainContainer, SearchLocationFragment())
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constant.PLACE_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                place = Autocomplete.getPlaceFromIntent(data)

                binding.tvAddressTitle.text = checkStringValue(place?.address, "Address")
//                getMapCameraPos(place?.latLng ?: LatLng(0.0, 0.0))
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR && data != null) {
                val status = Autocomplete.getStatusFromIntent(data)
                binding.tvAddressTitle.text = ""
                showMessage(status.statusMessage!!)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mapView?.let {
            it.onCreate(savedInstanceState)
            it.getMapAsync(this)
        }
    }


    private fun currentLocation(){
        // Check if location permissions are granted
        // Check if location permissions are granted
        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            // Get the last known location
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
            fusedLocationClient.lastLocation
                .addOnSuccessListener(requireActivity(),
                    OnSuccessListener<Location?> { location ->
                        if (location != null) {
                            val latitude = location.latitude
                            val longitude = location.longitude
                            currentLocation = LatLng(latitude, longitude)
                            // Use the currentLocation LatLng as needed
                            // You can move the camera to the current location with:
                            mGoogleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 14f))
                               updateAddress(currentLocation)
                        }
                    })
        }

    }

    private fun updateAddress(currentLocation: LatLng) {
        val geocoder = Geocoder(requireContext()!!)
        try {
            val addresses: List<Address>? = geocoder.getFromLocation(currentLocation.latitude, currentLocation.longitude, 1)
            if (!addresses.isNullOrEmpty()) {
                val address: Address = addresses[0]
                val fullAddress: String = address.getAddressLine(0) // Get the full address

                if (fullAddress.isNotEmpty()){
                    binding.tvAddressTitle.text= fullAddress
                    binding.tvAddressStreet.text= fullAddress
                }

                // You can also get other address details such as locality, country, etc. from the Address object
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        var mapviewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY)
        if (mapviewBundle == null) {
            mapviewBundle = Bundle()
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapviewBundle)
        }
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mGoogleMap = googleMap
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        googleMap.isMyLocationEnabled = true
        configMap()
    }

    private fun configMap() {
        mGoogleMap?.let {
            it.uiSettings?.let { uiSettings ->
                uiSettings.isZoomControlsEnabled = false
                uiSettings.isMapToolbarEnabled = false
            }
            it.setOnCameraMoveStartedListener(this)
            it.moveCamera(CameraUpdateFactory.zoomTo(14f))
            it.setOnMarkerClickListener(this)
            it.setOnCameraIdleListener(this)
            it.addMarker(MarkerOptions().position(LatLng(LAT, LONG)).title("Location"))
        }
    }

    override fun onCameraMoveStarted(reason: Int) {
        when (reason) {
            GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE -> {
//                if (binding.fmPlace.isVisible) {
//                    // Update the markers which are showing in map while start moving the map.
//                    viewModel.updateCurrentMarker()
//                }
            }
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {

        currentLocation = LatLng( marker.position.latitude,  marker.position.longitude)
        // Use the currentLocation LatLng as needed
        // You can move the camera to the current location with:
        mGoogleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 14f))
        return false
    }

    override fun onCameraIdle() {
        try {
            val visibleRegion = mGoogleMap?.projection?.visibleRegion
            val x = mGoogleMap?.projection?.toScreenLocation(visibleRegion?.farRight!!)
            val y = mGoogleMap?.projection?.toScreenLocation(visibleRegion?.nearLeft!!)
            val centerPoint = Point(x!!.x / 2, y!!.y / 2)
            val centerLatLng: LatLng = mGoogleMap?.projection?.fromScreenLocation(centerPoint) ?: LatLng(LAT,LONG)
            updateAddress(centerLatLng)
        } catch (e: Exception) {
           Log.e("Error",e.localizedMessage.toString())
        }

    }


}
