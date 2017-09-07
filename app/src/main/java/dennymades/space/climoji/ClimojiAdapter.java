package dennymades.space.climoji;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;

/**
 * Created by abrain on 8/19/17.
 */

public class ClimojiAdapter extends RecyclerView.Adapter<ClimojiAdapter.ClimojiViewHolder> {
    private final String TAG = ClimojiAdapter.class.getSimpleName();
    private List<Integer> fileIds;
    private LayoutInflater mLayoutInflater;
    private Activity mActivity;
    private Context mContext;

    private final int CLIMOJI_NUM = 14;

    public ClimojiAdapter(Context context, Activity activity){
        mContext = context;
        mActivity = activity;
        mLayoutInflater = LayoutInflater.from(context);
        fileIds = new ArrayList<>();
        for(int i=0;i<CLIMOJI_NUM;i++){
            int picId = mActivity.getResources().getIdentifier("climoji_"+(i+1), "drawable", mActivity.getApplicationContext().getPackageName());
            fileIds.add(picId);
        }
    }

    @Override
    public ClimojiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_climoji_grid, parent, false);
        ClimojiViewHolder viewHolder = new ClimojiViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ClimojiViewHolder holder, final int position) {
        int drawableId = fileIds.get(position);
        //holder.getClimojiLabel().setText(drawableId);
        Picasso.with(mContext).load(fileIds.get(position)).into(holder.getClimojiImage());

        holder.getClimojiCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "clicked on "+position);
                sendImage(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fileIds.size();
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

    private void sendImage(int position){
        String path = "android.resource://dennymades.space.climoji/drawable/climoji_"+(position+1);
        Uri uriToImage = Uri.parse(path                                                                                                                                                                     );

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
        shareIntent.setType("image/png");
        mActivity.startActivity(Intent.createChooser(shareIntent, "Climoji"));
    }
}
