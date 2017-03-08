package edu.washington.ischool.commoncents.commoncents.adapters1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.washington.ischool.commoncents.commoncents.DataRepository;
import edu.washington.ischool.commoncents.commoncents.helpers1.ComponentHelper;
import edu.washington.ischool.commoncents.commoncents.models1.Event;
import edu.washington.ischool.commoncents.commoncents.R;

/**
 * Created by IreneW on 2017-03-06.
 */

public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.ViewHolder>  {

    static final String TAG = "EventsListAdapter";

    private static final int ITEM_LAYOUT_ID = R.layout.item_event_for_events_list;
    private static final int PIC_VIEW_ID = R.id.item_picture;
    private static final int NAME_VIEW_ID = R.id.item_title;
    private static final int DESCRIPTION_VIEW_ID = R.id.item_subtitle;
    private static final int MONEY_VIEW_ID = R.id.item_info;

    private Listener listener;
    private List<Event> events = new ArrayList<>();

    /**
     * Listener to which event handling logic is delegated
     */
    public interface Listener {
        void onEventClicked(View view, Event event);
    }

    /**
     * Custom view holder for this adapter
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameView;
        public TextView descrView;
        public TextView moneyView;
        public ImageView imageView;

        public ViewHolder(View itemView, TextView nameView, TextView descrView, TextView moneyView,
                          ImageView imageView) {
            super(itemView);
            this.nameView = nameView;
            this.descrView = descrView;
            this.moneyView = moneyView;
            this.imageView = imageView;
        }
    }

    //----------------------------------------------------------------------------------------------
    // Adapter Implementation
    //----------------------------------------------------------------------------------------------

    public EventsListAdapter(EventsListAdapter.Listener listener) {
        this.listener = listener;

        // Listen to calls to notifyDataSetChanged() so we can keep our local data up to date
        this.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                EventsListAdapter.this.updateDataSet();
            }
        });

        updateDataSet();
    }

    @Override
    public EventsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate container view for this item
        View itemView = LayoutInflater.from(parent.getContext()).inflate(ITEM_LAYOUT_ID, parent, false);

        // Get important views for this item
        TextView nameView = (TextView) itemView.findViewById(NAME_VIEW_ID);
        TextView descrView = (TextView) itemView.findViewById(DESCRIPTION_VIEW_ID);
        TextView moneyView = (TextView) itemView.findViewById(MONEY_VIEW_ID);
        ImageView imageView = (ImageView) itemView.findViewById(PIC_VIEW_ID);

        return new EventsListAdapter.ViewHolder(itemView, nameView, descrView, moneyView, imageView);
    }

    @Override
    public void onBindViewHolder(EventsListAdapter.ViewHolder holder, int position) {
        final Event event = events.get(position);

        // Set the view properties for this cell
        holder.nameView.setText(event.getName());
        holder.descrView.setText(event.getDescription());
        // Not sure where to go get the calculated costs
        holder.moneyView.setText("$100");
        ComponentHelper.getInstance().setEventPicture(holder.imageView, event,
                ComponentHelper.PictureType.IN_LIST_ITEM);

        // Set this cell's onClickListener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Delegate click logic to listener
                listener.onEventClicked(view, event);
            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    // Update our local collection of EVENTS with those from the repo
    private void updateDataSet() {
        events = DataRepository.getInstance().getEvents();
    }
}
