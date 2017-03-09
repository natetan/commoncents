package edu.washington.ischool.commoncents.commoncents.adapters1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.washington.ischool.commoncents.commoncents.AppState;
import edu.washington.ischool.commoncents.commoncents.R;
import edu.washington.ischool.commoncents.commoncents.helpers1.ComponentHelper;
import edu.washington.ischool.commoncents.commoncents.models1.Event;
import edu.washington.ischool.commoncents.commoncents.models1.LineItem;
import edu.washington.ischool.commoncents.commoncents.models1.Payment;

/**
 * Created by keegomyneego on 3/8/17.
 */

public class LineItemsListAdapter extends RecyclerView.Adapter<LineItemsListAdapter.ViewHolder> {

    static final String TAG = "LineItemsListAdapter";

    private static final int ITEM_LAYOUT_ID = R.layout.list_item_pic_title_info;
    private static final int PIC_VIEW_ID = R.id.item_picture;
    private static final int NAME_VIEW_ID = R.id.item_title;
    private static final int DESCRIPTION_VIEW_ID = R.id.item_subtitle;
    private static final int MONEY_VIEW_ID = R.id.item_info;

    private Context context;
    private List<LineItem> lineItems;

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

    public LineItemsListAdapter(Context context, List<LineItem> lineItems) {
        this.context = context;
        this.lineItems = lineItems;
    }

    @Override
    public LineItemsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate container view for this item
        View itemView = LayoutInflater.from(parent.getContext()).inflate(ITEM_LAYOUT_ID, parent, false);

        // Get important views for this item
        TextView nameView = (TextView) itemView.findViewById(NAME_VIEW_ID);
        TextView descrView = (TextView) itemView.findViewById(DESCRIPTION_VIEW_ID);
        TextView moneyView = (TextView) itemView.findViewById(MONEY_VIEW_ID);
        ImageView imageView = (ImageView) itemView.findViewById(PIC_VIEW_ID);

        return new LineItemsListAdapter.ViewHolder(itemView, nameView, descrView, moneyView, imageView);
    }

    @Override
    public void onBindViewHolder(LineItemsListAdapter.ViewHolder holder, int position) {
        final LineItem lineItem = lineItems.get(position);

        // Set title
        holder.nameView.setText(lineItem.getName());

        // Set subtitle (hiding it empty)
        String description = "";
        if (description == null || description.isEmpty()) {
            holder.descrView.setVisibility(View.GONE);
        } else {
            holder.descrView.setText(description);
        }

        int centsOwed = 0;
        List<Payment> payments = lineItem.getPayments();
        if (payments != null) {
            for (Payment payment : payments) {
                if (payment.getUser().equals(AppState.getCurrentState().getCurrentUser())) {
                    centsOwed += payment.getAmount();
                }
            }
        }

        // Set info to be the amount of money owed to the current
        // user in this event
        ComponentHelper.getInstance().setOweAmount(context, holder.moneyView,
                centsOwed, true);

        // Set event picture
        ComponentHelper.getInstance().setLineItemPicture(holder.imageView, lineItem,
                ComponentHelper.PictureType.IN_LIST_ITEM);
    }

    @Override
    public int getItemCount() {
        return lineItems.size();
    }
}
