package edu.orangecoastcollege.cs273.ocmusicevents2;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Connects the MusicEvent array to the view (activity_event_list)
 *
 * @author Devon Tallcott
 * @version 2.0
 *
 * Created on 9/26/2017.
 */

public class EventListAdapter extends ArrayAdapter<MusicEvent>
{
    private Context mContext;
    private int mResource;
    private List<MusicEvent> mAllEventsList;

    //context = Activity that uses the adapter (EventListActivity)
    //resource = Layout file to inflate (R.layout.music_event_list_item)
    //objects = list of Music Events

    /**
     * Creates and array adapter to then connect our events to the view
     * @param context
     * @param resource
     * @param allMusicEvents
     */
    public EventListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<MusicEvent> allMusicEvents)
    {
        super(context, resource, allMusicEvents);
        mContext = context;
        mResource = resource;
        mAllEventsList = allMusicEvents;
    }

    //Need to override the method getView
    @NonNull
    @Override
    /**
     * Inflates our layout file with the list of Music Events
     */
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
       //Use "inflater" to inflate the custom layout (R.layout.music_event_list_item)
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        //Inflating the custom layout for one single item in the list
        View listItemView = inflater.inflate(mResource, null);

        ImageView listItemImageView = (ImageView) listItemView.findViewById(R.id.listItemImageView);
        TextView listItemTitleTextView = (TextView) listItemView.findViewById(R.id.titleTextView);
        TextView listItemDateTextView = (TextView) listItemView.findViewById(R.id.dateTextView);

        MusicEvent selectedEvent = mAllEventsList.get(position);
        listItemTitleTextView.setText(selectedEvent.getTitle());
        listItemDateTextView.setText(selectedEvent.getDate());

        //Use asset manager to retrieve the image
        AssetManager am = mContext.getAssets();
        try
        {
            InputStream stream = am.open(selectedEvent.getImageName());
            Drawable image = Drawable.createFromStream(stream, selectedEvent.getTitle());
            listItemImageView.setImageDrawable(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listItemView;
    }
}
