package com.example.nabilhentati.pidev;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

public class DocumentCustomAdapter extends ArrayAdapter<Document> {

	  private int resourceId = 0;
	  private LayoutInflater inflater;
	  public Context mContext;

	  public DocumentCustomAdapter(Context context, int resourceId, List<Document> mediaItems) {
	    super(context, 0, mediaItems);
	    this.resourceId = resourceId;
	    this.mContext = context;
	    inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	  }
	  
	  //ViewHolder Design Pattern
	  static class ViewHolder {
		    public TextView titleText, typeText,isSignedText ;
		    public ImageView image;
		  public Button delete;
		  }
	  
	  @Override
	  public View getView(final int position, View convertView, ViewGroup parent) {
		  View rowView = convertView;
	  
		  //Réutiliser les Views
		  if (rowView == null) {
			rowView = inflater.inflate(resourceId, parent, false);
		  }
		  
		  //Configuration du ViewHolder
		  ViewHolder viewHolder = new ViewHolder();
		  viewHolder.titleText = (TextView) rowView.findViewById(R.id.docTitleItem);
		  viewHolder.typeText = (TextView) rowView.findViewById(R.id.typeDocItem);
		  viewHolder.isSignedText = (TextView) rowView.findViewById(R.id.isSignedItem);
		  viewHolder.delete = (Button) rowView.findViewById(R.id.delete);

		  viewHolder.delete.setOnClickListener(new View.OnClickListener() {
			  @Override
			  public void onClick(final View view) {
				  Log.i("item",getItem(position).toString());
				  RequestQueue mRequestQueue = Volley.newRequestQueue(view.getContext());
				  String url = "http://172.16.162.247:18080/pidev-web/ged/Document/"+getItem(position).getId();

				  mRequestQueue.start();

				  StringRequest dr = new StringRequest(Request.Method.DELETE, url,
						  new Response.Listener<String>()
						  {
							  @Override
							  public void onResponse(String response) {
								  // response
								  Toast.makeText(view.getContext(), response, Toast.LENGTH_LONG).show();

							  }
						  },
						  new Response.ErrorListener()
						  {
							  @Override
							  public void onErrorResponse(VolleyError error) {
								  // error.

							  }
						  }
				  );

				  mRequestQueue.add(dr);

				  ((Activity)view.getContext()).getFragmentManager().beginTransaction().replace(R.id.container,new ListDocFragment()).addToBackStack(null).commit();


			  }
		  });

		  rowView.setTag(viewHolder);
		  
		  //Affecter les données aux Views
		  ViewHolder holder = (ViewHolder) rowView.getTag();
		  Document document = getItem(position);
		  
		  holder.titleText.setText(document.getTitle());
		  holder.typeText.setText(document.getTypeDocu());
		  holder.isSignedText.setText(document.getSigned());
		  notifyDataSetChanged();
		  return rowView;
	  }

	}



