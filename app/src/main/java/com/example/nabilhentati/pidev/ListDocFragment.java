package com.example.nabilhentati.pidev;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ListDocFragment extends Fragment {
    ArrayList<Document> documents;
    ListView listDocuments;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_doc, container, false);
        documents = new ArrayList<>();
        RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity());
        listDocuments = (ListView)view.findViewById(R.id.listDoc);
        mRequestQueue.start();

        String url = "http://172.16.162.247:18080/pidev-web/ged/Document";
        Log.i("link",url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject j = array.getJSONObject(i);
                                Document d = new Document(j.getInt("idDoculent"),
                                        j.getString("typeDocu"),
                                        j.getString("titleDocument"),
                                        j.getString("dateAjoutDocument"),
                                        j.getBoolean("signeeDocument"));
                                documents.add(d);

                                Log.i("contrat" , d.toString());

                            }
                            listDocuments.setAdapter(new DocumentCustomAdapter(getActivity(), R.layout.one_doc_item, documents));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        System.out.println("Erreur "+error.getMessage());
                    }
                });

        mRequestQueue.add(stringRequest);
listDocuments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
});
        return view;
    }
}
