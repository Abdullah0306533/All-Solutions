package com.projects.solutionpack.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.projects.solutionpack.R;
import com.projects.solutionpack.databinding.LoginsItemListBinding;
import com.projects.solutionpack.model.passwordsavermodel.Logins;

import java.util.ArrayList;

/**
 * Adapter class for the RecyclerView that displays a list of login entries.
 */
public class PasswordSaverAdapter extends RecyclerView.Adapter<PasswordSaverAdapter.ViewHolder> {

    private ArrayList<Logins> logins;

    /**
     * Constructor for the Adapter class.
     *
     * @param logins The list of login entries to be displayed.
     */
    public PasswordSaverAdapter(ArrayList<Logins> logins) {
        this.logins = logins;
    }

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LoginsItemListBinding loginsItemListBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.logins_item_list, parent, false);
        return new ViewHolder(loginsItemListBinding);
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Logins login = logins.get(position);
        holder.loginsItemListBinding.setLogin(login);
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return logins != null ? logins.size() : 0;
    }

    /**
     * Sets the logins list and notifies the adapter that the data set has changed.
     *
     * @param logins The new list of login entries.
     */
    public void setLogins(ArrayList<Logins> logins) {
        this.logins = logins;
        notifyDataSetChanged();
    }

    /**
     * ViewHolder class that holds the binding for a login item.
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        private final LoginsItemListBinding loginsItemListBinding;

        /**
         * Constructor for the ViewHolder class.
         *
         * @param loginsItemListBinding The binding for the login item.
         */
        public ViewHolder(LoginsItemListBinding loginsItemListBinding) {
            super(loginsItemListBinding.getRoot());
            this.loginsItemListBinding = loginsItemListBinding;
        }
    }
}
