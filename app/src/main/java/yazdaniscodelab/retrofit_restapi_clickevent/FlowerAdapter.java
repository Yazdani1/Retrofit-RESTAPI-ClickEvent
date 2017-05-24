package yazdaniscodelab.retrofit_restapi_clickevent;

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


/**
 * Created by Yazdani on 5/24/2017.
 */

public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter.MyHolder> {

    private static final String TAG =FlowerAdapter.class.getSimpleName() ;
    private List<Flower>mFlowerList;

    private FlowerClickListener mClickListener;

    public FlowerAdapter(FlowerClickListener listener){
        mFlowerList=new ArrayList<>();
        mClickListener=listener;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View myview= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        MyHolder myHolder=new MyHolder(myview);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        Flower flower=mFlowerList.get(position);

        holder.mFlowerName.setText(flower.getName());
        holder.mFlowerPrice.setText(Double.toString(flower.getPrice()));

        Picasso.with(holder.itemView.getContext())
                .load("http://services.hanselandpetal.com/photos/"+flower.getPhoto())
                .into(holder.mFlowerPhoto);

    }

    @Override
    public int getItemCount() {
        return mFlowerList.size();
    }

    public void addFlower(Flower flower){
        Log.d(TAG,flower.getPhoto());
        mFlowerList.add(flower);
        notifyDataSetChanged();
    }

   public Flower getFlowerM(int position){
     return mFlowerList.get(position);
   }



    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView mFlowerPhoto;
        private TextView mFlowerName;
        private TextView mFlowerPrice;

        public MyHolder(View view){
            super(view);

            mFlowerPhoto=(ImageView)view.findViewById(R.id.flowerPhoto_xml);
            mFlowerName=(TextView)view.findViewById(R.id.flowerName_xml);
            mFlowerPrice=(TextView)view.findViewById(R.id.flowerPrice_xml);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            mClickListener.onClick(getLayoutPosition());

        }
    }

    public interface FlowerClickListener{
        void onClick(int position);
    }


}
