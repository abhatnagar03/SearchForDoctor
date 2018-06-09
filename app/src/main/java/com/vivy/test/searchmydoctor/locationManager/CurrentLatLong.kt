package com.vivy.test.searchmydoctor.locationManager

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import com.vivy.test.searchmydoctor.R
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat.startActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.location.*
import com.vivy.test.searchmydoctor.Utils.Constants.MY_PERMISSIONS_REQUEST_LOCATION


class CurrentLatLong(private var mcontext: Context, private var locationCallback: LocationCallbackListener)
    : GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        ResultCallback<LocationSettingsResult>, LocationListener {

    private var mGoogleApiClient: GoogleApiClient
    private var mLocationManager: LocationManager
    private var mLocation: Location? = null
    private var mLocationRequest: LocationRequest? = null
    private val listener: LocationListener? = this
    private val UPDATE_INTERVAL = (2 * 10000).toLong()  /* 10 secs */
    private val FASTEST_INTERVAL: Long = 2000 /* 2 sec */

    init {
        mGoogleApiClient = GoogleApiClient.Builder(mcontext as Context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build()

        mGoogleApiClient.connect();

        mLocationManager = mcontext.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        checkLocation()
    }

    private fun checkLocation(): Boolean {
        if (!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }

    private fun isLocationEnabled(): Boolean {
        mLocationManager = mcontext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun showAlert() {
        val dialog = AlertDialog.Builder(mcontext as Context)
        dialog.setTitle("Enable Location")
                .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " + "use this app")
                .setPositiveButton("Location Settings", { paramDialogInterface, paramInt ->
                    val myIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    startActivity(mcontext as Activity, myIntent, null)
                })
                .setNegativeButton("Cancel", { paramDialogInterface, paramInt -> })
        dialog.show()
    }

    override fun onConnected(p0: Bundle?) {
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_INTERVAL);

        val fusedLocationProviderClient:
                FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(mcontext as Activity)

        if (ActivityCompat.checkSelfPermission(mcontext as Activity,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(mcontext as Activity,
                            Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                AlertDialog.Builder(mcontext)
                        .setTitle(R.string.title_location_permission)
                        .setMessage(R.string.text_location_permission)
                        .setPositiveButton(R.string.ok, DialogInterface.OnClickListener { dialogInterface, i ->
                            //Prompt the user once explanation has been shown
                            ActivityCompat.requestPermissions(mcontext as Activity,
                                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                                    MY_PERMISSIONS_REQUEST_LOCATION)
                        })
                        .create()
                        .show()


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(mcontext as Activity,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        MY_PERMISSIONS_REQUEST_LOCATION)
            }
        } else {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
                    mLocationRequest, listener);

            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(mcontext as Activity, { location ->
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            mLocation = location;
                            locationCallback.onLocationRecieved(mLocation?.latitude as Double, mLocation?.longitude as Double)
                        }
                    })
        }
    }

    override fun onConnectionSuspended(p0: Int) {
        mGoogleApiClient.connect();
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
    }

    override fun onResult(p0: LocationSettingsResult) {}

    override fun onLocationChanged(location: Location?) {
        if (null != location) {
            locationCallback.onLocationRecieved(location.latitude, location.longitude)
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, listener)
        }
    }
}