package com.artkodec.uv_control.views.fragments;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.artkodec.uv_control.R;
import com.artkodec.uv_control.model.OpenWeatherEntity;
import com.artkodec.uv_control.model.UserEntity;
import com.artkodec.uv_control.presenter.CurrentWeatherPresenter;
import com.artkodec.uv_control.services.GPSTracker;
import com.artkodec.uv_control.utils.SessionManager;
import com.artkodec.uv_control.utils.WS;
import com.artkodec.uv_control.views.adapters.PlaceAutocompleteAdapter;
import com.artkodec.uv_control.views.contracts.CurrentWeatherContract;
import com.artkodec.uv_control.views.dialogs.TipsUVDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import org.json.JSONException;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Created by junior on 01/06/16.
 */
public class CurrentWeatherFragment extends Fragment implements CurrentWeatherContract.View,
        GoogleApiClient.OnConnectionFailedListener {

    public static final String TAG = "MainActivity";
    @BindView(R.id.ic_favorite)
    ImageButton icFavorite;
    @BindView(R.id.ib_tips)
    ImageButton ibTips;
    @BindView(R.id.frame_load)
    FrameLayout frameLoad;
    @BindView(R.id.pb_load)
    ProgressBar pbLoad;
    @BindView(R.id.tv_load)
    TextView tvLoad;
    @BindView(R.id.title_country)
    TextView titleCountry;
    @BindView(R.id.iv_weather)
    ImageView ivWeather;
    @BindView(R.id.temp_min)
    TextView tempMin;
    @BindView(R.id.temp_max)
    TextView tempMax;
    @BindView(R.id.tv_uv_index)
    TextView tvUvIndex;
    @BindView(R.id.tv_uv_mode)
    TextView tvUvMode;
    @BindView(R.id.tv_temp_prom)
    TextView tvTempProm;
    @BindView(R.id.tv_wind_speed)
    TextView tvWindSpeed;
    @BindView(R.id.tv_rain)
    TextView tvRain;
    @BindView(R.id.tv_humedity)
    TextView tvHumedity;
    @BindView(R.id.sub_title_country)
    TextView subTitleCountry;
    @BindView(R.id.tv_uv_title)
    TextView tvUvTitle;
    private UserEntity userEntity;
    private CurrentWeatherContract.Presenter mPresenter;
    private boolean isLikeMe = false;
    private GPSTracker gpsTracker;
    Location location;
    protected GoogleApiClient mGoogleApiClient;
    private PlaceAutocompleteAdapter mAdapter;
    private String LocationTitle;
    private String LocationSubtitle;
    private OpenWeatherEntity openWeatherEntity;
    private SessionManager sessionManager;
    private static final LatLngBounds BOUNDS_GREATER_SYDNEY = new LatLngBounds(
            new LatLng(-34.041458, 150.790100), new LatLng(-33.682247, 151.383362));

    public static CurrentWeatherFragment newInstance() {
        CurrentWeatherFragment fragment = new CurrentWeatherFragment();
        return fragment;
    }

    public CurrentWeatherFragment() {

    }


    @Override
    public void onResume() {
        super.onResume();
        setLocation();
        if (location != null) {
            mPresenter.start(location.getLatitude(), location.getLongitude());
        } else {
            Toast.makeText(getContext(), "No se puedo obtener la localización actual", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new CurrentWeatherPresenter(this, getContext());
        gpsTracker = new GPSTracker(getContext());
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .enableAutoManage(this.getActivity(), 0 /* clientId */, this)
                .addApi(Places.GEO_DATA_API)
                .build();


        // mAutocompleteView.setOnItemClickListener(mAutocompleteClickListener);

        // Retrieve the TextViews that will display details and attributions of the selected place.


        // Set up the adapter that will retrieve suggestions from the Places Geo Data API that cover
        // the entire world.
        mAdapter = new PlaceAutocompleteAdapter(this.getContext(), mGoogleApiClient, BOUNDS_GREATER_SYDNEY,
                null);

        sessionManager= new SessionManager(getContext());
        //  mAutocompleteView.setAdapter(mAdapter);

        // Set up the 'clear text' button that clears the text in the autocomplete view


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.layout_current_weather, container, false);
        ButterKnife.bind(this, view);
        //frameLoad.setVisibility(View.VISIBLE);
        return view;
    }


    public void setLocation() {
        location = gpsTracker.getLocation();
    }

    /* @Override
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
            switch (requestCode) {
                case UtilityClass.MY_PERMISSIONS_REQUEST_LOCATION:
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                            location=gpsTracker.getLocation();
                    } else {
                        //code for deny
                    }
                    break;
            }
        }*/
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        /*gpsTracker = new GPSTracker(getContext());
        boolean result = UtilityClass.checkPermissionLocation(getContext());

        if(result){
            location=gpsTracker.getLocation();
        }else{

        }*/


    }


    public void iLiked() {
        if (isLikeMe) {
            icFavorite.setImageResource(R.drawable.ic_action_star_0_yellow);
            isLikeMe = false;
        } else {
            icFavorite.setImageResource(R.drawable.ic_action_star_10_yellow);
            isLikeMe = true;
        }
    }


    @Override
    public void showWeather(OpenWeatherEntity openWeatherEntity) {
        titleCountry.setText(openWeatherEntity.getName() + " - " + openWeatherEntity.getSys().getCountry());
        tempMax.setText(new BigDecimal((Double.parseDouble(openWeatherEntity.getMain().getTemp_max()) - 273.15)).setScale(2, RoundingMode.CEILING) + " C° max");
        tempMin.setText(new BigDecimal((Double.parseDouble(openWeatherEntity.getMain().getTemp_min()) - 273.15)).setScale(2, RoundingMode.DOWN) + " C° min");
        tvTempProm.setText(new BigDecimal((Double.parseDouble(openWeatherEntity.getMain().getTemp()) - 273.15)).setScale(0, RoundingMode.DOWN) + " C°");
        tvWindSpeed.setText(new BigDecimal((Double.parseDouble(openWeatherEntity.getWind().getSpeed()) * (3.6))).setScale(2, RoundingMode.DOWN) + " Km/H");
        tvRain.setText(new BigDecimal((Double.parseDouble(openWeatherEntity.getClouds().getAll()))).setScale(0, RoundingMode.DOWN) + " %");
        tvHumedity.setText(new BigDecimal((Double.parseDouble(openWeatherEntity.getMain().getHumidity()))).setScale(0, RoundingMode.DOWN) + " %");

        Glide.with(this).load(WS.url_icon + openWeatherEntity.getWeather().get(0).getIcon() + ".png")
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(ivWeather);


        if (openWeatherEntity.getWeatherIndexUVEntity() != null) {
            tvUvIndex.setText("" + openWeatherEntity.getWeatherIndexUVEntity().getData());
            if ((0 <= openWeatherEntity.getWeatherIndexUVEntity().getData())
                    && (openWeatherEntity.getWeatherIndexUVEntity().getData() <= 2.9)) {
                tvUvMode.setText("Bajo");
                tvUvMode.setTextColor(getResources().getColor(R.color.green));
                tvUvIndex.setTextColor(getResources().getColor(R.color.green));
                tvUvTitle.setTextColor(getResources().getColor(R.color.green));
            }
            if ((2.9 < openWeatherEntity.getWeatherIndexUVEntity().getData())
                    && (openWeatherEntity.getWeatherIndexUVEntity().getData() <= 5.9)) {
                tvUvMode.setText("Moderado");
                tvUvMode.setTextColor(getResources().getColor(R.color.yellow));
                tvUvIndex.setTextColor(getResources().getColor(R.color.yellow));
                tvUvTitle.setTextColor(getResources().getColor(R.color.yellow));
            }
            if ((6 < openWeatherEntity.getWeatherIndexUVEntity().getData())
                    && (openWeatherEntity.getWeatherIndexUVEntity().getData() <= 7.9)) {
                tvUvMode.setText("Alto");
                tvUvMode.setTextColor(getResources().getColor(R.color.orange));
                tvUvIndex.setTextColor(getResources().getColor(R.color.orange));
                tvUvTitle.setTextColor(getResources().getColor(R.color.orange));
            }
            if ((8 < openWeatherEntity.getWeatherIndexUVEntity().getData())
                    && (openWeatherEntity.getWeatherIndexUVEntity().getData() <= 10.9)) {
                tvUvMode.setText("Muy Alto");
                tvUvMode.setTextColor(getResources().getColor(R.color.red));
                tvUvIndex.setTextColor(getResources().getColor(R.color.red));
                tvUvTitle.setTextColor(getResources().getColor(R.color.red));
            }
            if ((10.9 < openWeatherEntity.getWeatherIndexUVEntity().getData())) {
                tvUvMode.setText("Extremo");
                tvUvMode.setTextColor(getResources().getColor(R.color.violet));
                tvUvIndex.setTextColor(getResources().getColor(R.color.violet));
                tvUvTitle.setTextColor(getResources().getColor(R.color.violet));
            }

        }

        if (LocationSubtitle != null && LocationTitle != null) {
            titleCountry.setText(LocationTitle);
            subTitleCountry.setText(LocationSubtitle);
            LocationSubtitle = null;
            LocationSubtitle = null;
        } else {
            titleCountry.setText(getCountryName(getContext(), location.getLatitude(), location.getLongitude()).getLocality());
            subTitleCountry.setText(getCountryName(getContext(), location.getLatitude(), location.getLongitude()).getCountryName());

        }

        this.openWeatherEntity = openWeatherEntity;

    }

    @Override
    public void start(double latitude, double longitude) {

    }

    @Override
    public void iLike() {

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void sendFavorite(String s) {
        SessionManager sessionManager = new SessionManager(getContext());
        mPresenter.registeFavorite(sessionManager.getUserToken(),
                s,location.getLatitude(),location.getLatitude());
    }


    public void setmPresenter(CurrentWeatherContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showLoad() {
        frameLoad.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoad() {
        frameLoad.setVisibility(View.GONE);
    }

    @Override
    public void setError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }


    @OnClick({R.id.ic_favorite, R.id.ib_tips})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_favorite:

                if(sessionManager.isLogin()){
                    iLiked();
                    DialogForgotPassword tipsUVDialog = new DialogForgotPassword(getContext(),this);
                    tipsUVDialog.show();
                }else{
                    Toast.makeText(getContext(), "Debe iniciar sesión para guardar la zona", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.ib_tips:
                TipsUVDialog alertDialog = null;
                try {
                    alertDialog = new TipsUVDialog(getContext(), openWeatherEntity);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                alertDialog.show();
                break;
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_principal, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
                SearchManager searchManager = (SearchManager) getContext().getSystemService(getContext().SEARCH_SERVICE);
                searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
                AutoCompleteTextView searchText = (AutoCompleteTextView) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);

                searchText.setOnItemClickListener(mAutocompleteClickListener);
                searchText.setAdapter(mAdapter);
                break;
            case R.id.menu_location:
                location = gpsTracker.getLocation();
                mPresenter.start(location.getLatitude(), location.getLongitude());
                break;
        }
        return true;
    }


    private AdapterView.OnItemClickListener mAutocompleteClickListener
            = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            /*
             Retrieve the place ID of the selected item from the Adapter.
             The adapter stores each Place suggestion in a AutocompletePrediction from which we
             read the place ID and title.
              */
            InputMethodManager input = (InputMethodManager) getActivity()
                    .getSystemService(Activity.INPUT_METHOD_SERVICE);
            input.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
            final AutocompletePrediction item = mAdapter.getItem(position);
            final String placeId = item.getPlaceId();
            final CharSequence primaryText = item.getPrimaryText(null);
            Places.GeoDataApi.getPlaceById(mGoogleApiClient, item.getPlaceId())
                    .setResultCallback(new ResultCallback<PlaceBuffer>() {
                        @Override
                        public void onResult(PlaceBuffer places) {
                            if (places.getStatus().isSuccess()) {
                                final Place myPlace = places.get(0);
                                LatLng queried_location = myPlace.getLatLng();
                                //Toast.makeText(getContext(),queried_location.latitude+"", Toast.LENGTH_SHORT).show();
                                // Toast.makeText(getContext(),queried_location.longitude+"", Toast.LENGTH_SHORT).show();
                                if (queried_location != null) {
                                    location.setLatitude(queried_location.latitude);
                                    location.setLongitude(queried_location.longitude);
                                    mPresenter.start(queried_location.latitude, queried_location.longitude);
                                    LocationTitle = (String) primaryText;
                                    LocationSubtitle = (String) item.getSecondaryText(null);
                                    titleCountry.setText(primaryText);
                                } else {
                                    Toast.makeText(getContext(), "No se pudo obtener los datos " +
                                            "del lugar", Toast.LENGTH_SHORT).show();
                                }

                                // Log.v("Latitude is",""+queriedLocation.latitude);
                                //Log.v("Longitude is",""+queriedLocation.longitude);
                            }
                            places.release();
                        }
                    });


            Log.i(TAG, "Autocomplete item selected: " + primaryText);

            /*
             Issue a request to the Places Geo Data API to retrieve a Place object with additional
             details about the place.
              */
            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);


            Log.i(TAG, "Called getPlaceById to get Place details for " + placeId);
        }
    };

    /**
     * Callback for results from a Places Geo Data API query that shows the first place result in
     * the details view on screen.
     */
    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback
            = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                // Request did not complete successfully
                Log.e(TAG, "Place query did not complete. Error: " + places.getStatus().toString());
                places.release();
                return;
            }
            // Get the Place object from the buffer.
            final Place place = places.get(0);


            // Display the third party attributions if set.
            final CharSequence thirdPartyAttribution = places.getAttributions();


            Log.i(TAG, "Place details received: " + place.getName());

            places.release();
        }
    };


    /**
     * Called when the Activity could not connect to Google Play services and the auto manager
     * could resolve the error automatically.
     * In this case the API is not available and notify the user.
     *
     * @param connectionResult can be inspected to determine the cause of the failure
     */
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        Log.e(TAG, "onConnectionFailed: ConnectionResult.getErrorCode() = "
                + connectionResult.getErrorCode());

        // TODO(Developer): Check error code and notify the user of error state and resolution.
        Toast.makeText(this.getContext(),
                "Could not connect to Google API Client: Error " + connectionResult.getErrorCode(),
                Toast.LENGTH_SHORT).show();
    }

    public static Address getCountryName(Context context, double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            Address result;

            if (addresses != null && !addresses.isEmpty()) {
                return addresses.get(0);
            }
            return null;
        } catch (IOException ignored) {
            //do something
        }
        return null;
    }
}
