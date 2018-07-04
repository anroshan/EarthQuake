package com.example.anroshan.earthquake;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<ListEntry> {

//    private LayoutInflater inflater;
//    private ArrayList<ListEntry> currentListItem = new ArrayList<ListEntry>();

    CustomAdapter(Activity context, ArrayList<ListEntry> androidFlavors) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, androidFlavors);
    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) listItemView = LayoutInflater.from(getContext()).inflate(
                R.layout.list_item, parent, false);

        ListEntry currentListItem;
        currentListItem = getItem(position);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        TextView magnitudeView = listItemView.findViewById(R.id.magnitude_view);
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentListItem.getMagnitude());
        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        String magnitude = Double.toString(currentListItem.getMagnitude());
        TextView magnitudeTextView = listItemView.findViewById(R.id.magnitude_view);
        magnitudeTextView.setText(magnitude);

        TextView directionTextView = listItemView.findViewById(R.id.direction_view);
        directionTextView.setText(currentListItem.getmDirection());

        TextView placeTextView = listItemView.findViewById(R.id.place_view);
        placeTextView.setText(currentListItem.getPlace());

        TextView timeTextView = listItemView.findViewById(R.id.time_view);
        timeTextView.setText(currentListItem.getTime());

        TextView dateTextView = listItemView.findViewById(R.id.date_view);
        dateTextView.setText(currentListItem.getmDate());

        return listItemView;

    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

//    public void setEmployees(ArrayList<ListEntry> data) {
//        currentListItem.addAll(data);
//        notifyDataSetChanged();
//    }

}
