package com.example.nabilhentati.pidev;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddDoc.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddDoc#newInstance} factory method to
 * create an instance of this fragment.
 */

public class AddDoc extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddDoc() {
    }

    public static AddDoc newInstance(String param1, String param2) {
        AddDoc fragment = new AddDoc();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_add_doc, container, false);
        Button add=(Button)v.findViewById(R.id.BtnAddDoc);
        final EditText title=(EditText) v.findViewById(R.id.BtnName);





        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity());

                mRequestQueue.start();

                String URL = "http://192.168.43.94:18080/ged-web/ged/Document";
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("titleDocument", title.getText().toString());

                JsonObjectRequest request_json = new JsonObjectRequest(URL, new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                //Process os success response
                                Log.i("tessdfsdft", "test");
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.e("Error123: ", error.getMessage());
                    }
                });
                mRequestQueue.add(request_json);


            }
        });
        return v;
    }

}
