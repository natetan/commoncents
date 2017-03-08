package edu.washington.ischool.commoncents.commoncents.adapters1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.washington.ischool.commoncents.commoncents.helpers1.ComponentHelper;
import edu.washington.ischool.commoncents.commoncents.R;
import edu.washington.ischool.commoncents.commoncents.models1.User;

/**
 * Created by keegomyneego on 3/5/17.
 */

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.ViewHolder> {

    static final String TAG = "UsersListAdapter";

    private static final int ITEM_LAYOUT_ID = R.layout.list_item_friend_for_friends_list;
    private static final int NAME_VIEW_ID = R.id.item_title;
    private static final int MONEY_VIEW_ID = R.id.item_info;
    private static final int PROFILE_PIC_VIEW_ID = R.id.profile_picture;

    private Context context;
    private Listener listener;
    private List<User> users = new ArrayList<>();

    //----------------------------------------------------------------------------------------------
    // Client Interface
    //----------------------------------------------------------------------------------------------

    /**
     * Listener to which event handling logic is delegated
     */
    public interface Listener {
        void onUserClicked(View view, User user);
        void onUserLongClicked(View view, User user);
        List<User> getUpdatedDataSet();
    }

    /**
     * Custom view holder for this adapter
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameView;
        public TextView moneyView;
        public ImageView profilePicView;

        public ViewHolder(View itemView, TextView nameView, TextView moneyView, ImageView profilePicView) {
            super(itemView);
            this.nameView = nameView;
            this.moneyView = moneyView;
            this.profilePicView = profilePicView;
        }
    }

    //----------------------------------------------------------------------------------------------
    // Adapter Implementation
    //----------------------------------------------------------------------------------------------

    public UsersListAdapter(Context context, Listener listener) {
        this.context = context;
        this.listener = listener;

        // Listen to calls to notifyDataSetChanged() so we can keep our local data up to date
        this.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                UsersListAdapter.this.updateDataSet();
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
        TextView nameView = (TextView) itemView.findViewById(NAME_VIEW_ID);
        TextView moneyView = (TextView) itemView.findViewById(MONEY_VIEW_ID);
        ImageView profilePicView = (ImageView) itemView.findViewById(PROFILE_PIC_VIEW_ID);

        return new ViewHolder(itemView, nameView, moneyView, profilePicView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final User user = users.get(position);

        // Set the view properties for this cell
        holder.nameView.setText(user.getName());
        ComponentHelper.getInstance().setOweAmount(context, holder.moneyView, user.getAmountOwed(), true);
        ComponentHelper.getInstance().setProfilePicture(holder.profilePicView, user, ComponentHelper.PictureType.IN_LIST_ITEM);

        // Set this cell's onClickListener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Delegate click logic to listener
                listener.onUserClicked(view, user);
            }
        });

        // Long click
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onUserLongClicked(view, user);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    // Update our local collection of users with those from the repo
    private void updateDataSet() {
        users = listener.getUpdatedDataSet();
    }
}
