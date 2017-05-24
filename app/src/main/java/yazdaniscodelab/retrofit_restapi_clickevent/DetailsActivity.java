package yazdaniscodelab.retrofit_restapi_clickevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {


    private ImageView mPhoto;
    private TextView mName,mID,mCategory,mInstruction,mPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent=getIntent();
        Flower flower= (Flower) intent.getSerializableExtra(Constants.REF.FLOWER);

        addDetails();

        mID.setText(String.format("%d",flower.getProductId()));
        mName.setText(flower.getName());
        mInstruction.setText(flower.getInstructions());
        mCategory.setText(flower.getCategory());
        mPrice.setText(String.format("$%.2f",flower.getPrice()));

        Picasso.with(getApplicationContext())
                .load("http://services.hanselandpetal.com/photos/"+flower.getPhoto())
                .into(mPhoto);
    }


    public void addDetails(){

        mPhoto=(ImageView)findViewById(R.id.flower_photo);
        mName=(TextView)findViewById(R.id.flowerName_xml);
        mCategory=(TextView)findViewById(R.id.flowerCategory_xml);
        mID=(TextView)findViewById(R.id.flowerID_xml);
        mInstruction=(TextView)findViewById(R.id.flowerInstruction_xml);
        mPrice=(TextView)findViewById(R.id.flowerPrice_xml);

    }


}
