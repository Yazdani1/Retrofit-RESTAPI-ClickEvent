package yazdaniscodelab.retrofit_restapi_clickevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements FlowerAdapter.FlowerClickListener {

    private RecyclerView recyclerView;

    private FlowerAdapter flowerAdapter;

    private RestManager restManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addallItem();
        restManager=new RestManager();

        Call<List<Flower>>listCall=restManager.getmFlowerServices().getallFlower();

        listCall.enqueue(new Callback<List<Flower>>() {
            @Override
            public void onResponse(Call<List<Flower>> call, Response<List<Flower>> response) {

                if (response.isSuccessful()){
                    List<Flower>flowerList=response.body();
                    for (int i=0;i<flowerList.size();i++){
                        Flower flower=flowerList.get(i);
                        flowerAdapter.addFlower(flower);
                    }

                }

            }

            @Override
            public void onFailure(Call<List<Flower>> call, Throwable t) {

            }
        });


    }

    public void addallItem(){
        recyclerView=(RecyclerView)findViewById(R.id.recycler_xml);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        flowerAdapter=new FlowerAdapter(this);
        recyclerView.setAdapter(flowerAdapter);
    }

    @Override
    public void onClick(int position) {

        Flower selectFlower=flowerAdapter.getFlowerM(position);
        Intent intent=new Intent(MainActivity.this,DetailsActivity.class);
        intent.putExtra(Constants.REF.FLOWER,selectFlower);
        startActivity(intent);

    }


}
