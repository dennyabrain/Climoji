package dennymades.space.climoji;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;

/**
 * Created by abrain on 8/19/17.
 */

public class ClimojiAdapter extends RecyclerView.Adapter<ClimojiAdapter.ClimojiViewHolder> {
    private List<String> fileNames;
    private LayoutInflater mLayoutInflater;
    private Activity mActivity;

    public ClimojiAdapter(Context context, Activity activity){
        mActivity = activity;
        mLayoutInflater = LayoutInflater.from(context);
        fileNames = new ArrayList<String>();
        fileNames.add("Name 1");
        fileNames.add("Name 2");
        fileNames.add("Name 3");
        fileNames.add("Name 4");
    }

    @Override
    public ClimojiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_climoji_grid, parent, false);
        ClimojiViewHolder viewHolder = new ClimojiViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ClimojiViewHolder holder, int position) {
        String climojiName = fileNames.get(position);
        holder.getClimojiLabel().setText(climojiName);
    }

    @Override
    public int getItemCount() {
        return fileNames.size();
    }

    public class ClimojiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Nullable @BindView(R.id.climoji_card_view) CardView climojiCardView;
        @Nullable @BindView(R.id.climoji_image) ImageView climojiImage;
        @Nullable @BindView(R.id.climoji_label) TextView climojiLabel;

        public ClimojiViewHolder(View v){
            super(v);
            ButterKnife.bind(this, v);
        }

        @Override
        public void onClick(View view) {

        }

        public CardView getClimojiCardView() {
            return climojiCardView;
        }

        public ImageView getClimojiImage() {
            return climojiImage;
        }

        public TextView getClimojiLabel() {
            return climojiLabel;
        }
    }
}
