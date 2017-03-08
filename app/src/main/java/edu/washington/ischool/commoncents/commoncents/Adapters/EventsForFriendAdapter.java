package edu.washington.ischool.commoncents.commoncents.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.washington.ischool.commoncents.commoncents.AppState;
import edu.washington.ischool.commoncents.commoncents.DataRepository;
import edu.washington.ischool.commoncents.commoncents.Helpers.ComponentHelper;
import edu.washington.ischool.commoncents.commoncents.Models.Event;
import edu.washington.ischool.commoncents.commoncents.Models.Friend;
import edu.washington.ischool.commoncents.commoncents.R;

/**
 * Created by keegomyneego on 3/7/17.
 */

public class EventsForFriendAdapter extends RecyclerView.Adapter<EventsForFriendAdapter.ViewHolder> {

    static final String TAG = "EventsForFriendAdapter";

    private static final int ITEM_LAYOUT_ID = R.layout.item_event_for_friend;
    private static final int PIC_VIEW_ID = R.id.item_picture;
    private static final int NAME_VIEW_ID = R.id.item_title;
    private static final int DESCRIPTION_VIEW_ID = R.id.item_subtitle;
    private static final int MONEY_VIEW_ID = R.id.item_info;

    private Context context;
    private Listener listener;
    private List<Event> events = new ArrayList<>();

    //----------------------------------------------------------------------------------------------
    // Client Interface
    //----------------------------------------------------------------------------------------------

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
        public ImageView picView;
        public TextView nameView;
        public TextView descriptionView;
        public TextView moneyView;

        public ViewHolder(View itemView, ImageView picView, TextView nameView, TextView descriptionView, TextView moneyView) {
            super(itemView);
            this.picView = picView;
            this.nameView = nameView;
            this.descriptionView = descriptionView;
            this.moneyView = moneyView;
        }
    }

    //----------------------------------------------------------------------------------------------
    // Adapter Implementation
    //----------------------------------------------------------------------------------------------

    public EventsForFriendAdapter(Context context, Listener listener) {
        this.context = context;
        this.listener = listener;

        // Listen to calls to notifyDataSetChanged() so we can keep our local data up to date
        this.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                EventsForFriendAdapter.this.updateDataSet();
            }
        });

        updateDataSet();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate container view for this item
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(ITEM_LAYOUT_ID, parent, false);

        // Get important views for this item
        ImageView picView = (ImageView) itemView.findViewById(PIC_VIEW_ID);
        TextView nameView = (TextView) itemView.findViewById(NAME_VIEW_ID);
        TextView descriptionView = (TextView) itemView.findViewById(DESCRIPTION_VIEW_ID);
        TextView moneyView = (TextView) itemView.findViewById(MONEY_VIEW_ID);

        return new ViewHolder(itemView, picView, nameView, descriptionView, moneyView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Event event = events.get(position);

        // Set the view properties for this cell
        ComponentHelper.getInstance().setEventPicture(holder.picView, event, ComponentHelper.PictureType.IN_LIST_ITEM);
        holder.nameView.setText(event.getName());
        holder.descriptionView.setText(event.getDescription());
        ComponentHelper.getInstance().setOweAmount(context, holder.moneyView, event.getAmountOwed(AppState.getCurrentState().getCurrentUser()));

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

    // Update our local collection of events with those from the repo
    private void updateDataSet() {
        events = DataRepository.getInstance().getEvents();
    }
}